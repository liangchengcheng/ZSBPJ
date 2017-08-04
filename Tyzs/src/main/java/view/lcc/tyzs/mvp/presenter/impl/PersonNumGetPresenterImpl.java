package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.PersonNumGetModel;
import view.lcc.tyzs.mvp.model.ShopCarGetModel;
import view.lcc.tyzs.mvp.presenter.PersonNumGetPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarGetPresenter;
import view.lcc.tyzs.mvp.view.PersonNumGetView;
import view.lcc.tyzs.mvp.view.ShopCarGetView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |自己发展的下级的人数
 */
public class PersonNumGetPresenterImpl implements PersonNumGetPresenter {
    private PersonNumGetView view;
    private PersonNumGetModel model;

    public PersonNumGetPresenterImpl(PersonNumGetView view) {
        this.view = view;
        model = new PersonNumGetModel();
    }

    @Override
    public void PersonNumGet( String phone) {
        view.PersonNumGetLoading();
        model.PersonNumGet(phone,new ResultCallback<String>() {

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
                        view.PersonNumGetSuccess("");
                    } else  {
                        view.PersonNumGetFail("获取地址信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.PersonNumGetFail("获取地址信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
