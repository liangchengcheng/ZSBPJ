package com.lcc.mvp.presenter;

import com.lcc.entity.TestEntity;

import java.util.List;

public interface TestPresenter {

    void getData(int page);

    void loadMore(int page);

    void refresh();

}
