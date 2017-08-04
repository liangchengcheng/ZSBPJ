package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.QuerenshouhuoModel;
import view.lcc.tyzs.mvp.model.ShopCarAddModel;
import view.lcc.tyzs.mvp.presenter.QuerenshouhuoPresenter;
import view.lcc.tyzs.mvp.presenter.ShopCarAddPresenter;
import view.lcc.tyzs.mvp.view.QuerenshouhuoView;
import view.lcc.tyzs.mvp.view.ShopCarAddView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class QuerenshouhuoPresenterImpl implements QuerenshouhuoPresenter {
    private QuerenshouhuoView view;
    private QuerenshouhuoModel model;

    public QuerenshouhuoPresenterImpl(QuerenshouhuoView view) {
        this.view = view;
        model = new QuerenshouhuoModel();
    }

    @Override
    public void Querenshouhuo(String OID) {
        view.QuerenshouhuoLoading();
        model.Querenshouhuo(OID,new ResultCallback<String>() {

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

                        view.QuerenshouhuoSuccess("");
                    } else  {
                        view.QuerenshouhuoFail("添加信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.QuerenshouhuoFail("添加信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
