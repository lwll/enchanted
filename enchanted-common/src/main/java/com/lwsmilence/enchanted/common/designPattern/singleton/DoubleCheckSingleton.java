package com.lwsmilence.enchanted.common.designPattern.singleton;

/**
 * 双重检查锁定的单例，线程安全
 */
public class DoubleCheckSingleton {

    // 使用volatile，线程可见性
    private static volatile DoubleCheckSingleton singleton;

    private DoubleCheckSingleton() {}

    public static DoubleCheckSingleton getInstance() {
        if (singleton == null) {
            // 需要时才同步
            synchronized (DoubleCheckSingleton.class) {
                // 需要再判断一次
                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
