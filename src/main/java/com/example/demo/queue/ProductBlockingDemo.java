package com.example.demo.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 阻塞队列实现生产消费
 * @Author: wukunlin
 * @CreateDate: 2019/8/16 下午12:00
 * @Version: 1.0
 */

class Resource{

    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void product(){
        String data;
        boolean res;
        try{
            while(flag){
                data = atomicInteger.getAndIncrement()+"";
                res = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if(res){
                    System.out.println(Thread.currentThread().getName()+"\t 插入数据"+ data +"成功");
                }else{
                    System.out.println(Thread.currentThread().getName()+"\t 插入数据"+ data +"失败");
                }
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println(Thread.currentThread().getName()+"\t stop product data");
        }catch (Exception e){

        }
    }

    public void consume(){
        String data;
        try{
            while (flag){
                data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(null == data || "".equals(data)){
                    flag = false;
                    System.out.println(Thread.currentThread().getName()+"\t 2s没有取到数据 quit");
                    return;
                }
                System.out.println(Thread.currentThread().getName()+"\t 消费数据"+ data +"成功");
            }
        }catch (Exception e){

        }
    }

    public void stop(){
        flag = false;
    }
}

public class ProductBlockingDemo {

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        resource.setBlockingQueue(new ArrayBlockingQueue<String>(3));
        new Thread(() -> {
            resource.product();
        }, "AA").start();

        new Thread(() -> {
            resource.consume();
        }, "BB").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println();
        System.out.println("时间到，停止活动");
        resource.stop();
    }

}
