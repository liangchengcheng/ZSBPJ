package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.AddressAddModel;
import view.lcc.tyzs.mvp.model.AddressDeleteModel;
import view.lcc.tyzs.mvp.presenter.AddressAddPresenter;
import view.lcc.tyzs.mvp.presenter.AddressDeletePresenter;
import view.lcc.tyzs.mvp.view.AddressAddView;
import view.lcc.tyzs.mvp.view.AddressDeleteView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |获取商品
 */
public class AddressAddPresenterImpl implements AddressAddPresenter {
    private AddressAddView view;
    private AddressAddModel model;

    public AddressAddPresenterImpl(AddressAddView view) {
        this.view = view;
        model = new AddressAddModel();
    }

    @Override
    public void addressAdd(String phone,String address, String addressee,String aphone) {
        view.AddressAddLoading();
        model.addressAdd(phone,address, addressee,aphone,new ResultCallback<String>() {

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

                        view.AddressAddSuccess("");
                    } else  {
                        view.AddressAddFail("添加信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.AddressAddFail("添加信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
