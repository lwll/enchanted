package com.lwsmilence.enchanted.common.designPattern.singleton;

/**
 * 静态内部类实现。Bill Pugh是实现此方式的人名
 * 静态内部类只有在外部类加载时，才会被加载和初始化。由于类加载机制的保证，这个加载过程是线程安全的。
 * 静态内部类的成员变量是静态的，因此只会在内存中存在一份。
 */
public class BillPughSingleton {


    private BillPughSingleton() {}

    private static class SingletonHelper {
        private static final BillPughSingleton singleton = new BillPughSingleton();

    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.singleton;
    }
}
