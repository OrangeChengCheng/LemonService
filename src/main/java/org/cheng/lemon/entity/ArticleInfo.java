package org.cheng.lemon.entity;

public class ArticleInfo {

    public Integer articleId;//博客id
    public String articleUrl;//博客链接
    public String articleName;//文章名称
    public Integer articleReadCount;//阅读次数
    public Integer articleCommentCount;//评论次数
    public String articleCreateTime;//创建时间
    public String articleUpdateTime;//更新时间
    public Integer articleLikeCount;//点赞次数


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getArticleReadCount() {
        return articleReadCount;
    }

    public void setArticleReadCount(Integer articleReadCount) {
        this.articleReadCount = articleReadCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public String getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(String articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(String articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }
}
