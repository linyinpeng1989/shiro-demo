package com.github.linyp.shiro.chapter6.realm;

import com.github.linyp.shiro.chapter6.BaseTest;
import com.github.linyp.shiro.chapter6.entity.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public class PrincialCollectionTest extends BaseTest {

    @Test
    public void test() {
        //因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
        super.login("classpath:shiro-multirealm.ini", "zhang", "123");
        Subject subject = super.subject();
        //获取Primary Principal（即第一个）
        Object primaryPrincipal1 = subject.getPrincipal();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Object primaryPrincipal2 = principalCollection.getPrimaryPrincipal();

        //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
        Assert.assertEquals(primaryPrincipal1, primaryPrincipal2);

        //返回 a b c
        Set<String> realmNames = principalCollection.getRealmNames();
        System.out.println(realmNames);

        //因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
        Set<Object> principals = principalCollection.asSet(); //asList和asSet的结果一样
        System.out.println(principals);

        //根据Realm名字获取
        Collection<User> users = principalCollection.fromRealm("c");
        System.out.println(users);
    }
}