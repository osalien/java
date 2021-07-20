package com.osalien.java.juc.Synchronized;

/**
 * @author zhaoyuan
 * @date 2021-07-19
 */
public class Demo_SellTickets_01 {
    public static void main(String[] args) {

        new Thread(new SellTickets(),"窗口一").start();
        new Thread(new SellTickets(),"窗口二").start();
        new Thread(new SellTickets(),"窗口三").start();
        new Thread(new SellTickets(),"窗口四").start();

    }


}

class SellTickets implements Runnable{
    private static int ticketNum = 100;
    static Object obj = new Object();
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj){
                if(ticketNum==0) {
                    System.out.println(Thread.currentThread().getName() + ": 票已卖完！");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "正在卖"+ticketNum+"号票~ 剩余票数："+ --ticketNum);
            }
        }

    }
}
