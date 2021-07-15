package com.osalien.java.thread.Executors;
import java.util.concurrent.*;

/**
 * @author zhaoyuan
 * @date 2021/7/13
 */
public class Demo_NewFixedThreadPool_01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 10;
        //可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程
        ExecutorService pools = Executors.newFixedThreadPool(taskSize);
        while (true){
            //提交多个线程任务，并执行
            pools.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" is running…… ");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
