package com.osalien.java.juc.Synchronized;

/**
 * 可重入锁
 * @author zhaoyuan
 * @date 2021/7/26
 */
public class Demo_RLock_01 {
    public synchronized void add(){
        System.out.println("1");
        addTwo();
    }
    public synchronized void addTwo(){
        System.out.println("2");
        addTwo();
    }
    public static void main(String[] args) {
        Demo_RLock_01 demo_rLock_01 = new Demo_RLock_01();
        new Thread(() -> {
            demo_rLock_01.add();
        }).start();
    }
}
