package com.lwsmilence.enchanted.common.designPattern.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxyImageTest {

    public static void main(String[] args) {
        RealImage realImage = new RealImage("test.jpg");

        Image imageProxy = (Image) Proxy.newProxyInstance(
                Image.class.getClassLoader(),
                new Class[] { Image.class },
                new DynamicProxyImage(realImage)
        );

        // 代理对象调用方法
        imageProxy.draw();
    }
}
