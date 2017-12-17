package com.github.linyp.shiro.chapter3.permission;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * @author Yinpeng Lin
 * @date 2017/11/16
 * @desc 用于实现位移方式的权限
 *
 * 规则（以+开头 中间通过+分割）：+资源字符串+权限位+实例ID
 *
 * 权限：
 *  0 - 表示所有全新啊
 *  1 - 新增 0001
 *  2 - 修改 0010
 *  4 - 删除 0100
 *  8 - 查看 1000
 *
 *  如 +user+10 表示对资源user拥有修改/查看权限
 *  不考虑一些异常情况
 */
public class BitPermission implements Permission {
    /**
     * 表示资源位
     */
    private String resourceIdentify;
    /**
     * 权限位
     */
    private int permissionBit;
    /**
     * 实例位
     */
    private String instanceId;

    /**
     * 构造方法
     * @param permissionString  权限字符串，比如+user+10
     */
    public BitPermission(String permissionString) {
        String[] arrays = permissionString.split("\\+");

        // 资源位
        if (arrays.length > 1) {
            resourceIdentify = arrays[1];
        }

        if (StringUtils.isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }

        // 权限位
        if (arrays.length > 2) {
            permissionBit = Integer.valueOf(arrays[2]);
        }

        // 实例位
        if (arrays.length > 3) {
            instanceId = arrays[3];
        }

        if (StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    public boolean implies(Permission p) {
        if (!(p instanceof BitPermission)) {
            return false;
        }
        BitPermission other = (BitPermission) p;

        if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
            return false;
        }

        if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
            return false;
        }

        if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
            return false;
        }
        return true;
    }

}
