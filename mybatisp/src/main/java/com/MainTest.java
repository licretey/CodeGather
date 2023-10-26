package com;

import com.licretey.MyBatisUtil;
import com.mapper.RoleMapper;
import com.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.jni.User;

import java.io.Reader;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        new MainTest().testMyBatisUtil();
    }

    private void mybatisCommonUse() throws Exception{
        // 1. 得到SqlSessionFactory
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 2. 得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 3. 得到具体Mapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 4. 执行业务代码：各种的增删改查
            User user = mapper.selectById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
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