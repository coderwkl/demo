package com.example.demo.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午3:27
 * @Version: 1.0
 */
public class SqlSession {

    /**
     * 获取mapper代理
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getMapper(Class<T> clazz) throws Exception{
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandlerMybatis(clazz));
    }
}
