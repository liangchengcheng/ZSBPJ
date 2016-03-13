package com.lcc.rx.service;

import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;
import java.util.List;
import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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
//        return subscription;
//        List<VideoItemEntity>

        Subscription subscription = RxService.getVideoListService().getVideoList(count + "",
                page + "","1089857302","867886022105856",
                "2","38e8c5aet76d5c012e32","zh-Hans","1","8601-M02")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<VideoItemEntity>>() {

                    @Override
                    public void onCompleted() {
                        LogUtils.e("lcc","完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("lccq",e);
                    }

                    @Override
                    public void onNext(List<VideoItemEntity> videoItemEntities) {
                        LogUtils.e("lcc","完成"+videoItemEntities.size());
                        EventBus.getDefault().post(videoItemEntities);
                    }
                });
        return subscription;
    }
}
