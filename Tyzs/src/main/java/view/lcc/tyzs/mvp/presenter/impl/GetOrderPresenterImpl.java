package view.lcc.tyzs.mvp.presenter.impl;

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

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |获取订单
 */
public class GetOrderPresenterImpl implements GetOrderPresenter {
    private GetOrderView view;
    private GetOrderModel model;

    public GetOrderPresenterImpl(GetOrderView view) {
        this.view = view;
        model = new GetOrderModel();
    }

    @Override
    public void getOrder(String page,String pagesize,String phone,String state) {
        view.RegisterLoading();
        model.register(page,pagesize,phone,state, new ResultCallback<String>() {

            @Override
            public void onError(Request request, Exception e) {
                view.NetWorkErr(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("resultno");
                    if (!TextUtils.isEmpty(status) && status.equals("000")) {
                        String data = response.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
                        // TODO: 2017/8/1 没写完 
                        view.RegisterSuccess("");
                    } else  {
                        view.RegisterFail("注册失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.RegisterFail("注册失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
