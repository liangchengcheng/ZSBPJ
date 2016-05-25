package com.lcc.mvp.view;

import com.lcc.entity.CompanyDescription;
import com.lcc.entity.TestEntity;

import java.util.List;

public interface CompanyDescriptionView {

    void showError();

    void refreshView(List<CompanyDescription> entities);

    void loadMoreView(List<CompanyDescription> entities);
}
