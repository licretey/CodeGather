package com.cache.bean.impl;

import com.cache.bean.User;
import com.cache.bean.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"user_cache"})
public class UserServiceImpl implements UserService {

    /**
     * 执行流程：
     *      先看缓存里有没有，
     *          有则直接从缓存中获取后返回
     *          没有则执行方法返回结果，并将结果放入缓存
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#id")
    public User getById(Long id) {
        System.out.println("模拟查询数据库");
        User user = new User();
        user.setId(id);
        user.setName("licretey3, id "+id);
        return user;
    }


    @Override
    @Cacheable
    public User getUser(User queryParam, int[] arr, String abc) {
        System.out.println("模拟查询数据库，测试自定义keygenerator");
        User user = new User();
        user.setId(111L);
        user.setName("licretey111");
        return user;
    }
}
