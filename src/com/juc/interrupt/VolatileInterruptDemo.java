package com.juc.interrupt;

/**
 * @author 李广鹏
 */
public class VolatileInterruptDemo {

    static boolean isVolatileMark = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (isVolatileMark) {
                    System.out.println("Stop");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " Volatile");
            }
        }, "thread-1").start();

        Thread.sleep(100);

        new Thread(() -> {
            isVolatileMark = true;
        }, "thread-2").start();

    }
}
