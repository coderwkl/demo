package com.example.demo.gc;

import java.lang.ref.SoftReference;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/20 上午10:19
 * @Version: 1.0
 */
public class SoftReferenceDemo {

    public static void softRefMemoryNotEnough(){

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        try{
            //byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }

    public static void main(String[] args) {
        softRefMemoryNotEnough();
    }
}
