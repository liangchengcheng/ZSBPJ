package com.lcc.service;

import com.lcc.bean.News;
import com.lcc.constants.AppConstants;
import com.lcc.entity.TestEntity;
import com.lcc.entity.VideoItemEntity;
import java.util.ArrayList;
import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface VideoListService {

    @GET("hot/feed_timeline.json/{count}/{page}")
    Observable<List<TestEntity>> getVideoList(@Query("count") String count, @Query("page") String page);

    @GET("api/v1.0/{type}/loadmore/{newsId}")
    Call<ArrayList<News>> loadMoreNews(@Path("type") String type, @Path("newsId") String newsId );
}
