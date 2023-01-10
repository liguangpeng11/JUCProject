package com.juc.lock.custom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李广鹏
 *  线程间定制化通信：
 *      A线程打印2次，B线程打印4次，C线程打印6次,按照此顺序循环 6 轮
 */
public class CustomConmunicationDemo {

    /**
     * flag=1为A线程，flag=2为B线程，flag =3为C线程。
     */
    private int flag = 1;

    /**
     * 得到Lock锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(int loop){

        lock.lock();

        try {
            while (flag != 1){
                condition1.await();
            }

            // 循环
            for (int i = 1; i <=2; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+i+" ：轮数："+loop);
            }

            flag = 2;
            // 唤醒对应线程
            condition2.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB(int loop){

        lock.lock();

        try {
            while (flag != 2){
                condition2.await();
            }

            // 循环
            for (int i = 1; i <=4; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+i+" ：轮数："+loop);
            }

            flag = 3;
            // 唤醒对应线程
            condition3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int loop){
        lock.lock();

        try {
            while (flag != 3){
                condition3.await();
            }

            // 循环
            for (int i = 1; i <=6; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+i+" ：轮数："+loop);
            }

            flag = 1;
            // 唤醒对应线程
            condition1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
