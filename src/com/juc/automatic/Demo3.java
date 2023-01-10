package com.juc.automatic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author 李广鹏
 */
public class Demo3 {

    public volatile Boolean flag = Boolean.FALSE;

    AtomicReferenceFieldUpdater<Demo3, Boolean> atomicReferenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(Demo3.class, Boolean.class, "flag");

    public void init(Demo3 demo3) {
        if (atomicReferenceFieldUpdater.compareAndSet(demo3, Boolean.FALSE, Boolean.TRUE)) {
            // 初始化
            System.out.println(Thread.currentThread().getName() +": 初始化Boolean对象！！！");
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
        } else {
            System.out.println(Thread.currentThread().getName() +": Boolean对象已被初始化！！！");
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                demo3.init(demo3);
            }, "T" + i).start();
        }
    }
}
