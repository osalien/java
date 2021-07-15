package com.osalien.java.log;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

public class Demo_logger {
    {
        j = 3;
    }

    private static int i = 0;
    private int j = 1;
    private String test = "hello";
    private static final Logger logger = Logger.getLogger("com.osalien.java.log");

    public String test() {
        return i + " " + j;
    }

    {
        j = 2;
    }

    public int test(String txt) {
        return 0;
    }

    {
        j = 3;
    }

    public Demo_logger() {
        i = 100;
    }

    public Demo_logger(String txt) {
        this.test = txt;
    }

    public static void main(String[] args) {
        System.out.println(++Demo_logger.i);
//        test();
        System.out.println();
        LocalDate nowDate = LocalDate.now();
        int month = nowDate.getMonthValue();
        int day = nowDate.getDayOfMonth();
        System.out.println(day);
        nowDate = nowDate.minusDays(day - 1);
        System.out.println(nowDate);
        int week = nowDate.getDayOfWeek().getValue();
        System.out.println("一  二  三  四  五  六  日");
        for (int i = 0; i < week; i++) {
            System.out.print("  ");
        }
        while (nowDate.getMonthValue() == month) {
            if (nowDate.getDayOfMonth() == day)
                System.out.print(nowDate.getDayOfMonth() + "* ");
            else
                System.out.print(nowDate.getDayOfMonth() + "  ");
            nowDate = nowDate.plusDays(1);
            if (nowDate.getDayOfWeek().getValue() == 1)
                System.out.println(" ");
        }
        logger.info("logger");
        System.out.println(LocalDate.of(1999, 5, 2).plusDays(1000));
    }
}
