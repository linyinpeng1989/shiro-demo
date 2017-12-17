package com.github.linyp.shiro.chapter8.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * @author Yinpeng Lin
 * @date 2017/11/23
 * @desc 扩展PathMatchingFilter
 */
public class MyPathMatchingFilter extends PathMatchingFilter {


    /**
     preHandle：会进行url 模式与请求url 进行匹配，如果匹配会调用onPreHandle；如果没有
            配置url模式/没有url模式匹配，默认直接返回true；
     onPreHandle：如果url 模式与请求url 匹配，那么会执行onPreHandle，并把该拦截器配置
            的参数传入
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("url matches,config is " + Arrays.toString((String[])mappedValue));
        return true;
        //return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====================== prehandle ");
        return super.preHandle(request, response);
    }
}
