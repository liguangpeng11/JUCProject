package com.juc.automatic;

import java.util.concurrent.CountDownLatch;

/**
 * @author 李广鹏
 */
public class Demo2 {
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
