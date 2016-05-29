package com.lcc.mvp.presenter.impl;

import android.os.Handler;

import com.lcc.entity.Answer;
import com.lcc.entity.TestEntity;
import com.lcc.frame.net.okhttp.callback.ResultCallback;
import com.lcc.mvp.model.TestAnswerModel;
import com.lcc.mvp.model.TestModel;
import com.lcc.mvp.presenter.TestAnswerPresenter;
import com.lcc.mvp.presenter.TestPresenter;
import com.lcc.mvp.view.TestAnswerView;
import com.lcc.mvp.view.TestView;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import zsbpj.lccpj.utils.GsonUtils;
import zsbpj.lccpj.utils.TimeUtils;

public class TestAnswerPresenterImpl implements TestAnswerPresenter {

    private static final int DEF_DELAY = (int) (1 * 1000);
    private TestAnswerModel model;
    private TestAnswerView view;

    public TestAnswerPresenterImpl(TestAnswerView view) {
        this.view = view;
        model = new TestAnswerModel();
    }

    private void loadData(final int page,final String fid) {
        final long current_time = TimeUtils.getCurrentTime();
        model.getTestAnswer(page,fid, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(String response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, page);
            }
        });
    }

    private void updateView(final String entities, int delay, final int page) {

           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   try{
                       JSONObject jsonObject=new JSONObject(entities);
                       String data=jsonObject.getString("data");
                       List<Answer> entitys= GsonUtils.fromJsonArray(data,Answer.class);
                       if (page == 1) {
                           view.refreshView(entitys);
                       } else {
                           view.loadMoreView(entitys);
                       }
                   }catch (Exception e){
                       view.showError();
                       e.printStackTrace();
                   }
               }
           }, delay);
    }

    @Override
    public void loadMore(int page,String fid) {
        loadData(page,fid);
    }

    @Override
    public void refresh(int page,String fid) {
        loadData(page,fid);
    }
}
