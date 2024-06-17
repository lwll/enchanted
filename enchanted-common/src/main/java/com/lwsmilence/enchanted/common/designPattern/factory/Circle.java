package com.lwsmilence.enchanted.common.designPattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Circle implements Shape {
    @Override
    public void draw() {
        log.info("i am a circle");
    }
}
