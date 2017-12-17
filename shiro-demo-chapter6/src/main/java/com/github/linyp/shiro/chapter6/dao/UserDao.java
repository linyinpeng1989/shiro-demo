package com.github.linyp.shiro.chapter6.dao;

import com.github.linyp.shiro.chapter6.entity.User;

import java.util.Set;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public interface UserDao {
    /**
     * 创建用户
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 新增用户、角色关系
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * 删除用户、角色关系
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    User findOne(Long userId);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查询权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);
}
