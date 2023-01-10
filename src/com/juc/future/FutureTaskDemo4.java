package com.juc.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 李广鹏
 */
public class FutureTaskDemo4 {
    public static void main(String[] args) {

        CompletableFuture<Void> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println();
            return null;
        }).thenRun(() -> {
            System.out.println("thenRun : " + 1);
        });

        CompletableFuture<Void> completableFuture2 = CompletableFuture.supplyAsync(
                () -> 2
        ).thenAccept(i -> {
            System.out.println("thenAccept : " + i);
        });

        CompletableFuture<Integer> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            return 3;
        }).thenApply(i -> {
            System.out.println("thenAccept : " + i);;
            return i;
        });

        System.out.println("主线程！！！");
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.printf(String.valueOf(completableFuture3.join()));
    }
}
