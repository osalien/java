package com.osalien.java.thread.Callable;
import java.util.concurrent.*;

/**
 * @author zhaoyuan
 * @date 2021/7/13
 */
public class Demo_Callable_02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 10;
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
