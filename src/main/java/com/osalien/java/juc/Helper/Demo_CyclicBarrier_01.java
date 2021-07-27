package com.osalien.java.juc.Helper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 *  juc三大辅助类之     CyclicBarrier 循环栅栏
 *      可等待n个线程执行完在执行另一个线程
 *      每完成一个线程使用await()方法，完成一个加一，直到n
 *
 *  案例： 集齐七颗龙珠，然后召唤神龙
 * @author zhaoyuan
 * @date 2021-07-27
 */
public class Demo_CyclicBarrier_01 {
    public static void main(String[] args) {

        // 创建循环栅栏 CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("召唤神龙！");
        });
        //创建6个线程
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"星龙珠已找到~");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();
        }
    }
}
