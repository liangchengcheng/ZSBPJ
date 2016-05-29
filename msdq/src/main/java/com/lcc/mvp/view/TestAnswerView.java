package com.lcc.mvp.view;

import com.lcc.entity.Answer;
import com.lcc.entity.TestEntity;

import java.util.List;

public interface TestAnswerView {

    void showError();

    void refreshView(List<Answer> entities);

    void loadMoreView(List<Answer> entities);
}
