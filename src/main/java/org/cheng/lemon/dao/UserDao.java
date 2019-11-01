package org.cheng.lemon.dao;


import org.cheng.lemon.MySQLConn;
import org.cheng.lemon.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDao {

    public String getUserListDao (UserInfo userInfo) {

        String text = "";
        Connection connection = null;
        try {
            //获取数据库连接
            connection = MySQLConn.getConnection();

            //mysql查询语句
            String sql = "SELECT * FROM authors";
            PreparedStatement prst = connection.prepareStatement(sql);
            //结果集
            ResultSet rst = prst.executeQuery();
            while (rst.next()) {
                System.out.println("用户名:" + rst.getString("username"));
                text = text + "【用户名:" + rst.getString("username") + "】";
            }

            MySQLConn.closeResultSet(prst, rst);
        } catch (Exception e) {
            System.out.println("catch");
            System.out.println(e);
            e.printStackTrace();
        }finally {
            MySQLConn.closeConn(connection);
        }

        return text;
    }
}
