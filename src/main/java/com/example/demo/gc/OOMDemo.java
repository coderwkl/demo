package com.example.demo.gc;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/19 下午4:58
 * @Version: 1.0
 */
public class OOMDemo {

    byte[] bytes = new byte[50 * 1024 * 1024];

    public static void main(String[] args) {

        OOMDemo oomDemo = new OOMDemo();

    }
}
