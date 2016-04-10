package com.lcc.mvp.view;

import com.lcc.entity.TestEntity;

import java.util.List;

public interface TestView {

    void showError();

    void refreshView(List<TestEntity> entities);

    void loadMoreView(List<TestEntity> entities);
}
