package com.lcc.rx.service;

import com.lcc.constants.StateConstants;
import com.lcc.entity.CommentEntity;
import com.lcc.entity.MediaEntity;
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

    /**
     * 获取视频列表
     *
     * @param count 数量
     * @param page  页码
     * @return Subscription
     */
    public static Subscription getVideoList(int count, final int page) {
        Subscription subscription = RxService.getVideoListService().getVideoList(count + "",
                page + "", "1089857302", "867886022105856",
                "2", "38e8c5aet76d5c012e32", "zh-Hans", "1", "8601-M02")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<VideoItemEntity>>() {

                    @Override
                    public void onCompleted() {
                        LogUtils.e("lcc", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setState(StateConstants.FAIL);
                        EventBus.getDefault().post(resultEntity);
                    }

                    @Override
                    public void onNext(List<VideoItemEntity> videoItemEntities) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setT(videoItemEntities);
                        if (page == 1) {
                            resultEntity.setState(StateConstants.REFRESH_SUCCESS);
                        } else {
                            resultEntity.setState(StateConstants.LOAD_SUCCESS);
                        }
                        EventBus.getDefault().post(resultEntity);
                    }
                });
        return subscription;
    }

    /**
     * 获取Medias列表
     *
     * @param id id
     * @return Subscription
     */
    public static Subscription getMedias(int id) {

        Subscription subscription = RxService.getVideoListService().getMedias(id + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MediaEntity>() {

                    @Override
                    public void onCompleted() {
                        LogUtils.e("lcc", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setState(StateConstants.FAIL);
                        resultEntity.setCode(1);
                        EventBus.getDefault().post(resultEntity);
                    }

                    @Override
                    public void onNext(MediaEntity mediaEntity) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setT(mediaEntity);
                        resultEntity.setCode(1);
                        EventBus.getDefault().post(resultEntity);
                    }
                });
        return subscription;
    }

    /**
     * 获取评论列表
     *
     * @param id id
     * @param page  页码
     * @return Subscription
     */
    public static Subscription getComments(int id, final int page) {

        Subscription subscription = RxService.getVideoListService().getComments(id + "",
                page + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CommentEntity>>() {

                    @Override
                    public void onCompleted() {
                        LogUtils.e("lcc", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setState(StateConstants.FAIL);
                        resultEntity.setCode(0);
                        EventBus.getDefault().post(resultEntity);
                    }

                    @Override
                    public void onNext(List<CommentEntity> commentEntities) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setT(commentEntities);
                        resultEntity.setCode(0);
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
