package com.lcc.service;

import com.lcc.bean.News;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:    {  }
 */
public interface LoginService {

    @GET("api/v1.0/{type}/update")
    Observable<News> updateNews(@Path("type") String type);
    @GET("api/v1.0/{type}/loadmore/{newsId}")
    Call<ArrayList<News>> loadMoreNews(@Path("type") String type,@Path("newsId") String newsId );
}
