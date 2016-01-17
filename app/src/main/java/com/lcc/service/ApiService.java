/**
 * Created by YuGang Yang on September 25, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
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
