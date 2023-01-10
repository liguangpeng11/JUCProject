package com.juc.syncup;

/**
 * @author 李广鹏
 */
public class Demo {

    static Object obj = new Object();

    // 锁消除
    public void m1() {
        Object object = new Object();
        synchronized (object) {
            System.out.println(object.hashCode() + " " + obj.hashCode());
        }
    }

    public void m2() {
        synchronized (obj) {
            System.out.println("1");
        }
        synchronized (obj) {
            System.out.println("2");
        }
        synchronized (obj) {
            System.out.println("3");
        }

        /*
            synchronized (obj) {
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
            }
        */
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        for (int i = 0; i < 10; i++) {
            new Thread(
                    ()->{
                        demo.m1();
                    }
            ).start();
        }
    }
}
