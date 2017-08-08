package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.ShopCarAddModel;
import view.lcc.tyzs.mvp.model.ShopCarGetModel;
import view.lcc.tyzs.mvp.presenter.ShopCarAddPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarGetPresenter;
import view.lcc.tyzs.mvp.view.ShopCarAddView;
import view.lcc.tyzs.mvp.view.ShopCarGetView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class ShopCarGetPresenterImpl implements ShopCarGetPresenter {
    private ShopCarGetView view;
    private ShopCarGetModel model;

    public ShopCarGetPresenterImpl(ShopCarGetView view) {
        this.view = view;
        model = new ShopCarGetModel();
    }

    @Override
    public void shopCarGet( String phone) {
        view.ShopCarGetLoading();
        model.shopCarGet(phone,new ResultCallback<String>() {

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
                        view.ShopCarGetSuccess(response);
                    } else  {
                        view.ShopCarGetFail("获取购物车信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.ShopCarGetFail("获取地址信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
