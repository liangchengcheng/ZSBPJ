package view.lcc.wyzsb.mvp.presenter.impl;

import com.squareup.okhttp.Request;
import org.json.JSONObject;

import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.FeedBackModel;
import view.lcc.wyzsb.mvp.presenter.FeedBackPresenter;
import view.lcc.wyzsb.mvp.view.FeedBackView;


public class FeedBackPresenterImpl implements FeedBackPresenter {

    private FeedBackView view;
    private FeedBackModel model;

    public FeedBackPresenterImpl(FeedBackView view) {
        this.view = view;
        model = new FeedBackModel();
    }

    @Override
    public void postMessage(String password) {
        view.showLoading();
        model.PostMessage(password, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.FeekFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status == 1) {
                        view.FeekSuccess();
                    } else {
                        view.FeekFail(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

