package view.lcc.wyzsb.mvp.presenter.impl;

import android.text.TextUtils;
import com.squareup.okhttp.Request;
import org.json.JSONObject;
import java.util.List;
import view.lcc.wyzsb.base.ApiException;
import view.lcc.wyzsb.bean.model.TravelingEntity;
import view.lcc.wyzsb.frame.okhttp.callback.ResultCallback;
import view.lcc.wyzsb.mvp.model.HomeFragmentModel;
import view.lcc.wyzsb.mvp.param.HomeParams;
import view.lcc.wyzsb.mvp.presenter.HomeFragmentPresenter;
import view.lcc.wyzsb.mvp.view.HomeFragmentView;
import view.lcc.wyzsb.utils.GsonUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class HomeFragmentPresenterImpl implements HomeFragmentPresenter {

    private HomeFragmentView view;

    private HomeFragmentModel model;

    public HomeFragmentPresenterImpl(HomeFragmentView view) {
        this.view = view;
        model = new HomeFragmentModel();
    }

    @Override
    public void getListData(final HomeParams params) {
        view.getLoading();
        model.getHomeFragment(params, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                view.getDataFail(ApiException.getApiExceptionMessage(e.getMessage()));
            }

            @Override
            public void onResponse(String response) {
                if (params.getPage() == 1){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String code = jsonObject.getString("status");
                        String msg = jsonObject.getString("message");
                        if (code.equals("1")) {
                            String result = jsonObject.getString("result");
                            List<TravelingEntity> orders = GsonUtils.fromJsonArray(result, TravelingEntity.class);
                            view.getDataSuccess(orders);
                        } else {
                            view.getDataFail("");
                        }
                    } catch (Exception e) {
                        view.getDataFail("");
                        e.printStackTrace();
                    }
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String code = jsonObject.getString("status");
                        String msg = jsonObject.getString("message");
                        if (TextUtils.equals(code, "1")) {
                            String result = jsonObject.getString("result");
                            List<TravelingEntity> orders = GsonUtils.fromJsonArray(result, TravelingEntity.class);
                            view.loadMoreDataSuccess(orders);
                        } else {
                            view.loadMoreDataFail(msg);
                        }
                    } catch (Exception e) {
                        view.loadMoreDataFail("");
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
