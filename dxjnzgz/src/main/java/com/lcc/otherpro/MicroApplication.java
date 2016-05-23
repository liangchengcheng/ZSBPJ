package com.lcc.otherpro;

import android.app.Application;
import android.content.Context;

public class MicroApplication extends Application {

    public static MicroApplication microApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        microApplication = this;
    }

    public static Context getContext(){
        return microApplication;
    }
}
