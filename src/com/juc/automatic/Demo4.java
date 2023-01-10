package com.juc.automatic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author 李广鹏
 */
public class Demo4 {

    public int number;

    // 加锁
    public synchronized void addBySynchronized() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addByAtomicInteger() {
        atomicInteger.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void addByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void addByLongAccumulator() {
        longAccumulator.accumulate(1);
    }

    public int getNumber() {
        return number;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public LongAdder getLongAdder() {
        return longAdder;
    }

    public LongAccumulator getLongAccumulator() {
        return longAccumulator;
    }

    public static void main(String[] args) throws InterruptedException {

        Demo4 demo4 = new Demo4();

        CountDownLatch cl1 = new CountDownLatch(100);
        CountDownLatch cl2 = new CountDownLatch(100);
        CountDownLatch cl3 = new CountDownLatch(100);
        CountDownLatch cl4 = new CountDownLatch(100);

        long start;
        long end;
        long time;

        start = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100000; j++) {
                        demo4.addBySynchronized();
                    }
                } finally {
                    cl1.countDown();
                }
            }, "t" + i).start();
        }
        cl1.await();
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(demo4.getNumber() + " addBySynchronized：" + time);

        start = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100000; j++) {
                        demo4.addByAtomicInteger();
                    }
                } finally {
                    cl2.countDown();
                }
            }, "t" + i).start();
        }
        cl2.await();
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(demo4.getAtomicInteger().get() + " addByAtomicInteger：" + time);

        start = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100000; j++) {
                        demo4.addByLongAdder();
                    }
                } finally {
                    cl3.countDown();
                }
            }, "t" + i).start();
        }
        cl3.await();
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(demo4.getLongAdder().sum() + " addByLongAdder：" + time);

        start = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100000; j++) {
                        demo4.addByLongAccumulator();
                    }
                } finally {
                    cl4.countDown();
                }
            }, "t" + i).start();
        }
        cl4.await();
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println(demo4.getLongAccumulator().get() + " addByLongAccumulator：" + time);
    }

}
