package com.lwsmilence.enchanted.common.designPattern.proxy;

/**
 * 静态代理模式。
 * 代理的对象会显示地声明为成员变量，对外提供代理对象的功能（函数），实际内部是用代理对象的功能（函数）
 */
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
