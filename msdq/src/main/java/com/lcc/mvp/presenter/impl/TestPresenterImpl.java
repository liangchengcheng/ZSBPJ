package com.lcc.mvp.presenter.impl;

import android.os.Handler;
import com.lcc.entity.TestEntity;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.TestModel;
import com.lcc.mvp.presenter.TestPresenter;
import com.lcc.mvp.view.TestView;
import com.squareup.okhttp.Request;
import java.util.List;
import zsbpj.lccpj.utils.TimeUtils;

public class TestPresenterImpl implements TestPresenter{

    private static final int DEF_DELAY = (int) (1 * 1000);
    private TestModel model;
    private TestView view;

    public TestPresenterImpl(TestView view) {
        this.view = view;
        model = new TestModel();
    }

    private void loadData(int id, int type, final int page, int count) {
        final long current_time = TimeUtils.getCurrentTime();
        model.getTestList(id, type, page, count, new ResultCallback<List<TestEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<TestEntity> response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, page);
            }
        });
    }

    private void updateView(final List<TestEntity> entities, int delay, final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page == 1) {
                    view.refreshView(entities);
                } else {
                    view.loadMoreView(entities);
                }
            }
        }, delay);
    }

    @Override
    public void loadMore(int id, int type, int page, int count) {
        loadData(id, type, page, count);
    }

    @Override
    public void refresh(int id, int type, int count) {
        loadData(id, type, 1, count);
    }
}
