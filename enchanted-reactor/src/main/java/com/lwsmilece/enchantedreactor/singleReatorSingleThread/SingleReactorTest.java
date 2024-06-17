package com.lwsmilece.enchantedreactor.singleReatorSingleThread;

import java.io.IOException;
import java.net.ServerSocket;

public class SingleReactorTest {

    public static void main(String[] args) throws IOException {

        Reactor reactor = new Reactor(1717);

        new Thread(reactor).start();
    }
}
