package com.lcc.activity.video;

import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;

import java.util.List;

public interface PlayVideoView {

    void showMediaData(MediaEntity mediaEntity);

    void showLoadMediaError();

    void refreshComment(List<CommentEntity> dataList);

    void showMoreComments(List<CommentEntity> dataList);
}
