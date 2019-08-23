package com.example.demo.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午3:44
 * @Version: 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface WParam {
    String value();
}
