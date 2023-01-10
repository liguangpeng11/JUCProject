package com.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author 李广鹏
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("runAsync");
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("supplyAsync");
            return "supplyAsync";
        });

        System.out.println("result: " + cf1.get());
        System.out.println("result: " + cf2.get());
    }



}
