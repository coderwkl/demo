package com.example.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 自旋锁
 * @Author: wukunlin
 * @CreateDate: 2019/8/15 上午10:31
 * @Version: 1.0
 */
public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while(!atomicReference.compareAndSet(null, thread)){}
        System.out.println(Thread.currentThread().getName()+"\t 更新值成功");
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoke unLock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        }, "AA").start();

        new Thread(() -> {
            //保证AA线程获得锁
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.lock();
            spinLockDemo.unLock();
        }, "BB").start();

        while(Thread.activeCount() > 2){}
    }

}
