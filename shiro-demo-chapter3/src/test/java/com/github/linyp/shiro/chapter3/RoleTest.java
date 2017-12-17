package com.github.linyp.shiro.chapter3;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 基于角色的访问控制（隐式角色）
 * @author Yinpeng Lin
 * @date 2017/11/8
 */
public class RoleTest extends BaseTest {

    /**
     * 是否拥有权限
     */
    @Test
    public void testHasRole() {
        super.login("classpath:shiro-role.ini", "zhang", "123");
        //判断拥有角色：role1
        Assert.assertTrue(super.subject().hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(super.subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2, 不拥有role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        super.login("classpath:shiro-role.ini", "zhang", "123");
        //判断拥有角色：role1
        super.subject().checkRole("role1");
        //判断拥有角色：role1 and role3 失败抛出异常
        super.subject().checkRoles("role1", "role3");
    }

}
