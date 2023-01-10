package com.juc.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExceptionDemo {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, "线程" + i).start();
        }
    }
}
