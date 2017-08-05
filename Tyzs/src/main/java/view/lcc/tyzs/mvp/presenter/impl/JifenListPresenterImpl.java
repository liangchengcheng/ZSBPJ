package view.lcc.tyzs.mvp.presenter.impl;

import android.os.Handler;
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
import view.lcc.tyzs.utils.TimeUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年08月01日07:41:07
 * Description:  |
 */
public class JifenListPresenterImpl implements JifenListPresenter {
    private JifenListView view;
    private JifenListModel model;
    private static final int DEF_DELAY = (int) (1 * 1000);

    public JifenListPresenterImpl(JifenListView view) {
        this.view = view;
        model = new JifenListModel();
    }

    private void loadData(final String page, String type, final boolean getData) {
        if (getData) {
            view.JifenListLoading();
        }
        final long current_time = TimeUtils.getCurrentTime();
        model.jifenList(page,type, new ResultCallback<String>() {

            @Override
            public void onError(Request request, Exception e) {
                view.NetWorkErr(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                int delay = 0;
                if (TimeUtils.getCurrentTime() - current_time < DEF_DELAY) {
                    delay = DEF_DELAY;
                }
                updateView(response, delay, Integer.parseInt(page), getData);
            }
        });
    }

    private void updateView(final String entities, int delay, final int page, final boolean get_data) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(entities);
                    String code = jsonObject.getString("resultno");
                    if (code.equals("000")) {
                        if (page == 1) {
                            view.refreshDataSuccess(entities);
                        } else {
                            view.loadMoreWeekDataSuccess(entities);
                        }
                    }  else {
                        if (get_data) {
                            // TODO: 2017/8/5 具体的错误信息
                            view.JifenListFail("获取信息失败");
                        } else {
                            view.refreshOrLoadFail("");
                        }
                    }
                } catch (Exception e) {
                    if (get_data) {
                        view.JifenListFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    } else {
                        view.refreshOrLoadFail(ApiException.getApiExceptionMessage(e.getMessage()));
                    }
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    @Override
    public void jifenList(String page,String type) {
        loadData(page,type,true);
    }

    @Override
    public void refresh(String page,String type) {
        loadData(page,type,false);
    }

    @Override
    public void loadMore(String page,String type) {
        loadData(page,type,false);
    }

}
