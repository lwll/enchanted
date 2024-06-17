package com.lwsmilence.enchanted.common.designPattern.singleton;

/**
 * 饿汉式单例，线程安全
 */
public class EagerSingleton {
    // 私有构造函数，避免直接实例化
    private EagerSingleton() {}

    // 类加载时就实例化对象
    private static final EagerSingleton eagerSingleton = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }
}
