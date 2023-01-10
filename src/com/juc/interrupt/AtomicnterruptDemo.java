package com.juc.interrupt;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 李广鹏
 */
public class AtomicnterruptDemo {
    static AtomicBoolean isAtomicnMark = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (isAtomicnMark.get()) {
                    System.out.println("Stop");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " AtomicBoolean");
            }
        }, "thread-1").start();

        Thread.sleep(100);

        new Thread(() -> {
            isAtomicnMark.set(true);
        }, "thread-2").start();

    }
}
