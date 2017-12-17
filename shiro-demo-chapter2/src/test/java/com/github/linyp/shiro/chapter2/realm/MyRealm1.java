package com.github.linyp.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author Yinpeng Lin
 * @date 2017/11/7
 */
public class MyRealm1 implements Realm {
    /**
     * 返回一个唯一的Realm名字
     *
     * @return
     */
    public String getName() {
        return "myrealm1";
    }

    /**
     * 判断此Realm是否支持此Token
     *
     * @param token
     * @return
     */
    public boolean supports(AuthenticationToken token) {
        // 仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 根据Token获取认证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获得用户名（身份）
        String userName = (String) token.getPrincipal();
        // 获得密码（认证）
        String password = new String((char[]) token.getCredentials());
        // 如果用户名错误
        if (!"zhang".equals(userName)) {
            throw new UnknownAccountException();
        }
        // 如果密码错误
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
