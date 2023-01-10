package com.juc.communication;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 李广鹏
 */
public class LockSupportCom {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("LockSupport come in...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            LockSupport.park();
            LockSupport.park();
            System.out.println("LockSupport被唤醒...");
        }, "t1");
        t1.start();

        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println("LockSupport唤醒...");
        }, "t2").start();
    }
}
