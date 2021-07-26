package com.osalien.java.juc.Collection;

import java.util.*;

/**
 * List集合线程安全解决方案：
 *      1. Vector  比较古老
 *   √  2. Collections  比较古老
 *      3. CopyOnWriteArrayList
 * @author zhaoyuan
 * @date 2021-07-26
 */
public class Demo_SafeCollections_03 {

    public static void main(String[] args) {

        // Collections解决
        List<String> list = Collections.synchronizedList(new ArrayList<>());

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
