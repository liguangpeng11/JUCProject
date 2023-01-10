package com.juc.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 李广鹏
 */
public class FutureTaskDemo3 {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            Integer i = 1;
            System.out.println("one : " + i);
            return i;
        }).handle((i,e) -> {
            // int j = 10/0;
            Integer num = i + 1;
            System.out.println("two : " + num);
            return num;
        }).handle((i,e) -> {
            Integer num = i + 1;
            System.out.println("three : " + num);
            return num;
        }).whenCompleteAsync((value, e) -> {
            System.out.println(value);
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println("主线程！！！");
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
