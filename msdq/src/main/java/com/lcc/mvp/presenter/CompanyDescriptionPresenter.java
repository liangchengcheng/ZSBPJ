package com.lcc.mvp.presenter;

public interface CompanyDescriptionPresenter {

    void getData(int page,String type);

    void loadMore(int page);

    void refresh();

}
