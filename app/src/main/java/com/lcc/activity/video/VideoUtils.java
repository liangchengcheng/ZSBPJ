package com.lcc.activity.video;

import com.lcc.entity.MediaEntity;
import com.lcc.entity.VideoItemEntity;
import java.util.ArrayList;
import java.util.List;


public class VideoUtils {

    public static List<MediaEntity> toMediaList(List<VideoItemEntity> dataList) {
        List<MediaEntity> data = new ArrayList<>();
        for (VideoItemEntity entity : dataList) {
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setId(entity.getMedia().getId());
            mediaEntity.setUser(entity.getMedia().getUser());
            mediaEntity.setLikes_count(entity.getMedia().getLikes_count());
            mediaEntity.setCaption(entity.getRecommend_caption());
            mediaEntity.setCover_pic(entity.getRecommend_cover_pic());
            data.add(mediaEntity);
        }
        return data;
    }
}
