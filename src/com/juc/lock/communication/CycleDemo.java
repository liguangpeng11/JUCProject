package com.juc.lock.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李广鹏
 * 线程通信
 */
public class CycleDemo {

    private int number = 0;

    /**
     * 得到Lock锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() throws InterruptedException {
        // 上锁
        lock.lock();
        try{
            // 判断
            while (number != 0){
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            // 释放锁
            condition.signalAll();
        }finally {
            // 解锁
            lock.unlock();
        }
    }

    public void subtraction() throws InterruptedException {
        // 上锁
        lock.lock();
        try{
            // 判断
            while (number != 1){
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            // 释放锁
            condition.signalAll();
        }finally {
            // 解锁
            lock.unlock();
        }
    }

}
