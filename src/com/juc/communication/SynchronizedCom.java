package com.juc.communication;

/**
 * @author 李广鹏
 */
public class SynchronizedCom {
    public static void main(String[] args) {

        Object obj = new Object();

        new Thread(() -> {
            System.out.println("Synchronized come in...");
            // synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Synchronized被唤醒...");
            // }
        },"t1").start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            // synchronized (obj) {
                obj.notify();
                System.out.println("Synchronized唤醒...");
            // }
        },"t2").start();

    }
}
