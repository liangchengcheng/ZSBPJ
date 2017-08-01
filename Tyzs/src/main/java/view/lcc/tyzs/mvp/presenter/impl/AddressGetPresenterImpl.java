package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.AddressAddModel;
import view.lcc.tyzs.mvp.model.AddressGetModel;
import view.lcc.tyzs.mvp.presenter.AddressAddPresenter;
import view.lcc.tyzs.mvp.presenter.AddressGetPresenter;
import view.lcc.tyzs.mvp.view.AddressAddView;
import view.lcc.tyzs.mvp.view.AddressGetView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class AddressGetPresenterImpl implements AddressGetPresenter {
    private AddressGetView view;
    private AddressGetModel model;

    public AddressGetPresenterImpl(AddressGetView view) {
        this.view = view;
        model = new AddressGetModel();
    }

    @Override
    public void addressGet(String phone) {
        view.AddressGetLoading();
        model.addressGet(phone,new ResultCallback<String>() {

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

                        view.AddressGetSuccess("");
                    } else  {
                        view.AddressGetFail("添加信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.AddressGetFail("添加信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
