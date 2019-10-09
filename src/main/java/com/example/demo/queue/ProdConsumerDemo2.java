package com.example.demo.queue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/9/20 下午1:45
 * @Version: 1.0
 */
public class ProdConsumerDemo2 {

    private AtomicInteger num = new AtomicInteger(0);

    public void product(){
        if(num.get() >= 0){
            num.incrementAndGet();
            System.out.println(Thread.currentThread().getName()+"\t product "+num.get());
        }

    }

    public void consumer(){
        if(num.get() > 0){
            num.decrementAndGet();
            System.out.println(Thread.currentThread().getName()+"\t consume "+num.get());
        }
    }

    public static void main(String[] args) {
        ProdConsumerDemo2 demo = new ProdConsumerDemo2();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.product();
            }
        }, "AAA1").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.consumer();
            }
        }, "BBB1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.product();
            }
        }, "AAA2").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.consumer();
            }
        }, "BBB2").start();
    }
}
