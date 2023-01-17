package com.study.spring.boot.config;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 工具类
 * MySQL中以“_”分割的列名格式化为驼峰命名的列名
 */
public class FormatDbColumns {
    static final String DB_URL = "jdbc:mysql://1.117.59.156:3306/sys";
    // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称
    static final String USER = "root";
    static final String PASS = "Lt199606.";

    public static void main(String[] args) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;

        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

        String str = "QualityCall";

        // 执行查询
        stat = conn.createStatement();
        String sql = "select COLUMN_NAME as columnName from information_schema.COLUMNS where table_name = '"+str+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        int i = 0;
        Map<String,String> map = new LinkedHashMap<>();
        while(rs.next()){
            i++;
            String a = rs.getString("columnName");
            String b= toUnderCase(a);
            map.put(b,"xxx");
            System.out.println(b);

            String sql2 = "alter table "+str+" change "+a+" "+"b"+"";
        }

        System.out.println(JSONObject.toJSONString(map));
        System.out.println(i);
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static final char UNDER_LINE = '_';
    public static String toUnderCase(String name) {
        if (name == null) {
            return null;
        }
        int len = name.length();
        StringBuilder res = new StringBuilder(len + 2);
        char pre = 0;
        for (int i = 0; i < len; i++) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (pre != UNDER_LINE) {
                    res.append(UNDER_LINE);
                }
                res.append(Character.toLowerCase(ch));
            } else {
                res.append(ch);
            }
            pre = ch;
        }
        return res.toString();
    }
}