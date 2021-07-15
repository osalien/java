package com.osalien.java.thread.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试加读锁
 * 会读到写的中间值。
 * @author zhaoyuan
 * @date 2021-07-15
 */
public class Demo_ReadWriteLock_02 {

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void test(TestMethods_02 testMethods) {
        ExecutorService pools = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            pools.execute(new Runnable() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    try {
                        long num = testMethods.get();
                        if(num > TestMethods.MAX_VALUE){
                            System.out.println(Thread.currentThread().getName()+"-取到错误值");
                        }
                        System.out.println(Thread.currentThread().getName()+"-read值："+num);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.readLock().unlock();
                    }
                }
            });

        }

        for (int i = 0; i < 20; i++) {
            pools.execute(new Runnable() {
                @Override
                public void run() {
//                    lock.writeLock().lock();
                    try {
                        testMethods.put();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
//                        lock.writeLock().unlock();
                    }
                }
            });

        }
    }
}

class TestMethods_02{
    public static final long MAX_VALUE = 3;

    private long num;

    public long get() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-read ready...");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"-read end...");
        return num;
    }

    public void put() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-write ready...");
        num++;
        Thread.sleep(10);
        if(num>MAX_VALUE){
            num = 0;
        }
        System.out.println(Thread.currentThread().getName()+"-write end..."+ num);
    }

    public static void main(String[] args) {
        TestMethods_02 testMethods = new TestMethods_02();
        Demo_ReadWriteLock_02.test(testMethods);
    }
}


//result:
//        pool-1-thread-1-read ready...
//        pool-1-thread-2-read ready...
//        pool-1-thread-3-read ready...
//        pool-1-thread-4-read ready...
//        pool-1-thread-5-read ready...
//        pool-1-thread-6-read ready...
//        pool-1-thread-7-read ready...
//        pool-1-thread-8-read ready...
//        pool-1-thread-9-read ready...
//        pool-1-thread-10-read ready...
//        pool-1-thread-11-read ready...
//        pool-1-thread-12-read ready...
//        pool-1-thread-13-read ready...
//        pool-1-thread-14-read ready...
//        pool-1-thread-15-read ready...
//        pool-1-thread-16-read ready...
//        pool-1-thread-17-read ready...
//        pool-1-thread-18-read ready...
//        pool-1-thread-19-read ready...
//        pool-1-thread-20-read ready...
//        pool-1-thread-1-read end...
//        pool-1-thread-1-read值：0
//        pool-1-thread-1-write ready...
//        pool-1-thread-4-read end...
//        pool-1-thread-4-read值：1
//        pool-1-thread-4-write ready...
//        pool-1-thread-3-read end...
//        pool-1-thread-3-read值：2
//        pool-1-thread-2-read end...
//        pool-1-thread-3-write ready...
//        pool-1-thread-5-read end...
//        pool-1-thread-5-read值：3
//        pool-1-thread-6-read end...
//        pool-1-thread-6-read值：3
//        pool-1-thread-6-write ready...
//        pool-1-thread-2-read值：2
//        pool-1-thread-2-write ready...
//        pool-1-thread-5-write ready...
//        pool-1-thread-8-read end...
//        pool-1-thread-7-read end...
//        pool-1-thread-7-取到错误值
//        pool-1-thread-8-取到错误值
//        pool-1-thread-7-read值：6
//        pool-1-thread-8-read值：6
//        pool-1-thread-7-write ready...
//        pool-1-thread-8-write ready...
//        pool-1-thread-9-read end...
//        pool-1-thread-9-取到错误值
//        pool-1-thread-10-read end...
//        pool-1-thread-10-取到错误值
//        pool-1-thread-10-read值：8
//        pool-1-thread-10-write ready...
//        pool-1-thread-9-read值：8
//        pool-1-thread-9-write ready...
//        pool-1-thread-11-read end...
//        pool-1-thread-11-取到错误值
//        pool-1-thread-11-read值：10
//        pool-1-thread-11-write ready...
//        pool-1-thread-12-read end...
//        pool-1-thread-13-read end...
//        pool-1-thread-13-取到错误值
//        pool-1-thread-13-read值：11
//        pool-1-thread-13-write ready...
//        pool-1-thread-12-取到错误值
//        pool-1-thread-12-read值：12
//        pool-1-thread-12-write ready...
//        pool-1-thread-14-read end...
//        pool-1-thread-16-read end...
//        pool-1-thread-16-取到错误值
//        pool-1-thread-16-read值：13
//        pool-1-thread-16-write ready...
//        pool-1-thread-15-read end...
//        pool-1-thread-15-取到错误值
//        pool-1-thread-15-read值：14
//        pool-1-thread-15-write ready...
//        pool-1-thread-14-取到错误值
//        pool-1-thread-14-read值：13
//        pool-1-thread-14-write ready...
//        pool-1-thread-18-read end...
//        pool-1-thread-19-read end...
//        pool-1-thread-19-取到错误值
//        pool-1-thread-17-read end...
//        pool-1-thread-17-取到错误值
//        pool-1-thread-19-read值：16
//        pool-1-thread-20-read end...
//        pool-1-thread-20-取到错误值
//        pool-1-thread-20-read值：16
//        pool-1-thread-20-write ready...
//        pool-1-thread-18-取到错误值
//        pool-1-thread-18-read值：16
//        pool-1-thread-18-write ready...
//        pool-1-thread-19-write ready...
//        pool-1-thread-17-read值：16
//        pool-1-thread-17-write ready...
//        pool-1-thread-1-write end...0
//        pool-1-thread-4-write end...0
//        pool-1-thread-3-write end...0
//        pool-1-thread-2-write end...0
//        pool-1-thread-6-write end...0
//        pool-1-thread-5-write end...0
//        pool-1-thread-8-write end...0
//        pool-1-thread-7-write end...0
//        pool-1-thread-10-write end...0
//        pool-1-thread-9-write end...0
//        pool-1-thread-11-write end...0
//        pool-1-thread-12-write end...0
//        pool-1-thread-13-write end...0
//        pool-1-thread-14-write end...0
//        pool-1-thread-15-write end...0
//        pool-1-thread-16-write end...0
//        pool-1-thread-17-write end...0
//        pool-1-thread-19-write end...0
//        pool-1-thread-18-write end...0
//        pool-1-thread-20-write end...0

