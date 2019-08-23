package com.example.demo.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/15 下午4:37
 * @Version: 1.0
 */
public class BlockingQueueDemo {

    //同步队列，不存储元素
    private static BlockingQueue blockingQueue = new SynchronousQueue();

    public static void main(String[] args) {

        new Thread(() -> {
            try{
                System.out.println(Thread.currentThread().getName()+"\tput\t1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\tput\t2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\tput\t3");
                blockingQueue.put("3");
            }catch (Exception e){

            }
        }, "AAA").start();

        new Thread(() -> {
            try{
                TimeUnit.SECONDS.sleep(3);
                Object take = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"\tget"+take);

                TimeUnit.SECONDS.sleep(3);
                take = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"\tget"+take);

                TimeUnit.SECONDS.sleep(3);
                take = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"\tget"+take);
            }catch (Exception e){

            }
        }, "BBB").start();
    }

}
