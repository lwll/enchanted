package com.lwsmilence.enchanted.common.designPattern.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        log.info("loading file: {} ...", fileName);
    }

    @Override
    public void draw() {
        log.info("drawing");
    }
}
