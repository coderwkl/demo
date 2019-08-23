package com.example.demo.mybatis;

import com.example.demo.mybatis.annotation.WInsert;
import com.example.demo.mybatis.annotation.WParam;
import com.example.demo.mybatis.annotation.WSelect;
import com.example.demo.mybatis.util.JDBCUtils;
import com.example.demo.mybatis.util.SQLUtils;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午3:30
 * @Version: 1.0
 */
public class InvocationHandlerMybatis implements InvocationHandler {

    private Object proxy;
    public InvocationHandlerMybatis(Object proxy){
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(null != method.getAnnotation(WInsert.class)){
            return insertSQL(method, args);
        }
        if(null != method.getAnnotation(WSelect.class)){
            return selectSQL(method, args);
        }
        return null;
    }

    public int insertSQL(Method method, Object[] args) {
        // 获取注解上的sql
        String insertSql = method.getAnnotation(WInsert.class).value();
        System.out.println("sql:" + insertSql);
        // 获取方法上的参数
        Parameter[] parameters = method.getParameters();
        // 将方法上的参数存放在Map集合中
        ConcurrentHashMap<Object, Object> parameterMap = getExtParams(parameters, args);
        // 获取SQL语句上需要传递的参数
        String[] sqlParameter = SQLUtils.sqlInsertParameter(insertSql);
        List<Object> parameValues = new ArrayList<>();
        for (int i = 0; i < sqlParameter.length; i++) {
            String str = sqlParameter[i];
            Object object = parameterMap.get(str);
            parameValues.add(object);
        }
        // 将SQL语句替换为？号
        String newSql = SQLUtils.parameQuestion(insertSql, sqlParameter);
        System.out.println("newSql:" + newSql);
        // 调用jdbc代码执行
        int insertResult = JDBCUtils.insert(newSql, false, parameValues);
        return insertResult;
    }

    public Object selectSQL(Method method, Object[] args) throws SQLException {
        try {
            // 获取查询SQL语句
            String selectSQL = method.getAnnotation(WSelect.class).value();
            // 将方法上的参数存放在Map集合中
            Parameter[] parameters = method.getParameters();
            // 获取方法上参数集合
            ConcurrentHashMap<Object, Object> parameterMap = getExtParams(parameters, args);
            // 获取SQL传递参数
            List<String> sqlSelectParameter = SQLUtils.sqlSelectParameter(selectSQL);
            // 排序参数
            List<Object> parameValues = new ArrayList<>();
            for (int i = 0; i < sqlSelectParameter.size(); i++) {
                String parameterName = sqlSelectParameter.get(i);
                Object object = parameterMap.get(parameterName);
                parameValues.add(object.toString());
            }
            // 变为?号
            String newSql = SQLUtils.parameQuestion(selectSQL, sqlSelectParameter);
            System.out.println("执行SQL:" + newSql + "参数信息:" + parameValues.toString());
            // 调用JDBC代码查询
            ResultSet rs = JDBCUtils.query(newSql, parameValues);
            // 获取返回类型
            Class<?> returnType = method.getReturnType();
            if (!rs.next()) {
                // 没有查找数据
                return null;
            }
            // 向上移动
            rs.previous();
            // 实例化对象
            Object newInstance = null;
            List<Object> resultList = null;
            while (rs.next()) {
                if(returnType == List.class){
                    Type type = method.getGenericReturnType();
                    Class<?> clazz = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
                    newInstance = clazz.newInstance();
                    if(null == resultList){
                        resultList = new ArrayList<>();
                    }
                }else{
                    newInstance = returnType.newInstance();
                }
                //sqlSelectParameter
                for (Field field : newInstance.getClass().getDeclaredFields()) {
                    String parameterName = field.getName();
                    // 获取集合中数据
                    Object value = rs.getObject(parameterName);
                    // 查找对应属性
                    // 设置允许私有访问
                    field.setAccessible(true);
                    // 赋值参数
                    field.set(newInstance, value);
                }
                if(null != resultList){
                    resultList.add(newInstance);
                }
            }
            return null == resultList ? newInstance : resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ConcurrentHashMap<Object, Object> getExtParams(Parameter[] parameters, Object[] args) {
        // 获取方法上参数集合
        ConcurrentHashMap<Object, Object> parameterMap = new ConcurrentHashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            // 参数信息
            Parameter parameter = parameters[i];
            WParam extParam = parameter.getDeclaredAnnotation(WParam.class);
            // 参数名称
            String paramValue = extParam.value();
            // 参数值
            Object oj = args[i];
            parameterMap.put(paramValue, oj);
        }
        return parameterMap;
    }
}
