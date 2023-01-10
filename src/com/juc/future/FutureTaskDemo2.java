package com.juc.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author 李广鹏
 */
public class FutureTaskDemo2 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture =CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // int i =10/0;
            System.out.println("supplyAsync......");
            return new Random().toString();
        }).whenCompleteAsync((value,exception)->{
            if(exception == null){
                System.out.println(value);
            }
        }).exceptionally(e ->{
            e.getStackTrace();
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
