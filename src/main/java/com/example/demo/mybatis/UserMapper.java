package com.example.demo.mybatis;

import com.example.demo.mybatis.annotation.WInsert;
import com.example.demo.mybatis.annotation.WParam;
import com.example.demo.mybatis.annotation.WSelect;
import com.example.demo.mybatis.domain.User;

import java.util.List;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午3:58
 * @Version: 1.0
 */
public interface UserMapper {

    @WInsert("insert into test(id) values(#{id})")
    int insertUser(@WParam("id") int id);

    @WSelect("select * from test")
    List<User> selectUser();
}
