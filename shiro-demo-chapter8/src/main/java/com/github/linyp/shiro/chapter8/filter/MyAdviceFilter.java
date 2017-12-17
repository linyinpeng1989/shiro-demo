package com.github.linyp.shiro.chapter8.filter;

import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Yinpeng Lin
 * @date 2017/11/23
 * @desc 扩展AdviceFilter
 */
public class MyAdviceFilter extends AdviceFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====预处理/前置处理");
        //返回false将中断后续拦截器链的执行
        return true;
        //return super.preHandle(request, response);
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====后处理/后置返回处理");
        //super.postHandle(request, response);
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        System.out.println("====完成处理/后置最终处理");
        //super.afterCompletion(request, response, exception);
    }
}
