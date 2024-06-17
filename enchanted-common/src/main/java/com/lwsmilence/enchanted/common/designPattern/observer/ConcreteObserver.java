package com.lwsmilence.enchanted.common.designPattern.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteObserver implements Observer {
    @Override
    public void update(String message) {
        log.info("Receive a message:{}", message);
    }
}
