package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.JifenTixianModel;
import view.lcc.tyzs.mvp.model.JifenYueModel;
import view.lcc.tyzs.mvp.presenter.JifenTixianPresenter;
import view.lcc.tyzs.mvp.presenter.JifenYuePresenter;
import view.lcc.tyzs.mvp.view.JifenTiXianView;
import view.lcc.tyzs.mvp.view.JifenYueView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class JifenYuePresenterImpl implements JifenYuePresenter {
    private JifenYueView view;
    private JifenYueModel model;

    public JifenYuePresenterImpl(JifenYueView view) {
        this.view = view;
        model = new JifenYueModel();
    }

    @Override
    public void jifenYue() {
        view.JifenYueLoading();
        model.jifenYue(new ResultCallback<String>() {

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
                        view.JifenYueSuccess(response);
                    } else  {
                        view.JifenYueFail("获取地址信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.JifenYueFail("获取地址信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
