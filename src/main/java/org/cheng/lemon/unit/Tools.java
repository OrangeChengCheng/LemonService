package org.cheng.lemon.unit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    //打印
    public static void Log (String string) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("【 " + df.format(new Date()) + " 】->  " + string);
    }

    public static String getCurrentTimeFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currentTimeFormat = df.format(new Date());
        return currentTimeFormat;
    }


    /**
     * 将resultSet 转 JSONArray
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public static JSONArray getToJsonArray (ResultSet rs) throws SQLException,JSONException {

        // json数组
        JSONArray array = new JSONArray();
        // 获取列表
        ResultSetMetaData rsd = rs.getMetaData();
        // 获取表列数
        int columnCount = rsd.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            // 每读取一行，新建对象
            JSONObject rowObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                // 获取列名
                String columnName = rsd.getColumnName(i);
                // 取数据
                String value = rs.getString(columnName);
                // 添加到rowObj中
                rowObj.put(columnName, value);
            }
            array.add(rowObj);
        }
        return array;
    }


    /**
     * 将resultSet 转 JSONObject
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public static JSONObject getToJsonObject (ResultSet rs) throws SQLException,JSONException {

        // json对象
        JSONObject rowObj = new JSONObject();
        // 获取列表
        ResultSetMetaData rsd = rs.getMetaData();
        // 获取表列数
        int columnCount = rsd.getColumnCount();
        // 遍历ResultSet中的每条数据
        if (rs.next()) {
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                // 获取列名
                String columnName = rsd.getColumnName(i);
                // 取数据
                String value = rs.getString(columnName);
                // 添加到rowObj中
                rowObj.put(columnName, value);
            }
        }
        return rowObj;
    }


}
