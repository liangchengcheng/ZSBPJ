package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.JifenListModel;
import view.lcc.tyzs.mvp.model.JifenTixianModel;
import view.lcc.tyzs.mvp.presenter.JifenListPresenter;
import view.lcc.tyzs.mvp.presenter.JifenTixianPresenter;
import view.lcc.tyzs.mvp.view.JifenListView;
import view.lcc.tyzs.mvp.view.JifenTiXianView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class JifenTixianPresenterImpl implements JifenTixianPresenter {
    private JifenTiXianView view;
    private JifenTixianModel model;

    public JifenTixianPresenterImpl(JifenTiXianView view) {
        this.view = view;
        model = new JifenTixianModel();
    }

    @Override
    public void jifenTixian(String value,
                            String name, String CONTACT,
                            String BCID,
                            String bank) {
        view.JifenTiXianLoading();
        model.jifenTixian(value,name,CONTACT,BCID,bank,new ResultCallback<String>() {

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
                        view.JifenTiXianSuccess(response);
                    } else  {
                        view.JifenTiXianFail("提交提现信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.JifenTiXianFail("提交提现信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
