package com.lcc.service;

import com.lcc.bean.News;
import com.lcc.entity.VideoItemEntity;
import java.util.ArrayList;
import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface VideoListService {

    @GET("channels/feed_timeline.json/{count}/{page}/{client_id}/{device_id}/{id}/{client_secret}/{language}/{type}/{model}")
    Observable<List<VideoItemEntity>> getVideoList(@Query("count") String count,
                                                   @Query("page") String page,
                                                   @Query("client_id") String client_id,
                                                   @Query("device_id") String device_id,
                                                   @Query("id") String id,
                                                   @Query("client_secret") String client_secret,
                                                   @Query("language") String language,
                                                   @Query("type") String type,
                                                   @Query("model") String model);

    @GET("api/v1.0/{type}/loadmore/{newsId}")
    Call<ArrayList<News>> loadMoreNews(@Path("type") String type, @Path("newsId") String newsId );
}
