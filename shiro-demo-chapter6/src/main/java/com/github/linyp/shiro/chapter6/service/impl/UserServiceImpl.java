package com.github.linyp.shiro.chapter6.service.impl;

import com.github.linyp.shiro.chapter6.dao.UserDao;
import com.github.linyp.shiro.chapter6.dao.impl.UserDaoImpl;
import com.github.linyp.shiro.chapter6.entity.User;
import com.github.linyp.shiro.chapter6.service.UserService;
import com.github.linyp.shiro.chapter6.util.PasswordHelper;

import java.util.Set;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public class UserServiceImpl implements UserService {

    private PasswordHelper passwordHelper = new PasswordHelper();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User createUser(User user) {
        // 密码加密
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId, roleIds);
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDao.uncorrelationRoles(userId, roleIds);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
