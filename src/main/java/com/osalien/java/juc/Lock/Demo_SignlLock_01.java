package com.osalien.java.juc.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信 Lock实现方式
 * @author zhaoyuanr
 * @date 2021/7/19
 */
public class Demo_SignlLock_01 {
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
        },"添加线程").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"减少线程").start();
    }

}

//1.创建资源类
class Share{
    private int num = 0;

    private final Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();
        try{
            //2. 判断 干活 通知
            while(num != 0){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() +" :: "+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        lock.lock();
        try{
            //2. 判断 干活 通知
            while(num != 1){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() +" :: "+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
