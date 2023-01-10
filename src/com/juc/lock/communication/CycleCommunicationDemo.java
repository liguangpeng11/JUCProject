package com.juc.lock.communication;



public class CycleCommunicationDemo {

    public static void main(String[] args) {

        CycleDemo cycleDemo = new CycleDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=10;i++){
                    try {
                        cycleDemo.increase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"increase").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=10;i++){
                    try {
                        cycleDemo.increase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"increase2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=10;i++){
                    try {
                        cycleDemo.subtraction();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"subtraction").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=10;i++){
                    try {
                        cycleDemo.subtraction();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"subtraction2").start();

    }

}
