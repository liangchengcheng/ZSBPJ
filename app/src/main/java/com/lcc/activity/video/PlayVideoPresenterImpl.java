package com.lcc.activity.video;

import android.app.Activity;
import android.os.Handler;
import com.google.gson.JsonElement;
import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;
import com.lcc.rx.RxService;
import com.squareup.okhttp.Request;
import java.util.List;

public class PlayVideoPresenterImpl implements PlayVideoPresenter {
    private static final int DEF_DELAY = 1000;
    private PlayVideoView view;
    private Activity activity;

    public PlayVideoPresenterImpl(PlayVideoView view,Activity activity) {
        this.view = view;
        this.activity=activity;
    }

    @Override
    public void getMedia(int id) {
        RxService.getInstance().getMedias(activity.getTaskId(),  id);
    }

    private void getComments(int id, final int page) {
        RxService.getInstance().getComments(activity.getTaskId(),  id,page);
        //updateView(response, delay, page);
    }

    private void updateView(final List<CommentEntity> commentEntityList, int delay, final int page) {
        //定时器延时刷新接界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page == 1) {
                    view.refreshComment(commentEntityList);
                } else {
                    view.showMoreComments(commentEntityList);
                }
            }
        }, delay);
    }

    @Override
    public void refresh(int id) {
        getComments(id, 1);
    }

    @Override
    public void loadMore(int id, int page) {
        getComments(id, page);
    }

    @Override
    public void createLikeVideo(int id) {
    }

    @Override
    public void destoryLikeVideo(int id) {

    }

    @Override
    public void createLikeComment(int id) {
    }

    @Override
    public void destoryLikeComment(int id) {
    }
}
