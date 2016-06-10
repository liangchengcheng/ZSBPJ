package com.lcc.mvp.view;

import com.lcc.entity.Answer;
import com.lcc.entity.CompanyAnswer;

import java.util.List;

public interface CompanyAnswerView {

    void showError();

    void refreshView(List<CompanyAnswer> entities);

    void loadMoreView(List<CompanyAnswer> entities);
}
