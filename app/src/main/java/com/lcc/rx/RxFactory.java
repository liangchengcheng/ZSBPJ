package com.lcc.rx;

import com.lcc.service.LoginService;
import com.lcc.service.VideoListService;

public class RxFactory {

    private static LoginService loginListService=null;
    private static VideoListService videoListService=null;

    private static final Object WATCH_DOG=new Object();

    private RxFactory(){}

    public static LoginService getLoginService() {
        synchronized (WATCH_DOG) {
            if(loginListService==null){
                RxClient rxClient = new RxClient();
                loginListService=rxClient.getLoginClient();
            }
            return loginListService;
        }
    }

    public static VideoListService getVideoListService() {
        synchronized (WATCH_DOG) {
            if(videoListService==null){
                RxClient rxClient = new RxClient();
                videoListService=rxClient.getVideoClient();
            }
            return videoListService;
        }
    }
}
