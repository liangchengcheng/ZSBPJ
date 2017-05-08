package view.lcc.wyzsb.mvp.presenter;

import view.lcc.wyzsb.bean.Video;

public interface VideoDetailsPresenter {

    void getData(String vid);

    void favVideo(Video video);

    void historyVideo(Video video);

}
