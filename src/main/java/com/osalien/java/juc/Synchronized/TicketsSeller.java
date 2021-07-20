package com.osalien.java.juc.Synchronized;

public class TicketsSeller extends Thread {
    private static int tickets = 100;
    static Object obj = new Object();

    public TicketsSeller() {
        super();

    }

    public TicketsSeller(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {

            synchronized (obj) {
                if (tickets <= 0)
                    break;
                try {
                    Thread.sleep(100);//线程1睡,线程2睡,线程3睡,线程4睡
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                System.out.println(getName() + "...这是第" + tickets-- + "号票");
            }
        }
    }
}
