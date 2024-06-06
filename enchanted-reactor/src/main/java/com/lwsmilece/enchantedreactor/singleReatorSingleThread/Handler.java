package com.lwsmilece.enchantedreactor.singleReatorSingleThread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    private final SocketChannel socketChannel;

    private final SelectionKey selectionKey;

    private static final int READING = 0, SENDING = 1;

    private int state = READING;

    private final ByteBuffer buffer = ByteBuffer.allocate(256);

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        this.selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);

        this.selectionKey.attach(this);

        selector.wakeup();
    }

    @Override
    public void run()  {

    }

    private void read() throws IOException {
        int byteRead = socketChannel.read(buffer);
        if (byteRead == -1) {
            socketChannel.close();
        } else if (byteRead > 0) {
            buffer.flip();
            state = SENDING;
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private void send() throws IOException {
        socketChannel.write(buffer);
        if (!buffer.hasRemaining()) {
            buffer.clear();
            state = READING;
            selectionKey.interestOps(SelectionKey.OP_READ);
        }
    }
}
