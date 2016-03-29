package com.lcc.rx.service;

import com.lcc.bean.News;
import com.lcc.constants.StateConstants;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;

import java.util.List;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zsbpj.lccpj.utils.LogUtils;

public class RxLogin {

    /**
     * 登录的相关操作
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    public static Subscription doLogin(String username, final String password) {
        Subscription subscription = RxService.getLoginService().Login(username, password)
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
                        EventBus.getDefault().post(resultEntity);
                    }

                    @Override
                    public void onNext(MediaEntity videoItemEntities) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setT(videoItemEntities);
                        resultEntity.setClass_tag(StateConstants.LOGIN_CLASS_TAG);
                        resultEntity.setState(StateConstants.LOAD_SUCCESS);
                        EventBus.getDefault().post(resultEntity);
                    }
                });
        return subscription;
    }

    /**
     * 注册的相关操作
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    public static Subscription doRegister(String username, final String password) {
        Subscription subscription = RxService.getLoginService().Register(username, password)
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
                        EventBus.getDefault().post(resultEntity);
                    }

                    @Override
                    public void onNext(MediaEntity videoItemEntities) {
                        ResultEntity resultEntity = new ResultEntity();
                        resultEntity.setT(videoItemEntities);
                        resultEntity.setClass_tag(StateConstants.VIDEO_CLASS_TAG);
                        resultEntity.setState(StateConstants.LOAD_SUCCESS);
                        EventBus.getDefault().post(resultEntity);
                    }
                });
        return subscription;
    }

}
