package com.example.demo.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/28 下午6:43
 * @Version: 1.0
 */
public class JoinDemo {

    private static int num = 1;

    public void threadA(){
        try{
            while(num != 1){
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t this is Thread A");
            }
            num = 2;
        }catch (Exception e){

        }finally {
        }
    }

    public void threadB(){
        try{
            while(num != 2){
                wait();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t this is Thread B");
            }
            num = 3;
        }catch (Exception e){

        }finally {
        }
    }

    public void threadC(){
        try{
            while(num != 3){
                wait();
            }

            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t this is Thread C");
            }
            num = 1;
        }catch (Exception e){

        }finally {
        }
    }

    public static void main(String[] args) throws Exception{

        ConditionDemo demo = new ConditionDemo();

        for (int i = 0; i < 10; i++) {

            Thread AA = new Thread(() -> {
                demo.threadB();
            }, "BB");
            AA.start();
            Thread BB = new Thread(() -> {
                demo.threadA();
            }, "AA");
            BB.start();
            Thread CC = new Thread(() -> {
                demo.threadC();
            }, "CC");
            CC.start();

            if(num == 2){
                BB.join();
            }
            if(num == 3){
                CC.join();
            }
        }

    }

}
