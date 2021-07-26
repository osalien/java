package com.osalien.java.juc.Synchronized;

import java.util.concurrent.TimeUnit;

/**
 * 死锁演示
 *
 * 死锁原因：
 *      1. 系统资源不足
 *      2. 进程推进顺序不合适
 *      3. 资源分配不当
 *
 * 检查死锁方式：
 *      1. 通过 jps -l 查询PID
 *      2. 使用 jstack PID 获取堆栈信息
 *
 * @author zhaoyuan
 * @date 2021/7/26
 */
public class Demo_DeadLock_01 {

    static Object obj1 = new Object();
    static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+": 已锁定obj1，正在获取obj2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+": 已获取obj2");

                }
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+": 已锁定obj2，正在获取obj1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+": 已获取obj1");

                }
            }
        },"t2").start();
    }

}
