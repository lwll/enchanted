package com.lwsmilence.enchanted.common.designPattern.proxy;

public class StaticProxyImage implements Image {

    private RealImage realImage;

    private String filename;

    public StaticProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void draw() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.draw();
    }
}
