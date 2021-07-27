package com.osalien.java.juc.Helper;

import java.util.concurrent.CountDownLatch;

/**
 *  juc三大辅助类之     CountDownLatch 计数器
 *      可等待n个线程执行完在执行主线程
 *      每完成一个线程使用countDown()方法-1
 *      主线程使用await()方法等待计数器归零，然后在继续执行
 *
 *  案例： 同学都走了，班长才锁门
 * @author zhaoyuan
 * @date 2021-07-27
 */
public class Demo_CountDownLatch_01 {
    public static void main(String[] args) throws InterruptedException {

        // 创建计数器 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //创建6个线程
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"号同学离开教室~");

                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println("班长锁门！");

    }
}
