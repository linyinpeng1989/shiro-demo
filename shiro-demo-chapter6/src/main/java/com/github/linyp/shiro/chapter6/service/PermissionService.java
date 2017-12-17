package com.github.linyp.shiro.chapter6.service;

import com.github.linyp.shiro.chapter6.entity.Permission;

/**
 * @author Yinpeng Lin
 * @date 2017/11/19
 * @desc 权限Service
 */
public interface PermissionService {
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
