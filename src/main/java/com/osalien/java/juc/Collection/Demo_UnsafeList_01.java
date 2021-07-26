package com.osalien.java.juc.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * List集合线程不安全案例
 * 解决方案：
 *      1. Vector
 *      2. Collections
 *      3. CopyOnWriteArrayList
 * @author zhaoyuan
 * @date 2021-07-26
 */
public class Demo_UnsafeList_01 {

    public static void main(String[] args) {

        //创建ArrayList
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // List添加元素 add方法没有上锁，所以线程不安全，导致读的时候可能会报.ConcurrentModificationException
                list.add(UUID.randomUUID().toString().substring(0,8));
                // 打印List
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
