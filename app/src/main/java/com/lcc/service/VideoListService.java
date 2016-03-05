package com.lcc.service;

import com.lcc.bean.News;
import java.util.ArrayList;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface VideoListService {

    @GET("/channels/feed_timeline.json/{id}/{type}/{page}/{count}")
    Observable<News> updateNews(@Query("id") String id,@Query("type") String type,@Query("page") String page,@Query("count") String count);

    @GET("api/v1.0/{type}/loadmore/{newsId}")
    Call<ArrayList<News>> loadMoreNews(@Path("type") String type, @Path("newsId") String newsId );
}
