package com.lcc.activity.video;


public interface PlayVideoPresenter {

    void getMedia(int id);

    void refresh(int page);

    void loadMore(int id, int page);

    void createLikeVideo(int id);

    void destoryLikeVideo(int id);


    void createLikeComment(int id);

    void destoryLikeComment(int id);

}
