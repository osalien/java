package com.osalien.java.juc.Synchronized;

public class Demo_Synchronized_01 {
    /**
     * @param args 需求:铁路售票,一共100张,通过四个窗口卖完.
     */
    public static void main(String[] args) {
        TicketsSeller t1 = new TicketsSeller();
        TicketsSeller t2 = new TicketsSeller();
        TicketsSeller t3 = new TicketsSeller();
        TicketsSeller t4 = new TicketsSeller();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t4.setName("窗口4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
