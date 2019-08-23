package com.example.demo.mybatis.domain;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午4:32
 * @Version: 1.0
 */
public class User {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
