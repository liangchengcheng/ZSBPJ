package com.lcc.mvp.presenter;

public interface IndexMenuPresenter {

    void getData(int page,String type);

    void loadMore(int page,String type);

    void refresh(int page,String type);
}
