package org.cheng.lemon;

import org.cheng.lemon.unit.Tools;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConn {

    //mysql驱动包名
    private static String DRIVER;
    //数据库连接地址
    private static String URL;
    //用户名
    private static String USER;
    //密码
    private static String PASSWORD;

    static {
        try {
            ClassLoader classLoader = MySQLConn.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("application.properties");
            Properties pt = new Properties();
            pt.load(is);
            DRIVER = pt.getProperty("driver");
            URL = pt.getProperty("url");
            USER = pt.getProperty("user");
            PASSWORD = pt.getProperty("password");
        }catch (IOException e){
            Tools.Log("获取配置文件报错 " + e);
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception e){
            Tools.Log("Connection 连接报错" + e);
            e.printStackTrace();
        }
        return conn;
    }

    //关闭查询和结果集
    public static void closeResultSet(PreparedStatement pstmt, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                Tools.Log("ResultSet 关闭报错" + e);
                e.printStackTrace();
            }
        }
        if (pstmt!=null){
            try {
                pstmt.close();
            }catch (SQLException e){
                Tools.Log("PreparedStatement 关闭报错" + e);
                e.printStackTrace();
            }
        }
    }

    //关闭连接
    public static void closeConn(Connection conn) {
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                Tools.Log("Connection 关闭报错" + e);
                e.printStackTrace();
            }
        }
    }


}
