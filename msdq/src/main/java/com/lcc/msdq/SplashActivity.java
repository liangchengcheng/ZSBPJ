package com.lcc.msdq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lcc.msdq.choice.ChoiceTypeoneActivity;
import com.lcc.utils.SharePreferenceUtil;

import java.util.Calendar;

import zsbpj.lccpj.frame.ImageManager;
import zsbpj.lccpj.utils.LogUtils;

public class SplashActivity extends Activity {
    private ImageView ly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ly= (ImageView) findViewById(R.id.ly);
        Calendar calendar= Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        if (hour>6&&hour<12){
            ImageManager.getInstance().loadResImage(SplashActivity.this,
                    R.drawable.morning,ly);
        }else if (hour>12&&hour<18){
            ImageManager.getInstance().loadResImage(SplashActivity.this,
                    R.drawable.afternoon,ly);
        }else {
            ImageManager.getInstance().loadResImage(SplashActivity.this,
                    R.drawable.night,ly);
        }

        FrameLayout reveal = (FrameLayout) findViewById(R.id.reveal);
        getWelcomeView(reveal);
    }

    private void getWelcomeView(View view) {
        AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
        aa.setDuration(3000);
        view.startAnimation(aa);

        aa.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                JumpNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    public void JumpNextPage() {
        boolean is_guide = SharePreferenceUtil.getGuide();
        Intent intent = null;
        if (!is_guide) {
            intent = new Intent(SplashActivity.this, GuideActivity.class);
        } else {
            String type = SharePreferenceUtil.getUserType();
            if (TextUtils.isEmpty(type)) {
                intent = new Intent(SplashActivity.this, ChoiceTypeoneActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
        }
        startActivity(intent);
        finish();
    }

}
