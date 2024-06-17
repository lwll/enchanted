package com.lwsmilence.enchanted.common.designPattern.singleton;

/**
 * 懒汉式单例，线程不安全
 */
public class LazySingleton {
    private static LazySingleton lazySingleton;

    // 私有构造函数
    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            // 多个线程会同时进来
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
