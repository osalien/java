package com.osalien.java.thread.Runnable;

/**
 * @author zhaoyuan
 * @date 2021/7/14
 */
public class Demo_Interrupt_01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(5000);
        myThread.interrupt();
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            while (!isInterrupted()){
                try {
                    Thread.sleep(3000);
                    System.out.println("running……");
//                    interrupt();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }
}
