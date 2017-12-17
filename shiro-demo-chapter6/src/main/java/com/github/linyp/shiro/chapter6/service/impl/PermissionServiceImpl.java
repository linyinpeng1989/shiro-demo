package com.github.linyp.shiro.chapter6.service.impl;

import com.github.linyp.shiro.chapter6.dao.PermissionDao;
import com.github.linyp.shiro.chapter6.dao.impl.PermissionDaoImpl;
import com.github.linyp.shiro.chapter6.entity.Permission;
import com.github.linyp.shiro.chapter6.service.PermissionService;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
