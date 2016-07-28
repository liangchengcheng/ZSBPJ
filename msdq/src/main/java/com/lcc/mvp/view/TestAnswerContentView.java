package com.lcc.mvp.view;


public interface TestAnswerContentView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    /**
     * 标识是否被收藏了
     */
    void isHaveFav(boolean isfavEntity);

    /**
     * 收藏成功
     */
    void FavSuccess();

    /**
     * 收藏失败
     */
    void FavFail(String msg);

    /**
     * 取消收藏成功
     */
    void UnFavSuccess();

    /**
     * 取消收藏失败
     */
    void UnFavFail(String msg);
}
