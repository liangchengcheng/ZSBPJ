package com.lcc.rx.service;

import com.lcc.bean.News;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxVideoList {

    public static Subscription getVideoList(int id, int type, int page, int count) {

    Subscription subscription = RxService.getVideoListService().getVideoList(id, type, page, count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<VideoItemEntity>>() {
                @Override
                public void call(List<VideoItemEntity> video_list) {
                    RxService.getBus().post(video_list);
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    //具体错误信息的封装请草靠nba项目
                    RxService.getBus().post("错误信息");
                }
            });
    return subscription;
}
}
