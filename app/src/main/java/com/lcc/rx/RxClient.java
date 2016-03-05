package com.lcc.rx;

import com.lcc.service.LoginService;
import com.lcc.service.VideoListService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import zsbpj.lccpj.network.RetrofitBuilder;

public class RxClient {

    final LoginService loginService ;
    final VideoListService videoListService ;

    public RxClient() {
        loginService= retrofit().create(LoginService.class);
        videoListService= retrofit().create(VideoListService.class);
    }

    public LoginService getLoginClient() {
        return loginService;
    }

    public VideoListService getVideoClient() {
        return videoListService;
    }

    private static Retrofit retrofit() {
        return RetrofitBuilder.get().retrofit();
    }

}
