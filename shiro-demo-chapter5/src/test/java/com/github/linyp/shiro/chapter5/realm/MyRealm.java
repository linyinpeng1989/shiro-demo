package com.github.linyp.shiro.chapter5.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author Yinpeng Lin
 * @date 2017/11/17
 * @desc
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 加密密码服务
     */
    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    /**
     * 获取权限信息
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 获取身份认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String userName = (String) token.getPrincipal();
        // 获取密码
        String passowrd = new String((char[]) token.getCredentials());
        if (!"wu".equals(userName)) {
            throw new UnknownAccountException("账号不存在");
        }
        if (!"123".equals(passowrd)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        return new SimpleAuthenticationInfo("wu", passwordService.encryptPassword("123"), getName());
    }
}
