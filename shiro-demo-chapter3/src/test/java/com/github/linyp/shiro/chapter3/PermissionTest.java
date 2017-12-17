package com.github.linyp.shiro.chapter3;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * 基于资源/权限的访问控制（显示角色）
 * @author Yinpeng Lin
 * @date 2017/11/9
 */
public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        super.login("classpath:shiro-permission.ini", "zhang", "123");
        // 判断拥有权限：user:create
        Assert.assertTrue(super.subject().isPermitted("user:create"));
        // 判断拥有权限：user:update and user:delete
        Assert.assertTrue(super.subject().isPermittedAll("user:update", "user:delete"));
        // 判断没有权限：user:view
        Assert.assertFalse(super.subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        super.login("classpath:shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        super.subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        super.subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        super.subject().checkPermissions("user:view");
    }
}
