package com.github.linyp.shiro.chapter6.realm;

import com.github.linyp.shiro.chapter6.entity.User;
import com.github.linyp.shiro.chapter6.service.UserService;
import com.github.linyp.shiro.chapter6.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc 自定义Realm，实现身份校验和权限校验
 */
public class UserRealm extends AuthorizingRealm {

    private UserService userService = new UserServiceImpl();

    /**
     * 获取授权信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        Set<String> roles = userService.findRoles(username);
        Set<String> permissions = userService.findPermissions(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取用户身份验证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException("账号被锁定");
        }
        return new SimpleAuthenticationInfo(
                // 用户名，即身份
                user.getUsername(),
                // 密码：hashedCredentials，即经过hash的验证
                user.getPassword(),
                // 加密盐值
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                // Realm名称
                getName()
        );
    }
}
