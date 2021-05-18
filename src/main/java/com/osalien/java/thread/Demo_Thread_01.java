package com.osalien.java.thread;

public class Demo_Thread_01 {

    public static void main(String[] args) {
        //4,创建自定义类的对象
        Demo_Thread_01.MyThread mt = new Demo_Thread_01().new MyThread();
        //5,开启线程
        mt.start();

        for(int i = 0; i < 3000; i++) {
            System.out.println("bb");
        }
    }


    class MyThread extends Thread {  //1,定义类继承Thread
        @Override
        public void run() {  //2,重写run方法
            for (int i = 0; i < 3000; i++) {    //3,将要执行的代码,写在run方法中
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        }
    }
}
