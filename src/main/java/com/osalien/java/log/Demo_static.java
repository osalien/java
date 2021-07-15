package com.osalien.java.log;

import java.lang.reflect.Field;

public class Demo_static {
    private String txt;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Demo_logger demo_loggers = new Demo_logger("123");
        String txt = "txt";
        Class c = demo_loggers.getClass();
        Field f = c.getDeclaredField("test");
        f.setAccessible(true);
        System.out.println(f.get(demo_loggers));
        System.out.println(demo_loggers.test());
        Demo_logger demo_logger = new Demo_logger("sas");
        System.out.println(demo_logger.test());
        Integer i = 1;
        update(i);
        System.out.println(i);
    }

    private static void update(Integer i) {
        i = i + 2;
    }
//    private void test(){
//        System.out.println(Demo_logger.i);
//    }
}
