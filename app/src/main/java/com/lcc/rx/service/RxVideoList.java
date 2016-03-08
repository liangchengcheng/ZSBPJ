package com.lcc.rx.service;

import com.lcc.bean.News;
import com.lcc.entity.TestEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zsbpj.lccpj.utils.LogUtils;

public class RxVideoList {

    public static Subscription getVideoList(int count, int page) {

//    Subscription subscription = RxService.getVideoListService().getVideoList(count+"",page+"")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Action1<List<VideoItemEntity>>() {
//                @Override
//                public void call(List<VideoItemEntity> video_list) {
//                    RxService.getBus().post(video_list);
//                }
//            }, new Action1<Throwable>() {
//                @Override
//                public void call(Throwable throwable) {
//                    //具体错误信息的封装请参考nba项目
//                    RxService.getBus().post("错误信息");
//                }
//            });
//    return subscription;
//        List<VideoItemEntity>
        Subscription subscription = RxService.getVideoListService().getVideoList(count + "", page + "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TestEntity>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<TestEntity> videoItemEntities) {
                        LogUtils.e("lccq",videoItemEntities);
                    }
                });
        return subscription;
    }
}
