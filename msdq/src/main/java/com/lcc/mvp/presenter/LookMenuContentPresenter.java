package com.lcc.mvp.presenter;

import com.lcc.entity.Article;

public interface LookMenuContentPresenter {

    void Fav(Article article, String type);

    void UnFav(Article article);

    //为了查看收藏的重新获取
    void getArticleContentAndFav(String id);
}
