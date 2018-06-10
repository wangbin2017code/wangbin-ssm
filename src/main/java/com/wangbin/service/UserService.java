package com.wangbin.service;

import com.wangbin.entity.User;

import java.util.List;

/**
 * Created by wangbin on 2018/06/10.
 */
public interface UserService {
    public int addUser(User user);

    public int deleteUser(Integer id);

    public int updateUser(User user);

    public User getUser(Integer id);

    public List<User> getUserList();
}
