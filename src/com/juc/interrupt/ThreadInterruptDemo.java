package com.juc.interrupt;

/**
 * @author 李广鹏
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                // 检测当前线程中断状态
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Stop");
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                    // throw new RuntimeException(e);
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " ThreadInterrupt");
            }
        }, "thread1");
        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        new Thread(() -> {
            // 设置线程中断状态
            thread1.interrupt();
        }, "thread2").start();

    }
}
