package com.juc.lock.custom;

import java.util.HashMap;
import java.util.Map;

public class CustomMainDemo {

    public static void main(String[] args) {



        CustomConmunicationDemo customConmunicationDemo = new CustomConmunicationDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=6;i++){
                    customConmunicationDemo.printA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=6;i++){
                    customConmunicationDemo.printB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=6;i++){
                    customConmunicationDemo.printC(i);
                }
            }
        },"C").start();

    }

}
