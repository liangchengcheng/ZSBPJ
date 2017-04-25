package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.bean.Link;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.BookDetailsModel;
import view.lcc.wyzsb.mvp.model.LinkDetailsModel;
import view.lcc.wyzsb.mvp.presenter.BookDetailsPresenter;
import view.lcc.wyzsb.mvp.presenter.LinkDetailsPresenter;
import view.lcc.wyzsb.mvp.view.BookDetailsView;
import view.lcc.wyzsb.mvp.view.LinkDetailsView;
import view.lcc.wyzsb.utils.GsonUtils;

public class LinkDetailsPresenterImpl implements LinkDetailsPresenter {
    private LinkDetailsModel model;
    private LinkDetailsView view;

    public LinkDetailsPresenterImpl(LinkDetailsView view) {
        this.view = view;
        model = new LinkDetailsModel();
    }

    @Override
    public void getContent(String mid) {
        view.getLoading();
        model.getContent(mid, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    //这个是内容
                    if (status == 1) {
                        String result = jsonObject.getString("result");
                        Link answerContent = GsonUtils.changeGsonToBean(result, Link.class);
                        view.getDataSuccess(answerContent);
                    } else {
                        view.getDataFail(message);
                    }
                } catch (Exception e) {
                    view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    e.printStackTrace();
                }
            }
        });
    }
}
