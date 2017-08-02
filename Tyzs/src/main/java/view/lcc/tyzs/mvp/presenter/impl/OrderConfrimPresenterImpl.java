package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.OrderConfirmModel;
import view.lcc.tyzs.mvp.model.ShopCarAddModel;
import view.lcc.tyzs.mvp.presenter.OrderConfirmPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarAddPresenter;
import view.lcc.tyzs.mvp.view.OrderConfirmView;
import view.lcc.tyzs.mvp.view.ShopCarAddView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class OrderConfrimPresenterImpl implements OrderConfirmPresenter {
    private OrderConfirmView view;
    private OrderConfirmModel model;

    public OrderConfrimPresenterImpl(OrderConfirmView view) {
        this.view = view;
        model = new OrderConfirmModel();
    }

    @Override
    public void orderConfirm(String verification, String content) {
        view.OrderConfirmLoading();
        model.orderConfirm(verification,content,new ResultCallback<String>() {

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
                        view.OrderConfirmSuccess(response);
                    } else  {
                        view.OrderConfirmFail("添加信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.OrderConfirmFail("添加信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
