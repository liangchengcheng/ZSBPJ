package com.lcc.rx;

import com.google.gson.Gson;
import com.lcc.service.LoginService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import de.greenrobot.event.EventBus;
import rx.subscriptions.CompositeSubscription;

public class RxService {
    private static final RxService NBAPLUS_SERVICE=new RxService();
    private static Gson sGson;
    private static EventBus sBus ;
    private static LoginService sNbaplus;
    private static ExecutorService sSingleThreadExecutor;
    private Map<Integer,CompositeSubscription> mCompositeSubMap;
    private RxService(){}

    public void initService() {
        sBus = new EventBus();
        sGson=new Gson();
        mCompositeSubMap=new HashMap<Integer,CompositeSubscription>();
        sSingleThreadExecutor= Executors.newSingleThreadExecutor();
        backGroundInit();
    }

    private void backGroundInit() {
        sSingleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sNbaplus = RxFactory.getLoginService();
            }
        });
    }

    public void addCompositeSub(int taskId) {
        CompositeSubscription compositeSubscription;
        if(mCompositeSubMap.get(taskId)==null) {
            compositeSubscription = new CompositeSubscription();
            mCompositeSubMap.put(taskId, compositeSubscription);
        }
    }

    public void removeCompositeSub(int taskId) {
        CompositeSubscription compositeSubscription;
        if(mCompositeSubMap!=null&& mCompositeSubMap.get(taskId)!=null){
            compositeSubscription= mCompositeSubMap.get(taskId);
            compositeSubscription.unsubscribe();
            mCompositeSubMap.remove(taskId);
        }
    }



    public void initNews(int taskId,String type) {
        getCompositeSubscription(taskId).add(RxLogin.getTeams(type));
    }

    private CompositeSubscription getCompositeSubscription(int taskId) {
        CompositeSubscription compositeSubscription ;
        if(mCompositeSubMap.get(taskId)==null) {
            compositeSubscription = new CompositeSubscription();
            mCompositeSubMap.put(taskId, compositeSubscription);
        }else {
            compositeSubscription= mCompositeSubMap.get(taskId);
        }
        return compositeSubscription;
    }


    public static RxService getInstance() {
        return NBAPLUS_SERVICE;
    }

    public static EventBus getBus() {
        return sBus;
    }

    public static LoginService getNbaplus() {
        return sNbaplus;
    }

    public static Gson getGson() {
        return sGson;
    }

    public static ExecutorService getSingleThreadExecutor(){
        return sSingleThreadExecutor;
    }

}
