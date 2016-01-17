package com.lcc.rx;

import com.lcc.service.LoginService;

public class RxFactory {
    private static LoginService sInstance=null;
    private static final Object WATCH_DOG=new Object();

    private RxFactory(){}

    public static LoginService getLoginService() {
        synchronized (WATCH_DOG) {
            if(sInstance==null){
                RxCilent rxCilent = new RxCilent();
                sInstance=rxCilent.getCilent();
            }
            return sInstance;
        }
    }
}
