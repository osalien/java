package com.osalien.java.juc.CollectionThreadSafe;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * List集合线程安全解决方案：
 *   √  1. Vector  比较古老
 *      2. Collections
 *      3. CopyOnWriteArrayList
 * @author zhaoyuan
 * @date 2021-07-26
 */
public class Demo_SafeVector_02 {

    public static void main(String[] args) {

        //Vector解决
        List<String> list = new Vector<>();

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
