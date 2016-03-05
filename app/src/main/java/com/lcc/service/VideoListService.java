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

    @GET("/channels/feed_timeline.json/{id}/{type}/{page}/{count}")
    Observable<List<VideoItemEntity>> getVideoList(@Query("id") int id, @Query("type") int type, @Query("page") int page, @Query("count") int count);

    @GET("api/v1.0/{type}/loadmore/{newsId}")
    Call<ArrayList<News>> loadMoreNews(@Path("type") String type, @Path("newsId") String newsId );
}
