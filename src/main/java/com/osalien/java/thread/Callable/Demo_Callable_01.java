package com.osalien.java.thread.Callable;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程Callable实现的两种方式-线程池以及new Thread
 * @author zhaoyuan
 * @date 2021/7/13
 */
public class Demo_Callable_01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo_Callable_01 demo_callable_01 = new Demo_Callable_01();
        demo_callable_01.test1();
        demo_callable_01.test2();
    }

    /**
     * 线程直接使用new Thread来创建
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void test2() throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable(-1);
        FutureTask futureTask = new FutureTask(myCallable);
        new Thread(futureTask).start();
//        futureTask.run(); //在主线程
        System.out.println(Thread.currentThread().getName()+"-"+futureTask.get());
    }

    /**
     * 使用Executors.newFixedThreadPool创建线程池
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void test1() throws ExecutionException, InterruptedException {
        int taskSize = 10;
        ExecutorService pools = Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            Callable callable = new MyCallable(i);
            Future f = pools.submit(callable);
            list.add(f);
        }
        pools.shutdown();
        for (int i = 0; i < list.size(); i++) {
            Future f1 = list.get(i);
            System.out.println(Thread.currentThread().getName()+"-"+"res: " + f1.get().toString());

        }
    }

    //实现Callable接口
    class MyCallable implements Callable{
        private int taskNum;
        public MyCallable(int taskNum){
            this.taskNum = taskNum;
        }
        @Override
        public Object call() throws Exception {
//            if(taskNum==5){ Thread.sleep(1000); }
            System.out.println("======="+Thread.currentThread().getName()+"-"+taskNum+"=======");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("taskNum","Thread-"+taskNum);
            return jsonObject;
        }
    }
}
