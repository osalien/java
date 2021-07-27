package com.osalien.java.juc.Helper;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *  juc三大辅助类之     Semaphore 信号量
 *      类似于同步锁，只不过可同时n个线程允许
 *
 *  案例： 6辆车，三个停车位
 * @author zhaoyuan
 * @date 2021-07-27
 */
public class Demo_Semaphore_01 {
    public static void main(String[] args) {

        // 创建信号量 Semaphore
        Semaphore semaphore = new Semaphore(3);
        //创建6个线程
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                //获取车位
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"号车停入车位~");
                    TimeUnit.SECONDS.sleep(Integer.valueOf(Thread.currentThread().getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //释放车位
                    System.out.println(Thread.currentThread().getName()+"号车驶出车位~");
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
