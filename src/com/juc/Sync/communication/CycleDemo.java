package com.juc.Sync.communication;


/**
 * @author 李广鹏
 * 线程通信
 */
public class CycleDemo {

    private int number = 0;

    public synchronized void increase() throws InterruptedException {

        // 使用条件判断时wait()会出现虚假唤醒
        // if(number != 0){ this.wait(); }
        while(number != 0){
            this.wait();
        }

        number++;

        System.out.println(Thread.currentThread().getName()+" :: "+number);

        // 唤醒所有休眠线程
        this.notifyAll();

    }

    public synchronized void subtraction() throws InterruptedException {

        // 使用条件判断时wait()会出现虚假唤醒
        // if(number != 1){ this.wait(); }
        while(number != 1){
            this.wait();
        }

        number--;

        System.out.println(Thread.currentThread().getName()+" :: "+number);

        // 唤醒所有休眠线程
        this.notifyAll();

    }

}
