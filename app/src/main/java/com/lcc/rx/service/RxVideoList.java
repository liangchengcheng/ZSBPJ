package com.lcc.rx.service;

import com.lcc.constants.StateConstants;
import com.lcc.entity.ResultEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;
import java.util.List;
import de.greenrobot.event.EventBus;
import retrofit.http.PATCH;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zsbpj.lccpj.utils.LogUtils;

public class RxVideoList {

    public static Subscription getVideoList(int count, final int page) {
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
                        ResultEntity resultEntity=new ResultEntity();
                        resultEntity.setState(StateConstants.FAIL);
                        EventBus.getDefault().post(resultEntity);
                    }

                    @Override
                    public void onNext(List<VideoItemEntity> videoItemEntities) {
                        ResultEntity resultEntity=new ResultEntity();
                        resultEntity.setT(videoItemEntities);
                        if (page==1){
                            resultEntity.setState(StateConstants.REFRESH_SUCCESS);
                        }else {
                            resultEntity.setState(StateConstants.LOAD_SUCCESS);
                        }
                        EventBus.getDefault().post(resultEntity);
                    }
                });
        return subscription;
    }
}
