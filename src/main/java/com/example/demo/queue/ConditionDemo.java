package com.example.demo.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/16 上午10:52
 * @Version: 1.0
 */
public class ConditionDemo {

    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();


    public void threadA(){
        try{
            lock.lock();
            while(num != 1){
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t this is Thread A");
            }
            num = 2;
            conditionB.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void threadB(){
        try{
            lock.lock();
            while(num != 2){
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t this is Thread B");
            }
            num = 3;
            conditionC.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void threadC(){
        try{
            lock.lock();
            while(num != 3){
                conditionC.await();
            }

            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t this is Thread C");
            }
            num = 1;
            conditionA.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ConditionDemo demo = new ConditionDemo();

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                demo.threadB();
            }, "BB").start();
            new Thread(() -> {
                demo.threadA();
            }, "AA").start();

            new Thread(() -> {
                demo.threadC();
            }, "CC").start();
        }

    }
}
