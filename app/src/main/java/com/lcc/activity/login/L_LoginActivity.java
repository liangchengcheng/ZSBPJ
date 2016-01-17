package com.lcc.activity.login;

import android.view.View;

import com.lcc.bean.News;
import com.lcc.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import zsbpj.lccpj.app.activity.StartNetActivity;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:    {  }
 */
public class L_LoginActivity extends StartNetActivity <ArrayList<News>>{

    @Override
    public View provideSnackbarView() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<ArrayList<News>> listCall = ApiService.createNewsService().loadMoreNews("","");
        networkQueue().enqueue(listCall);
    }

    @Override
    public void respondSuccess(ArrayList<News> data) {
        try {
            if (!isNull(data)) {
                //the data is data
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void respondWithError(Throwable t) {
    }


    private boolean isNull(List list) {
        return (null == list || list.size() == 0 || null == list.get(0));
    }
}
