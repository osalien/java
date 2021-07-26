package com.osalien.java.juc.CollectionThreadSafe;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List集合线程安全解决方案：
 *      1. Vector  比较古老
 *      2. Collections  比较古老
 *   √  3. CopyOnWriteArrayList  常用
 * @author zhaoyuan
 * @date 2021-07-26
 */
public class Demo_SafeCopyOnWriteArrayList_04 {

    public static void main(String[] args) {

        // CopyOnWriteArrayList解决
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // List添加元素 add方法没有上锁，所以线程不安全，导致读的时候可能会报.ConcurrentModificationException
                list.add(UUID.randomUUID().toString().substring(0,8));
                // 打印List
                System.out.println(Thread.currentThread().getName() +"::"+ list);
            },String.valueOf(i)).start();
        }

    }

}
