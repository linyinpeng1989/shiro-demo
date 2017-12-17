package com.github.linyp.shiro.chapter6.util;

import com.github.linyp.shiro.chapter6.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author Yinpeng Lin
 * @date 2017/11/20
 * @desc 密码进行哈希加密处理
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    /**
     * 使用Shiro加解密功能对密码进行加密
     * @param user
     */
    public void encryptPassword(User user) {
        // 生成盐（随机数）
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                // 用户名+ 盐作为加密盐
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        // 设置
        user.setPassword(newPassword);
    }

}
