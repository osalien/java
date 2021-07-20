package com.osalien.java.juc.Synchronized;

/**
 * 线程间通信 Synchronized实现方式
 * @author zhaoyuan
 * @date 2021/7/19
 */
public class Demo_SignlSync_01 {
    public static void main(String[] args) {
        //3. 创建多线程，调用资源
        Share share = new Share();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"添加线程1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"减少线程2").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"添加线程3").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"减少线程4").start();
    }

}

//1.创建资源类
class Share{
    private int num = 0;

    public synchronized void incr() throws InterruptedException {
        //2. 判断 干活 通知
        while(num != 0){  //使用if会有虚假唤醒问题，所以需要使用while
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() +" :: "+num);
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        //2. 判断 干活 通知
        while(num != 1){  //使用if会有虚假唤醒问题，所以需要使用while
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() +" :: "+num);
        this.notifyAll();
    }
}
