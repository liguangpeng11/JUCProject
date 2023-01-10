package com.juc.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 李广鹏
 */
public class FutureTaskDemo6 {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 =CompletableFuture.supplyAsync(()->10);
        CompletableFuture<Integer> cf2 =CompletableFuture.supplyAsync(()->21);
        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2,(x,y)->x+y);
        System.out.printf(String.valueOf(cf3.join()));
    }
}
