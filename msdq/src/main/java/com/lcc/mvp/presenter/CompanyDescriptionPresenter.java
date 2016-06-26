package com.lcc.mvp.presenter;

public interface CompanyDescriptionPresenter {

    void getData(int page,String company_name);

    void loadMore(int page,String company_name);

    void refresh(String company_name);

}
