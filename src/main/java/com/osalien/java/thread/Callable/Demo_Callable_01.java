package com.osalien.java.thread.Callable;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhaoyuan
 * @date 2021/7/13
 */
public class Demo_Callable_01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
            System.out.println("res: " + f1.get().toString());

        }
    }
    static class MyCallable implements Callable{
        private int taskNum;
        public MyCallable(int taskNum){
            this.taskNum = taskNum;
        }
        @Override
        public Object call() throws Exception {
//            if(taskNum==5){ Thread.sleep(1000); }
            System.out.println("=======Thread-"+taskNum+"=======");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("taskNum","Thread-"+taskNum);
            return jsonObject;
        }
    }
}
