package com.github.linyp.shiro.chapter8.env;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

import javax.servlet.Filter;

/**
 * @author Yinpeng Lin
 * @date 2017/11/23
 * @desc 继承IniWebEnvironment，重写createFilterChainResolver方法（自定义FilterChainResolver实现）
 */
public class MyIniWebEnvironment extends IniWebEnvironment {
    @Override
    protected FilterChainResolver createFilterChainResolver() {
        // 在此处扩展自己的FilterChainResolver
        // 1、创建FilterChainResolver
        PathMatchingFilterChainResolver filterChainResolver = new PathMatchingFilterChainResolver();

        // 2、创建FilterChainManager
        DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();

        // 3、注册默认的Filter
        for (DefaultFilter filter : DefaultFilter.values()) {
            filterChainManager.addFilter(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
        }

        // 4、注册URL-Filter的映射关系
        filterChainManager.addToChain("/login.jsp", "authc");
        filterChainManager.addToChain("/unauthorized.jsp", "anon");
        filterChainManager.addToChain("/**", "authc");
        filterChainManager.addToChain("/**", "roles", "admin");

        // 5、设置Filter属性
        FormAuthenticationFilter authcFilter = (FormAuthenticationFilter) filterChainManager.getFilter("authc");
        authcFilter.setLoginUrl("/login.jsp");
        RolesAuthorizationFilter rolesFilter = (RolesAuthorizationFilter) filterChainManager.getFilter("roles");
        rolesFilter.setUnauthorizedUrl("/unauthorized.jsp");

        filterChainResolver.setFilterChainManager(filterChainManager);
        return filterChainResolver;
    }
}
