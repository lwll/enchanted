package com.lwsmilence.enchanted.common.multithread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * CopyOnWrite,在进行修改操作时，会创建原始数据的副本，修改操作会在副本上执行，完成后再将副本替换为原始数据
 */
public class CopyOnWriteExample {

    /**
     * CopyOnWrite在多线程写的时候，内部会使用锁，所以能保证最终数据一致性
     * @throws InterruptedException
     */
    static void multipleWriteExample() throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("one");
        list.add("two");

        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                list.add(Thread.currentThread().getName() + "-" + i);
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("list 最终数据");
        System.out.println(list);
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
//        List<String> list =  Collections.synchronizedList(new ArrayList<>());

        list.add("A");
        list.add("B");
        list.add("C");

        for (String s : list) {
            if (s.equals("A")) {
                list.add("D");
            }
            // 此时不会输出D
            System.out.println(s);
        }



        System.out.println("list 最终数据");
        // 最终会输出ABCD
        System.out.println(list);

        multipleWriteExample();
//        list.forEach(System.out::println);
    }
}
