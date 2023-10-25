package com;

import com.licretey.MyBatisUtil;
import com.mapper.RoleMapper;
import com.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.jni.User;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


    private void testMyBatisUtil(){
        try (SqlSession sqlSession = MyBatisUtil.openSession()) {
            // 1. 得到UserMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 2. 执行业务代码：各种的增删改查
            User user = mapper.selectById(1);
            System.out.println(user);
        }
    }


    private void testMyBatisUtilByT(){
        MyBatisUtil.select(UserMapper.class, (userMapper -> {
            User user = userMapper.selectById(1);
            System.out.println(user);
        }));
    }
}