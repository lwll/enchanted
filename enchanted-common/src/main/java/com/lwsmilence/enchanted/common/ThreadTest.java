package com.lwsmilence.enchanted.common;

import lombok.Data;

import java.util.concurrent.Exchanger;

public class ThreadTest {


    @Data
    private class Entity {
        private String param;
    }

    public void test() throws InterruptedException {
        Entity entity = new Entity();

        entity.wait();
    }


    public static void main(String[] args) {
        Object entity = new Object();

        try {
            Exchanger exchanger = new Exchanger();
            Thread thread = new Thread();
            thread.join();
            entity.wait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
