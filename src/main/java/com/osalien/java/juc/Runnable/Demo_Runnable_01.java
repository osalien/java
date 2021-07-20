package com.osalien.java.juc.Runnable;

public class Demo_Runnable_01 {

    public static void main(String[] args) {
        //4,创建自定义类对象
        Demo_Runnable_01.MyRunnable mr = new Demo_Runnable_01().new MyRunnable();
        //Runnable target = new MyRunnable();
        //5,将其当作参数传递给Thread的构造函数
        Thread t = new Thread(mr);
        //6,开启线程
        t.start();

        for (int i = 0; i < 3000; i++) {
            System.out.println("bb");
        }
    }

    class MyRunnable implements Runnable {       //1,自定义类实现Runnable接口
        @Override
        public void run() {           //2,重写run方法
            for (int i = 0; i < 3000; i++) {       //3,将要执行的代码,写在run方法中
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        }
    }
}
