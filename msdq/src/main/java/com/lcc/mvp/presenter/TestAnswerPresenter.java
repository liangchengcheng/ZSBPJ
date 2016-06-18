package com.lcc.mvp.presenter;

public interface TestAnswerPresenter {

    void getData(int page,String fid);

    void loadMore(int page,String fid);

    void refresh(int page,String fid);

}
