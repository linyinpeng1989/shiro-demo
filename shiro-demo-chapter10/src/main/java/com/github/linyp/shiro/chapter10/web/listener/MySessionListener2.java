package com.github.linyp.shiro.chapter10.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * @author Yinpeng Lin
 * @date 2017/11/24
 * @desc 继承适配器，选择监听某些事件（不用全部）
 */
public class MySessionListener2 extends SessionListenerAdapter {

    @Override
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }
}
