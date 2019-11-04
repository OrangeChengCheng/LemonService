package org.cheng.lemon.dao;


import org.cheng.lemon.MySQLConn;
import org.cheng.lemon.entity.ArticleInfo;
import org.cheng.lemon.unit.Logger;
import org.cheng.lemon.unit.Tools;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class ArticleDao {



    //创建文章
    public Boolean createArticle (ArticleInfo articleInfo) {

        int status = 0;
        String createTime = Tools.getCurrentTimeFormat();

        Connection connection = null;
        try {
            //获取数据库连接
            connection = MySQLConn.getConnection();

            //mysql查询语句
            String sql = "INSERT INTO articles " +
                    "(articleUrl," +
                    "articleName," +
                    "articleReadCount," +
                    "articleCommentCount," +
                    "articleCreateTime," +
                    "articleUpdateTime," +
                    "articleLikeCount) " +
                    "VALUES " +
                    "(?,?,?,?,?,?,?)";

            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1,articleInfo.getArticleUrl());
            prst.setString(2,articleInfo.getArticleName());
            prst.setInt(3,0);
            prst.setInt(4,0);
            prst.setString(5,createTime);
            prst.setString(6,createTime);
            prst.setInt(7,0);

            status = prst.executeUpdate();
            Tools.Log("Result 插入结果：" + status);

            MySQLConn.closeResultSet(prst, null);
        } catch (Exception e) {
            Logger.recordException(e);
            System.out.println(e);
            e.printStackTrace();
        }finally {
            MySQLConn.closeConn(connection);
        }

        return status > 0 ? true : false;
    }



}
