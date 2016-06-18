package com.lcc.mvp.view;

import com.lcc.entity.Answer;
import com.lcc.entity.CompanyAnswer;

import java.util.List;

public interface CompanyAnswerView {

    void getLoading();

    void getDataEmpty();

    void getDataFail(String msg);

    void refreshOrLoadFail(String msg);

    void refreshView(List<CompanyAnswer> entities);

    void loadMoreView(List<CompanyAnswer> entities);
}
