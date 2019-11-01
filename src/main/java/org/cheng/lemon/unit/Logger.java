package org.cheng.lemon.unit;

import com.alibaba.fastjson.JSON;
import org.cheng.lemon.MySQLConn;
import org.cheng.lemon.entity.RequestParam;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;


@Service
public class Logger {


    //记录网络请求
    public static void recordRequestOperate(RequestParam param, HttpServletRequest request) {

        String requestName = request.getRequestURL().toString();
        String requestParam = JSON.toJSONString(param);
        String requestDate = Tools.getCurrentTimeFormat();

        Connection connection = null;

        try {
            //获取数据库连接
            connection = MySQLConn.getConnection();

            //mysql查询语句
            String sql = "INSERT INTO requestlogs (requestName,requestParam,requestDate) VALUES (?,?,?)";

            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1,requestName);
            prst.setString(2,requestParam);
            prst.setString(3,requestDate);
            prst.executeUpdate();

            MySQLConn.closeResultSet(prst, null);
        } catch (Exception e) {

            recordException(e);
            e.printStackTrace();
        }finally {
            MySQLConn.closeConn(connection);
        }

    }


    //记录异常
    public static void recordException(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] st = e.getStackTrace();
        for (StackTraceElement stackTraceElement : st) {
            String exclass = stackTraceElement.getClassName();
            if (exclass.contains("com.example")) {
                String method = stackTraceElement.getMethodName();
                String pathStr = "【类: " + exclass + " 调用 -> "
                        + method + " <- 时在(第" + stackTraceElement.getLineNumber()
                        + "行) 代码处发生异常!异常类型: " + e.getClass().getName() + "】";
                sb.append(pathStr);
            }
        }

        String exceptionString = sb.toString();
        String requestDate = Tools.getCurrentTimeFormat();
        Connection connection = null;

        try {
            //获取数据库连接
            connection = MySQLConn.getConnection();
            //mysql查询语句
            String sql = "INSERT INTO errorlogs (errorcontent,errordate) VALUES (?,?)";

            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, exceptionString);
            prst.setString(2, requestDate);
            prst.executeUpdate();

            MySQLConn.closeResultSet(prst, null);

        }catch (Exception ex) {

            ex.printStackTrace();
        }finally {
            MySQLConn.closeConn(connection);
        }

    }



}
