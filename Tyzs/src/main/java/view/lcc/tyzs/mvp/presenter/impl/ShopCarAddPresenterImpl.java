package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.AddressAddModel;
import view.lcc.tyzs.mvp.model.ShopCarAddModel;
import view.lcc.tyzs.mvp.presenter.AddressAddPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarAddPresenter;
import view.lcc.tyzs.mvp.view.AddressAddView;
import view.lcc.tyzs.mvp.view.ShopCarAddView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class ShopCarAddPresenterImpl implements ShopCarAddPresenter {
    private ShopCarAddView view;
    private ShopCarAddModel model;

    public ShopCarAddPresenterImpl(ShopCarAddView view) {
        this.view = view;
        model = new ShopCarAddModel();
    }

    @Override
    public void shopCarAdd(String user, String content) {
        view.ShopCarAddLoading();
        model.shopCarAdd(user,content,new ResultCallback<String>() {

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
                        view.ShopCarAddSuccess("");
                    } else  {
                        view.ShopCarAddFail("添加信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.ShopCarAddFail("添加信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
