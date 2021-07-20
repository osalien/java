package com.osalien.java.juc.Lock;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyuan
 * @date 2021/7/19
 */
@Data
public class Demo_SellTickets_01 {
    private int ticketNum = 100;

    private final ReentrantLock lock = new ReentrantLock();

    public synchronized boolean sell(){
        lock.lock();
        try {
            if(ticketNum <= 0){
                System.out.println(Thread.currentThread().getName()+" : 票已卖完!");
                return false;
            }
            System.out.println(Thread.currentThread().getName()+" : 票"+ticketNum+"号已卖，还剩"+--ticketNum+"张票～");
            return true;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo_SellTickets_01 demo_sellTickets_01 = new Demo_SellTickets_01();
        new Thread(new SellTicket(demo_sellTickets_01),"售票窗口1").start();
        new Thread(new SellTicket(demo_sellTickets_01),"售票窗口2").start();
        new Thread(new SellTicket(demo_sellTickets_01),"售票窗口3").start();
        new Thread(new SellTicket(demo_sellTickets_01),"售票窗口4").start();
    }

}

class SellTicket implements Runnable{

    private Demo_SellTickets_01 demo_sellTickets_01;

    public SellTicket(Demo_SellTickets_01 demo_sellTickets_01){
        this.demo_sellTickets_01 = demo_sellTickets_01;
    }

    @Override
    public void run() {
        while(demo_sellTickets_01.sell()){
            System.out.println(demo_sellTickets_01.getTicketNum());
            System.out.println(demo_sellTickets_01.toString());
        }
    }
}

