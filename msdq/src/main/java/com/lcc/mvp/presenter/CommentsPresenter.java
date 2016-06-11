package com.lcc.mvp.presenter;

public interface CommentsPresenter {

    void getData(int page, String nid);

    void loadMore(int page, String nid);

    void refresh(int page, String nid);
}
