package com.osalien.java.juc.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间定制化通信
 * @author zhaoyuan
 * @date 2021/7/20
 */

// 1.创建资源类
class ShareResource{
    // 定义标识位  1 A线程、 2 B线程、 3 C线程
    private int flag = 1;
    //定义锁
    private final Lock lock = new ReentrantLock();
    //定义Condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印五次
    public void print5(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            //判断 while
            while( flag != 1 ){
                c1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+(i+1)+" :轮数: "+ loopNum);
            }
            //改变标识位，并通知B线程干活
            flag = 2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }

    //打印十次
    public void print10(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            //判断 while
            while( flag != 2 ){
                c2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+(i+1)+" :轮数: "+ loopNum);
            }
            //改变标识位，并通知C线程干活
            flag = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }

    //打印十五次
    public void print15(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            //判断 while
            while( flag != 3 ){
                c3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+(i+1)+" :轮数: "+ loopNum);
            }
            //改变标识位，并通知B线程干活
            flag = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
}

public class Demo_CustomizedSignlLock_01 {

    //创建多个线程，调用资源类方法
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5(i+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10(i+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15(i+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }


}
