package com.github.linyp.shiro.chapter3.realm;

import com.github.linyp.shiro.chapter3.permission.BitPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author Yinpeng Lin
 * @date 2017/11/16
 * @desc 自定义Realm
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 根据用户身份获取授权信息
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 得到用户名
        String userName = (String) token.getPrincipal();
        // 得到密码
        String password = new String((char[]) token.getCredentials());
        if (!"zhang".equals(userName)) {
            //如果密码错误
            throw new IncorrectCredentialsException();
        }
        if (!"123".equals(password)) {
            //如果密码错误
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
