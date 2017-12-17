package com.github.linyp.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author Yinpeng Lin
 * @date 2017/11/16
 * @desc 自定义PersissionResolver，用于解析权限字符串到Permission实例
 */
public class BitAndWildPermissionResolver implements PermissionResolver {
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
