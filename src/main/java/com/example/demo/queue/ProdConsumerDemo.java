package com.example.demo.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/15 下午6:07
 * @Version: 1.0
 */
public class ProdConsumerDemo {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int num = 0;

    public void product(){
        try{
            lock.lock();
            while(num != 0){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"\t product "+num);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void consumer(){
        try{
            lock.lock();
            while(num == 0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"\t consume "+num);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProdConsumerDemo demo = new ProdConsumerDemo();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.product();
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.consumer();
            }
        }, "BBB").start();
    }
}
