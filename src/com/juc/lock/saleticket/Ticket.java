package com.juc.lock.saleticket;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket {

    //票数量
    private int ticketNumber = 100;

    // 得到Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    public void saleTickct(){

        // 上锁
        lock.lock();

        try {
            if(ticketNumber > 0){
                System.out.println(Thread.currentThread().getName()+" : 卖出："+(ticketNumber--)+" 剩下："+ticketNumber);
            }
        }finally {
            // 解锁
            lock.unlock();
        }
    }

}
