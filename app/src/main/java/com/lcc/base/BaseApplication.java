package com.lcc.base;

import android.app.Application;

import com.lcc.constants.AppConstants;
import com.lcc.rx.RxService;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.network.RetrofitBuilder;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:   BaseApplication
 */
public class BaseApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        FrameManager.setAppContext(this);
        FrameManager.getInstance().init();

        new RetrofitBuilder.Builder()
                .baseUrl(AppConstants.RequestPath.BASE_URL)
                .client(new OkHttpClient())
                .build();

        RxService.getInstance().initService();
    }
}
