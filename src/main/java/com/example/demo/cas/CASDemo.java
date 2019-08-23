package com.example.demo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/13 下午3:32
 * @Version: 1.0
 */
public class CASDemo {

    /*static AtomicInteger a = new AtomicInteger(0);
    static AtomicInteger b = new AtomicInteger(1);*/
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);


    public static void main(String[] args) throws Exception{

        //解决CAS的ABA问题
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            //停留一秒，保证t2线程拿到的stamp为旧值
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        },"t1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "获取的stamp=" + stamp);
            try {
                //保证t1线程ABA执行完成
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 2019, stamp, atomicStampedReference.getStamp() + 1);
        },"t2").start();





        /*new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.getAndIncrement();
                a.getAndIncrement();
                //a.compareAndSet();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                a.getAndIncrement();
                b.getAndIncrement();
            }
        }).start();*/

        while(Thread.activeCount() > 2){}
        System.out.println(atomicStampedReference.getReference());

        //System.out.println("a=" + a.get() + ",b=" + b.get());
    }

}
