package com.lcc.service;

import com.lcc.bean.News;
import com.lcc.entity.MediaEntity;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:    {  }
 */
public interface LoginService {

    //登录接口
    @POST("medias/show.json/{username}/{password}")
    Observable<MediaEntity> Login(@Query("username") String username, @Query("password") String password);

    //注册接口
    @POST("medias/show.json/{username}/{password}")
    Observable<MediaEntity> Register(@Query("username") String username, @Query("password") String password);

}
