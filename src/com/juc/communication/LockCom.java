package com.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李广鹏
 */
public class LockCom {
    public static void main(String[] args) {

        Lock lk = new ReentrantLock();
        Condition condition = lk.newCondition();

        new Thread(() -> {
            System.out.println("Condition come in...");
            lk.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lk.unlock();
            }
            System.out.println("Condition被唤醒...");
        }, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            lk.lock();
            try {
                condition.signal();
                System.out.println("Condition唤醒...");
            } finally {
                lk.unlock();
            }
        }, "t2").start();

    }
}
