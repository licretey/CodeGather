package com.cache.bean;

public interface UserService {
    User getById(Long id);

    User getUser(User queryParam, int[] arr, String str);
}
