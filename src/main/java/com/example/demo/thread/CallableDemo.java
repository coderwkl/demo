package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/16 下午4:25
 * @Version: 1.0
 */

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call start");
        //throw new RuntimeException("ex");
        int i = 1/0;
        return "haha";
    }
}
public class CallableDemo {

    public static void main(String[] args) {
        try{
            FutureTask<String> futureTask = new FutureTask<>(new MyThread());
            new Thread(futureTask).start();
        }catch (Exception e){
            System.out.println("catch ex");
        }
        Executors.newFixedThreadPool(5);
    }

}
