package com.example.demo.SerializableTest;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/9/26 下午2:12
 * @Version: 1.0
 */
public class SerializableTest {

    /**
     * list的element元素数组被transient修饰，不参与序列化
     * 但是输出结果仍然有值
     * 关键在于writeObject和readObject方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        List<String> stringList = new ArrayList<String>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hollis");
        stringList.add("chuang");
        System.out.println("init StringList" + stringList);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
        objectOutputStream.writeObject(stringList);

        objectOutputStream.close();
        File file = new File("stringlist");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        List<String> newStringList = (List<String>)objectInputStream.readObject();
        objectInputStream.close();
        if(file.exists()){
            file.delete();
        }
        System.out.println("new StringList" + newStringList);
    }
}
