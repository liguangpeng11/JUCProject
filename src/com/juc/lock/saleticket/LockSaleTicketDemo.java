package com.juc.lock.saleticket;


public class LockSaleTicketDemo {

    public static void main(String[] args) {

        //创建Ticket对象
        Ticket ticket = new Ticket();

        // 创建五个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=100;i++){
                    ticket.saleTickct();
                }
            }
        }, "juzhikai").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=100;i++){
                    ticket.saleTickct();
                }
            }
        }, "limin").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=100;i++){
                    ticket.saleTickct();
                }
            }
        }, "tuxinyi").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=100;i++){
                    ticket.saleTickct();
                }
            }
        }, "chengli").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<=100;i++){
                    ticket.saleTickct();
                }
            }
        }, "tianyu").start();

    }

}
