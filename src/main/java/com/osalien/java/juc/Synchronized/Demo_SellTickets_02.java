package com.osalien.java.juc.Synchronized;

import lombok.Data;

/**
 * @author zhaoyuan
 * @date 2021/7/19
 */
@Data
public class Demo_SellTickets_02 {
    private int ticketNum = 100;

    public synchronized boolean sell(){
        if(ticketNum <= 0){
            System.out.println(Thread.currentThread().getName()+" : 票已卖完!");
            return false;
        }
        System.out.println(Thread.currentThread().getName()+" : 票"+ticketNum+"号已卖，还剩"+--ticketNum+"张票～");
        return true;
    }

    public static void main(String[] args) {
        Demo_SellTickets_02 demo_sellTickets_02 = new Demo_SellTickets_02();
        new Thread(new SellTicket(demo_sellTickets_02),"售票窗口1").start();
        new Thread(new SellTicket(demo_sellTickets_02),"售票窗口2").start();
        new Thread(new SellTicket(demo_sellTickets_02),"售票窗口3").start();
        new Thread(new SellTicket(demo_sellTickets_02),"售票窗口4").start();
    }

}

class SellTicket implements Runnable{

    private Demo_SellTickets_02 demo_sellTickets_02;

    public SellTicket(Demo_SellTickets_02 demo_sellTickets_02){
        this.demo_sellTickets_02 = demo_sellTickets_02;
    }

    @Override
    public void run() {
        while(demo_sellTickets_02.sell()){
            System.out.println(demo_sellTickets_02.getTicketNum());
            System.out.println(demo_sellTickets_02.toString());
        }
    }
}