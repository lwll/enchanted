package com.lwsmilence.enchanted.common.designPattern.singleton;

// 线程安全式的懒汉式单例
public class SynchronizedSingleton {
    private static SynchronizedSingleton singleton;

    private SynchronizedSingleton() {}

    public static synchronized SynchronizedSingleton getInstance() {
        if (singleton == null) {
            singleton = new SynchronizedSingleton();
        }
        return singleton;
    }
}
