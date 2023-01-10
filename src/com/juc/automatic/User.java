package com.juc.automatic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author 李广鹏
 */
public class User {

    String name = "LGP";
    public volatile int number;

    /**
     * 只变更User的number属性
     *
     * @param user
     */
    public void add(User user) {
        AtomicIntegerFieldUpdater.newUpdater(User.class, "number").getAndIncrement(user);
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        User user = new User();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        user.add(user);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "T" + i).start();
        }
        countDownLatch.await();

        System.out.println(user.getNumber());
    }
}
