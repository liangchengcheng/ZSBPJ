package com.lcc;

import android.app.Application;
import android.graphics.Typeface;

import zsbpj.lccpj.frame.FrameManager;

public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;

    @Override
    public void onCreate() {
        super.onCreate();
        FrameManager.setAppContext(this);
        FrameManager.getInstance().init();

        initTypeface();
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);

    }
}
