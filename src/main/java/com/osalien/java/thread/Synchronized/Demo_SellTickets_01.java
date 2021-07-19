package com.osalien.java.thread.Synchronized;

/**
 * @author zhaoyuan
 * @date 2021-07-19
 */
public class Demo_SellTickets_01 {
    public static void main(String[] args) {
        Thread window1 = new SellTickets();
    }

    static class SellTickets extends Thread{
        private int ticketNum = 100;
        @Override
        public void run(){
            synchronized (this){
                ticketNum--;
                System.out.println(Thread.currentThread().getName() + "正在卖票~ 剩余票数："+ ticketNum);
            }
        }
    }
}
