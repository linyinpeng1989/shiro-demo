package com.github.linyp.shiro.chapter6.dao;

import com.github.linyp.shiro.chapter6.entity.Role;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public interface RoleDao {
    /**
     * 创建角色
     * @param role
     * @return
     */
    Role createRole(Role role);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(Long roleId);

    /**
     * 创建角色-权限关系
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 删除角色-全新啊关系
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
