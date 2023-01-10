package com.juc.automatic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 李广鹏
 */
public class Demo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    private void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " SpinLock Lock......");
        // 当期望值为null时，线程抢占用；否则自旋
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + " Lock Loop Spin......");
        }
    }

    private void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " SpinLock Unlock......");
        // 当期望值为不为null时，线程释放锁；否则自旋
        while (!atomicReference.compareAndSet(thread, null)) {
            System.out.println(Thread.currentThread().getName() + " Unlock Loop Spin......");
        }
    }

    public static void main(String[] args) {

        Demo demo = new Demo();

        new Thread(() -> {
            demo.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            demo.unlock();
        }, "t1").start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            demo.lock();

            demo.unlock();
        }, "t2").start();
    }
}
