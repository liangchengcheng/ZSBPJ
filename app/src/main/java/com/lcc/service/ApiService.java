package com.lcc.service;

import retrofit.Retrofit;
import zsbpj.lccpj.network.RetrofitBuilder;

public class ApiService {

  public static LoginService createNewsService() {
    return retrofit().create(LoginService.class);
  }

  private static Retrofit retrofit() {
    return RetrofitBuilder.get().retrofit();
  }

}
