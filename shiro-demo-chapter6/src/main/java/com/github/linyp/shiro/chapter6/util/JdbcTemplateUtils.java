package com.github.linyp.shiro.chapter6.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Yinpeng Lin
 * @date 2017/11/21
 * @desc
 */
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {
        DruidDataSource ds = null;
        try {
            ds = new DruidDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/shiro");
            ds.setUsername("root");
            ds.setPassword("zxc654321");
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
        return new JdbcTemplate(ds);
    }
}
