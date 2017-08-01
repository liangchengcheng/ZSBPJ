package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.JifenListModel;
import view.lcc.tyzs.mvp.model.ShopCarGetModel;
import view.lcc.tyzs.mvp.presenter.JifenListPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarGetPresenter;
import view.lcc.tyzs.mvp.view.JifenListView;
import view.lcc.tyzs.mvp.view.ShopCarGetView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class JifenListPresenterImpl implements JifenListPresenter {
    private JifenListView view;
    private JifenListModel model;

    public JifenListPresenterImpl(JifenListView view) {
        this.view = view;
        model = new JifenListModel();
    }

    @Override
    public void jifenList(String page,String type) {
        view.JifenListLoading();
        model.jifenList(page,type,new ResultCallback<String>() {

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

                        view.JifenListSuccess("");
                    } else  {
                        view.JifenListFail("获取地址信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.JifenListFail("获取地址信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
