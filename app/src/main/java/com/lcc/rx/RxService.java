package com.lcc.rx;

import com.google.gson.Gson;
import com.lcc.rx.service.RxLogin;
import com.lcc.rx.service.RxVideoList;
import com.lcc.service.LoginService;
import com.lcc.service.VideoListService;
import com.squareup.okhttp.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;
import rx.subscriptions.CompositeSubscription;

public class RxService {

    private static final RxService NBAPLUS_SERVICE = new RxService();

    private static Gson sGson;
    private static LoginService loginService;
    private static VideoListService videoListService;
    private static ExecutorService sSingleThreadExecutor;
    private Map<Integer, CompositeSubscription> mCompositeSubMap;

    private RxService() {
    }

    public void initService() {
        sGson = new Gson();
        mCompositeSubMap = new HashMap<Integer, CompositeSubscription>();
        sSingleThreadExecutor = Executors.newSingleThreadExecutor();
        backGroundInit();
    }

    private void backGroundInit() {
        sSingleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                loginService = RxFactory.getLoginService();
                videoListService = RxFactory.getVideoListService();
            }
        });
    }

    public void addCompositeSub(int taskId) {
        CompositeSubscription compositeSubscription;
        if (mCompositeSubMap.get(taskId) == null) {
            compositeSubscription = new CompositeSubscription();
            mCompositeSubMap.put(taskId, compositeSubscription);
        }
    }

    public void removeCompositeSub(int taskId) {
        CompositeSubscription compositeSubscription;
        if (mCompositeSubMap != null && mCompositeSubMap.get(taskId) != null) {
            compositeSubscription = mCompositeSubMap.get(taskId);
            compositeSubscription.unsubscribe();
            mCompositeSubMap.remove(taskId);
        }
    }

    /**
     * 登录
     */
    public void getLogin(int taskId, String username, String password) {
        getCompositeSubscription(taskId).add(RxLogin.doLogin(username, password));
    }

    /**
     * 注册
     */
    public void getRegister(int taskId, String username, String password) {
        getCompositeSubscription(taskId).add(RxLogin.doLogin(username, password));
    }

    /**
     * 获取视频列表
     */
    public void getVideoList(int taskId, int page, int count) {
        getCompositeSubscription(taskId).add(RxVideoList.getVideoList(count, page));
    }

    /**
     * 获取medias
     */
    public void getMedias(int taskId, int id) {
        getCompositeSubscription(taskId).add(RxVideoList.getMedias(id));
    }

    /**
     * 获取视频评论
     */
    public void getComments(int taskId, int id, int page) {
        getCompositeSubscription(taskId).add(RxVideoList.getComments(id, page));
    }

    /**
     * 上传个人信息带头像
     */
    public void getPostDataImage(int taskId, String filename, RequestBody requestBody, String username, String password) {
        getCompositeSubscription(taskId).add(RxLogin.doPostAllInfo(filename, requestBody, username, password));
    }

    private CompositeSubscription getCompositeSubscription(int taskId) {
        CompositeSubscription compositeSubscription;
        if (mCompositeSubMap.get(taskId) == null) {
            compositeSubscription = new CompositeSubscription();
            mCompositeSubMap.put(taskId, compositeSubscription);
        } else {
            compositeSubscription = mCompositeSubMap.get(taskId);
        }
        return compositeSubscription;
    }

    public static RxService getInstance() {
        return NBAPLUS_SERVICE;
    }

    public static Gson getGson() {
        return sGson;
    }

    public static ExecutorService getSingleThreadExecutor() {
        return sSingleThreadExecutor;
    }

    public static LoginService getLoginService() {
        return loginService;
    }

    public static VideoListService getVideoListService() {
        return videoListService;
    }


}
