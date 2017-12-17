package com.github.linyp.shiro.chapter6.service;

import com.github.linyp.shiro.chapter6.entity.User;

import java.util.Set;

/**
 * @author Yinpeng Lin
 * @date 2017/11/19
 * @desc 用户Service
 */
public interface UserService {
    /**
     * 创建账户
     * @param user  需要创建的用户
     * @return
     */
    User createUser(User user);

    /**
     * 修改密码
     * @param userId    用户ID
     * @param newPassword   新密码
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId    用户ID
     * @param roleIds   角色ID数组
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * 删除用户-角色关系
     * @param userId    用户ID
     * @param roleIds   角色ID数组
     */
    void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找用户
     * @param username  用户名
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username  用户名
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);
}
