package com.licretey;

import cn.hutool.core.collection.CollUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;

/**
 * 文档：https://www.toutiao.com/article/7290551894903407119/?app=news_article&timestamp=1698210168&use_new_style=1&req_id=20231025130247F42BF95AD696EE09A697&group_id=7290551894903407119&share_token=7de58915-2c1a-4d99-a999-d9c2736f05bc&source=m_redirect
 */
public class MyBatisUtil {
    private static volatile SqlSessionFactory sqlSessionFactory = null;
    public static final String RESOURCE_FILE_NAME = "mybatis-config.xml";

    public static SqlSessionFactory getSqlSessionFactory() {
        // double check 双重检查, 保证SqlSessionFactory全局只创建一次
        if (sqlSessionFactory == null) {
            synchronized (MyBatisUtil.class) {
                if (sqlSessionFactory == null) {
                    try {
                        Reader reader = Resources.getResourceAsReader(RESOURCE_FILE_NAME);
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSession() {
        return getSqlSessionFactory().openSession();
    }

    public static <T> void select(Class<T> clazz, Consumer<T> consumer) {
        try (SqlSession sqlSession = MyBatisUtil.openSession()) {
            // 1. 得到UserMapper
            T mapper = sqlSession.getMapper(clazz);
            // 2. 执行业务代码：各种的增删改查
            consumer.accept(mapper);
        }
    }
}