package com.github.linyp.shiro.chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yinpeng Lin
 * @date 2017/11/16
 * @desc 授权测试
 */
public class AuthorizerTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        super.login("classpath:shiro-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(super.subject().isPermitted("user1:update"));
        Assert.assertTrue(super.subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(super.subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(super.subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(super.subject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(super.subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(super.subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}
