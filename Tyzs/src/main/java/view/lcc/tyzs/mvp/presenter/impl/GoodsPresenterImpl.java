package view.lcc.tyzs.mvp.presenter.impl;

import android.text.TextUtils;

import com.squareup.okhttp.Request;

import org.json.JSONObject;

import view.lcc.tyzs.base.ApiException;
import view.lcc.tyzs.frame.okhttp.callback.ResultCallback;
import view.lcc.tyzs.mvp.model.GoodsModel;
import view.lcc.tyzs.mvp.model.RegisterModel;
import view.lcc.tyzs.mvp.presenter.GoodsPresenter;
import view.lcc.tyzs.mvp.presenter.RegisterPresenter;
import view.lcc.tyzs.mvp.view.GoodsView;
import view.lcc.tyzs.mvp.view.RegisterView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |获取商品
 */
public class GoodsPresenterImpl implements GoodsPresenter {
    private GoodsView view;
    private GoodsModel model;

    public GoodsPresenterImpl(GoodsView view) {
        this.view = view;
        model = new GoodsModel();
    }

    @Override
    public void goods(String page,String pagesize,String type) {
        view.getGoodsLoading();
        model.getGoods(page,pagesize,type, new ResultCallback<String>() {

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

                        view.getGoodsSuccess("");
                    } else  {
                        view.getGoodsFail("获取信息失败，请稍后再试");
                    }
                } catch (Exception e) {
                    view.getGoodsFail("获取信息失败");
                    e.printStackTrace();
                }
            }
        });
    }

}
