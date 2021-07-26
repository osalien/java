package com.osalien.java.juc.Collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * HashSet集合线程不安全案例
 * 解决方案：
 *   √  1. CopyOnWriteArraySet
 * @author zhaoyuan
 * @date 2021-07-26
 */
public class Demo_UnsafeHashSet_01 {

    public static void main(String[] args) {

        //创建HashSet
//        Set<String> set = new HashSet<>();

        //解决方案 CopyOnWriteArraySet
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // HashSet添加元素 add方法没有上锁，所以线程不安全，导致读的时候可能会报.ConcurrentModificationException
                set.add(UUID.randomUUID().toString().substring(0,8));
                // 打印List
                System.out.println(set);
            },String.valueOf(i)).start();
        }

    }

}
