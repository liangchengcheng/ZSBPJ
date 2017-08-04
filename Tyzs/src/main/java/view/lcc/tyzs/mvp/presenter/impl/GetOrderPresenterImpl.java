package view.lcc.tyzs.mvp.presenter.impl;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.GetOrderModel;
import view.lcc.tyzs.mvp.model.RegisterModel;
import view.lcc.tyzs.mvp.presenter.GetOrderPresenter;
import view.lcc.tyzs.mvp.presenter.RegisterPresenter;
import view.lcc.tyzs.mvp.view.GetOrderView;
import view.lcc.tyzs.mvp.view.RegisterView;
import view.lcc.tyzs.utils.TimeUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |获取订单
 */
public class GetOrderPresenterImpl implements GetOrderPresenter {
    private GetOrderView view;
    private GetOrderModel model;
    private static final int DEF_DELAY = (int) (1 * 1000);


    public GetOrderPresenterImpl(GetOrderView view) {
        this.view = view;
        model = new GetOrderModel();
    }


    private void loadData(final String page, String pagesize, String phone, String state, final boolean getData) {
        if (getData) {
            view.GetOrderLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();

        model.register(page,pagesize,phone,state, new ResultCallback<String>() {

            @Override
            public void onError(Request request, Exception e) {
                view.NetWorkErr(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, Integer.parseInt(page), getData);
            }
        });
    }

    private void updateView(final String entities, int delay, final int page, final boolean get_data) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(entities);
                    String code = jsonObject.getString("resultno");
                    if (code.equals("000")) {
                        if (page == 1) {
                            view.refreshDataSuccess(entities);
                        } else {
                            view.loadMoreWeekDataSuccess(entities);
                        }
                    }  else {
                        if (get_data) {
                            // TODO: 2017/8/4 具体的错误信息
                            view.GetOrderFail("获取信息失败");
                        } else {
                            view.refreshOrLoadFail("");
                        }
                    }
                } catch (Exception e) {
                    if (get_data) {
                        view.GetOrderFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    } else {
                        view.refreshOrLoadFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    }
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    @Override
    public void getOrder(String page,String pagesize,String phone,String state) {
        view.GetOrderLoading();

    }

    @Override
    public void refresh(String page, String pagesize, String phone, String state) {

    }

    @Override
    public void loadMore(String page, String pagesize, String phone, String state) {

    }

}
