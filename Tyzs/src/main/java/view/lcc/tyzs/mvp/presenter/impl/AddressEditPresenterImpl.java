package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.AddressEditModel;
import view.lcc.tyzs.mvp.model.AddressGetModel;
import view.lcc.tyzs.mvp.presenter.AddressEditPresenter;
import view.lcc.tyzs.mvp.presenter.AddressGetPresenter;
import view.lcc.tyzs.mvp.view.AddressEditView;
import view.lcc.tyzs.mvp.view.AddressGetView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class AddressEditPresenterImpl implements AddressEditPresenter {
    private AddressEditView view;
    private AddressEditModel model;

    public AddressEditPresenterImpl(AddressEditView view) {
        this.view = view;
        model = new AddressEditModel();
    }

    @Override
    public void addressEdit(String aid,
                           String phone,
                           String address,
                           String addressee,
                           String aphone,
                           String isdefault) {
        view.AddressEditLoading();
        model.addressAdd(aid,phone,address,addressee,aphone,isdefault,new ResultCallback<String>() {

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

                        view.AddressEditSuccess("");
                    } else  {
                        view.AddressEditFail("添加信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.AddressEditFail("添加信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
