package com.lwsmilece.enchantedreactor;

import reactor.core.publisher.Flux;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;

public class ReactorTest {


    public static void main(String[] args) {
        Flux.just("apple", "orange")
                .subscribe(System.out::println);
    }

}
