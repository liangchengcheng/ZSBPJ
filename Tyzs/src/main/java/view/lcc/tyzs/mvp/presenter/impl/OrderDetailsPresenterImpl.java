package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.AddressAddModel;
import view.lcc.tyzs.mvp.model.OrderDetailsModel;
import view.lcc.tyzs.mvp.presenter.AddressAddPresenter;
import view.lcc.tyzs.mvp.presenter.OrderDetailsPresenter;
import view.lcc.tyzs.mvp.view.AddressAddView;
import view.lcc.tyzs.mvp.view.OrderDetailsView;
import view.lcc.tyzs.utils.ErrorLogUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class OrderDetailsPresenterImpl implements OrderDetailsPresenter {
    private OrderDetailsView view;
    private OrderDetailsModel model;

    public OrderDetailsPresenterImpl(OrderDetailsView view) {
        this.view = view;
        model = new OrderDetailsModel();
    }

    @Override
    public void OrderDetails(String OID) {
        view.OrderDetailsLoading();
        model.OrderDetails(OID,new ResultCallback<String>() {

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
                        view.OrderDetailsSuccess(response);
                    } else  {
                        view.OrderDetailsFail(ErrorLogUtils.SystemError(status));
                    }
                } catch (Exception e) {
                    view.OrderDetailsFail("获取信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
