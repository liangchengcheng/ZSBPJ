package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.JifenZhuanHuanModel;
import view.lcc.tyzs.mvp.model.JifenZhuanZhangModel;
import view.lcc.tyzs.mvp.presenter.JifenZhuanHuanPresenter;
import view.lcc.tyzs.mvp.presenter.JifenZhuanZhangPresenter;
import view.lcc.tyzs.mvp.view.JifenZhuanHuanView;
import view.lcc.tyzs.mvp.view.JifenZhuanZhangView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class JifenZhuanZhangPresenterImpl implements JifenZhuanZhangPresenter {
    private JifenZhuanZhangView view;
    private JifenZhuanZhangModel model;

    public JifenZhuanZhangPresenterImpl(JifenZhuanZhangView view) {
        this.view = view;
        model = new JifenZhuanZhangModel();
    }

    @Override
    public void jifenZhuanZhang(String aphone, String value) {
        view.JifenZhuanZhangLoading();
        model.jifenZhuanZhangModel(aphone,value,new ResultCallback<String>() {

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

                        view.JifenZhuanZhangSuccess("");
                    } else  {
                        view.JifenZhuanZhangFail("获取地址信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.JifenZhuanZhangFail("获取地址信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
