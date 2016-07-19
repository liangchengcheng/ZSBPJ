package com.lcc.mvp.view;

import com.lcc.entity.NewsInfo;

public interface NewsInfoView {

    /**
     * 等待
     */
    void showLoading();

    void NewsInfoFail(String msg);

    void NewsInfoSuccess(NewsInfo newsInfo);
}
