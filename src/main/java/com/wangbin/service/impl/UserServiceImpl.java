package com.wangbin.service.impl;

import com.wangbin.dao.UserDao;
import com.wangbin.entity.User;
import com.wangbin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangbin on 2018/06/10.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public int addUser(User user) {
        int result = userDao.addUser(user);
        return result;
    }

    public int deleteUser(Integer id) {
        int result = userDao.deleteUser(id);
        return result;
    }

    public int updateUser(User user) {
        int result = userDao.updateUser(user);
        return result;
    }

    public User getUser(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    public List<User> getUserList() {
        List<User> list = userDao.getUserList();
        return list;
    }

}
