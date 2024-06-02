package com.lwsmilece.enchantedreactor;

import reactor.core.publisher.Flux;

public class ReactorTest {


    public static void main(String[] args) {
        Flux.just("apple", "orange")
                .subscribe(System.out::println);
    }

}
