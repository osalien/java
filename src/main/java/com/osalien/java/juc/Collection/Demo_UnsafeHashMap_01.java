package com.osalien.java.juc.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * HashMap集合线程不安全案例
 * 解决方案：
 *   √  1.
 * @author zhaoyuan
 * @date 2021-07-26
 */
public class Demo_UnsafeHashMap_01 {

    public static void main(String[] args) {

        //创建HashMap
//        Map<String,String> map = new HashMap<>();

        //解决方案 CopyOnWriteArraySet
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // HashMap添加元素 put方法没有上锁，所以线程不安全，导致读的时候可能会报.ConcurrentModificationException
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                // 打印List
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }

}
