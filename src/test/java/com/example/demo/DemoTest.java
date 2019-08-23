package com.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/7/30 上午11:33
 * @Version: 1.0
 */
public class DemoTest {

    @Test
    public void test1(){
        int update = 0;
        do{
            System.out.println("aaa");
        }while(update != 1);
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>(1);
        for (int j = 0; j < 3; j++) {
            new Thread(() -> {
                //UUID.randomUUID().toString().substring(1,8)
                //elementData[size++] = e;
                //elementData[size] = e;
                //size++;
                if(System.currentTimeMillis()%2L == 0){
                    list.add(String.valueOf(1));
                }else{
                    list.add(String.valueOf(2));
                }
                System.out.println(list);
            }).start();
        }
        while(Thread.activeCount() > 2){}
    }
}
