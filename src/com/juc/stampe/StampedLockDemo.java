package com.juc.stampe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author 李广鹏
 */
public class StampedLockDemo {

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo demo = new StampedLockDemo();
        new Thread(() -> {
            try {
                demo.read();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "read").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            try {
                demo.Write();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "write").start();
    }

    static int number = 100;
    static StampedLock stampedLock = new StampedLock();

    public void read() throws InterruptedException {

        // 乐观读模式：在读操作运行时允许读操作介入
        long satmp = stampedLock.tryOptimisticRead();

        for (int i = 1; i <= 5; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Number值是否被修改：" + stampedLock.validate(satmp) + "  number值是：" + number);
        }

        // validate(satmp)被修改返回值为false：将乐观锁升级为悲观锁。
        if (!stampedLock.validate(satmp)) {
            System.out.println("被其他线程修改，乐观锁升级为悲观锁");
            satmp = stampedLock.readLock();
            try {
                System.out.println("被其他线程修改后的number值：" + number);
            } finally {
                stampedLock.unlock(satmp);
            }
        }

        System.out.println("最终在主内存的值：" + number);
    }

    public void Write() {
        long satmp = stampedLock.writeLock();
        System.out.println("number值开始修改，修改前：" + number);
        try {
            number += 1;
        } finally {
            stampedLock.unlockWrite(satmp);
        }
        System.out.println("number值修改完成；修改后：" + number);
    }

}
