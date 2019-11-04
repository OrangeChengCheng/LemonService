package org.cheng.lemon.service;


import org.cheng.lemon.dao.ArticleDao;
import org.cheng.lemon.entity.ArticleInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class ArticleService {

    @Resource
    private ArticleDao articleDao;


    //创建文章
    public Boolean createArticle (ArticleInfo articleInfo) {

        return articleDao.createArticle(articleInfo);

    }



    //获取文章列表
    public List getArticleList () {

        return articleDao.getArticleList();

    }


}
