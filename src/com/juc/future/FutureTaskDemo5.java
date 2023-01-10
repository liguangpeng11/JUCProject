package com.juc.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 李广鹏
 */
public class FutureTaskDemo5 {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println("cf1");
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "cf1";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println("cf2");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "cf2";
        });

        CompletableFuture<String> cf3 = cf1.applyToEither(cf2,f-> f + " is winner");
        System.out.printf(cf3.join());
    }
}
