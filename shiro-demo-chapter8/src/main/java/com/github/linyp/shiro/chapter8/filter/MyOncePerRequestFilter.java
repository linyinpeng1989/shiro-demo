package com.github.linyp.shiro.chapter8.filter;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author Yinpeng Lin
 * @date 2017/11/23
 * @desc 扩展OncePerRequestFilter
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("==================== once per request filter");
        chain.doFilter(request, response);
    }
}
