package com.juc.volatilepackage;

/**
 * @author 李广鹏
 */
public class VolatileDemo {

    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {
                System.out.println("循环");
            }
            System.out.println("结束循环");
        }, "t1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("开始变更值");
        flag = false;
        System.out.println("值变更完成");
    }
}
