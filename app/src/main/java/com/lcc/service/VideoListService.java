package com.lcc.service;


import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.VideoItemEntity;
import java.util.List;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface VideoListService {
    //获取视频列表
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

    //获取dedias
    @GET("medias/show.json/{id}")
    Observable<MediaEntity> getMedias(@Query("id") String id);

    //获取dedias
    @GET("comments/show.json/{id}/{page}")
    Observable<List<CommentEntity>> getComments(@Query("id") String id, @Query("page") String page);
}
