package com.example.demo.mybatis;

import com.example.demo.mybatis.domain.User;

import java.util.List;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午4:01
 * @Version: 1.0
 */
public class ExecutorMain {

    public static void main(String[] args) {
        try {
            UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
            //userMapper.insertUser(100);
            List<User> user = userMapper.selectUser();
            System.out.println(user);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
