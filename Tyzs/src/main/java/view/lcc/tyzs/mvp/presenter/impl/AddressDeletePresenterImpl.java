package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.AddressDeleteModel;
import view.lcc.tyzs.mvp.model.GoodsModel;
import view.lcc.tyzs.mvp.presenter.AddressDeletePresenter;
import view.lcc.tyzs.mvp.presenter.GoodsPresenter;
import view.lcc.tyzs.mvp.view.AddressDeleteView;
import view.lcc.tyzs.mvp.view.GoodsView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |获取商品
 */
public class AddressDeletePresenterImpl implements AddressDeletePresenter {
    private AddressDeleteView view;
    private AddressDeleteModel model;

    public AddressDeletePresenterImpl(AddressDeleteView view) {
        this.view = view;
        model = new AddressDeleteModel();
    }

    @Override
    public void addressDelete(String aid,String phone) {
        view.AddressDeleteLoading();
        model.addressDelete(aid,phone, new ResultCallback<String>() {

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
                        view.AddressDeleteSuccess("");
                    } else  {
                        view.AddressDeleteFail("删除信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.AddressDeleteFail("删除信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
