package com.lcc.activity.login;

import android.view.View;

import com.lcc.bean.News;
import com.lcc.entity.MediaEntity;
import com.lcc.rx.RxService;
import com.lcc.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import rx.Observable;
import zsbpj.lccpj.app.activity.StartNetActivity;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  登录界面的简单的实现
 */
public class LoginActivity1 extends StartNetActivity <ArrayList<News>>{

    @Override
    public View provideSnackbarView() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Observable<MediaEntity> login = ApiService.createNewsService().Login("", "");
        //networkQueue().enqueue(listCall);
        //RxService.getInstance().Login(getTaskId(), "");
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
        return (null == list || list.size() == 0);
    }
}
