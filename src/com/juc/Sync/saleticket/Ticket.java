package com.juc.Sync.saleticket;

public class Ticket {

    private int ticketNumber = 100;

    public synchronized void saleTickct(){
        if(ticketNumber > 0){
            System.out.println(Thread.currentThread().getName()+" : 卖出："+(ticketNumber--)+" 剩下："+ticketNumber);
        }
    }

}
