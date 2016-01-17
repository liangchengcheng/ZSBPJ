package com.lcc.rx;

import com.lcc.service.LoginService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import zsbpj.lccpj.network.RetrofitBuilder;

public class RxCilent {
    final LoginService loginService ;
//    RxCilent() {
//        Retrofit retrofit0 = new Retrofit.Builder()
//                .baseUrl("http://nbaplus.sinaapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        loginService=retrofit0.create(LoginService.class);
//    }
    public RxCilent() {
        loginService= retrofit().create(LoginService.class);
    }

    private static Retrofit retrofit() {
        return RetrofitBuilder.get().retrofit();
    }

    public LoginService getCilent() {
        return loginService;
    }
}
