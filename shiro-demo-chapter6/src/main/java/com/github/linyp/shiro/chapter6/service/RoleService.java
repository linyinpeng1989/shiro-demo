package com.github.linyp.shiro.chapter6.service;

import com.github.linyp.shiro.chapter6.entity.Role;

/**
 * @author Yinpeng Lin
 * @date 2017/11/19
 * @desc 角色Service
 */
public interface RoleService {
    /**
     * 创建角色
     * @param role  需要创建的角色
     * @return
     */
    Role createRole(Role role);

    /**
     * 删除角色
     * @param roleId    角色ID
     */
    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间的关系
     * @param roleId    角色ID
     * @param permissionIds     权限ID数组
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 删除角色-权限之间的关系
     * @param roleId    角色ID
     * @param permissionIds     权限ID数组
     */
    void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
