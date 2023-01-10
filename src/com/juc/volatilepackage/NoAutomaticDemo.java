package com.juc.volatilepackage;

/**
 * @author 李广鹏
 */
public class NoAutomaticDemo {

    static volatile int num = 0;

    public void add() {
        num++;
    }

    public static void main(String[] args) {

        NoAutomaticDemo demo = new NoAutomaticDemo();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for(int j = 0; j < 10000; j++){
                    demo.add();
                }
            }, String.valueOf(i)).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(num);
    }
}
