package com.lcc.mvp.presenter;


import com.lcc.entity.TestEntity;

import java.util.List;

public interface TestPresenter {

    void loadMore(int id, int type, int page, int count);
    void refresh(int id, int type, int count);

}
