package org.cheng.lemon.controllers;


import com.alibaba.fastjson.JSON;
import org.cheng.lemon.entity.ArticleInfo;
import org.cheng.lemon.entity.RequestParam;
import org.cheng.lemon.entity.ResultData;
import org.cheng.lemon.service.ArticleService;
import org.cheng.lemon.unit.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/lemon/article")
public class ArticleController {


    @Autowired
    ArticleService articleService;


    //创建文章
    @RequestMapping("/create")
    @ResponseBody
    public String createArticle(@RequestBody RequestParam param, HttpServletRequest request) throws IOException {

        //请求日志
        Logger.recordRequestOperate(param, request);

        ArticleInfo articleInfo = JSON.parseObject(JSON.toJSONString(param.getData()),ArticleInfo.class);

        Boolean status = articleService.createArticle(articleInfo);

        ResultData result = new ResultData();
        result.data = status;

        return JSON.toJSONString(result);
    }



    //获取文章列表
    @RequestMapping("/list")
    @ResponseBody
    public String getArticleList(@RequestBody RequestParam param, HttpServletRequest request) throws IOException {

        //请求日志
        Logger.recordRequestOperate(param, request);

        List list = articleService.getArticleList();

        List articles = new ArrayList();
        for (Object temp : list) {
            ArticleInfo articleInfo = JSON.parseObject(JSON.toJSONString(temp),ArticleInfo.class);
            articles.add(articleInfo);
        }

        ResultData result = new ResultData();
        result.data = articles;

        return JSON.toJSONString(result);
    }

}
