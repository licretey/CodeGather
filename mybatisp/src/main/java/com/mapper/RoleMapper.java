package com.mapper;

import com.com.dao.User;

import java.util.List;

public interface RoleMapper {
    List<User> listUserById(Integer in);
    User getUser(Integer in);
}
