package com.juc.interrupt;

/**
 * @author 李广鹏
 */
public class ThreadStaticInterruptDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+ " 中断状态标记：" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+ " 中断状态标记：" + Thread.interrupted());
        // 设置当前线程中断状态：
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName()+ " 中断状态标记：" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+ " 中断状态标记：" + Thread.interrupted());
    }
}
