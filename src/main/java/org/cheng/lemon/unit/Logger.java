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
        StringBuffer dao = new StringBuffer();
        StringBuffer service = new StringBuffer();
        StringBuffer controller = new StringBuffer();
        StackTraceElement[] st = e.getStackTrace();
        for (StackTraceElement stackTraceElement : st) {
            String exclass = stackTraceElement.getClassName();
            if (exclass.contains("org.cheng.lemon.dao")) {
                String method = stackTraceElement.getMethodName();
                String pathStr = "【类: " + exclass + " 调用 -> "
                        + method + " <- 时在(第" + stackTraceElement.getLineNumber()
                        + "行) 代码处发生异常!异常类型: " + e.getClass().getName() + "】";
                dao.append(pathStr);
            }
            else if (exclass.contains("org.cheng.lemon.service")) {
                String method = stackTraceElement.getMethodName();
                String pathStr = "【类: " + exclass + " 调用 -> "
                        + method + " <- 时在(第" + stackTraceElement.getLineNumber()
                        + "行) 代码处发生异常!异常类型: " + e.getClass().getName() + "】";
                service.append(pathStr);
            }
            else if (exclass.contains("org.cheng.lemon.controllers")) {
                String method = stackTraceElement.getMethodName();
                String pathStr = "【类: " + exclass + " 调用 -> "
                        + method + " <- 时在(第" + stackTraceElement.getLineNumber()
                        + "行) 代码处发生异常!异常类型: " + e.getClass().getName() + "】";
                controller.append(pathStr);
            }
        }

        String exceptionDaoString = dao.toString();
        String exceptionServiceString = service.toString();
        String exceptionControllerString = controller.toString();

        String requestDate = Tools.getCurrentTimeFormat();
        Connection connection = null;

        try {
            //获取数据库连接
            connection = MySQLConn.getConnection();
            //mysql查询语句
            String sql = "INSERT INTO errorlogs (errordate,errordao,errorservice,errorcontroller) VALUES (?,?,?,?)";

            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, requestDate);
            prst.setString(2, exceptionDaoString);
            prst.setString(3, exceptionServiceString);
            prst.setString(4, exceptionControllerString);
            prst.executeUpdate();

            MySQLConn.closeResultSet(prst, null);

        }catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }finally {
            MySQLConn.closeConn(connection);
        }

    }



}
