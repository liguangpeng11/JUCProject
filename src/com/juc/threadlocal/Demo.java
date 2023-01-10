package com.juc.threadlocal;

import java.util.concurrent.*;

/**
 * @author 李广鹏
 */
public class Demo {

    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void addByThreadLocal() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public static void main(String[] args) throws InterruptedException {

        Demo demo = new Demo();

        ExecutorService threadPool = new ThreadPoolExecutor(3, 3, 2L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for (int i = 1; i <= 6; i++) {
                // 执行
                threadPool.execute(() -> {
                    try {
                        for (int j = 1; j <= 5; j++) {
                            demo.addByThreadLocal();
                        }
                        System.out.println(Thread.currentThread().getName() + " : " + demo.threadLocal.get());
                    } finally {
                        demo.threadLocal.remove();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + demo.threadLocal.get());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭
            threadPool.shutdown();
        }

        TimeUnit.SECONDS.sleep(1);
    }
}
