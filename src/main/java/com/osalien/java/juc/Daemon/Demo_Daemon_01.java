package com.osalien.java.juc.Daemon;

/**
 * 主线程结束，用户线程还没结束，jvm存活
 * 没有用户线程，只有守护线程，jvm结束
 * @author zhaoyuan
 * @date 2021-07-19
 */
public class Demo_Daemon_01 {
    public static void main(String[] args) {

        isNotDaemon();
        isDaemon();

        System.out.println(Thread.currentThread().getName()+":: 3");
    }

    private static void isNotDaemon() {
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"::"+Thread.currentThread().isDaemon());
            while(true){}
        },"isNotDaemon");
        thread.start();

        System.out.println(Thread.currentThread().getName()+":: 1");

        thread.stop();
    }

    private static void isDaemon() {
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"::"+Thread.currentThread().isDaemon());
            while(true){}
        },"isDaemon");
        thread.setDaemon(true);
        thread.start();

        System.out.println(Thread.currentThread().getName()+":: 2");
    }
}
