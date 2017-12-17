package com.github.linyp.shiro.chapter6.dao;

import com.github.linyp.shiro.chapter6.entity.Permission;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public interface PermissionDao {
    /**
     * 创建权限
     * @param permission
     * @return
     */
    Permission createPermission(Permission permission);

    /**
     * 删除权限
     * @param permissionId
     */
    void deletePermission(Long permissionId);
}
