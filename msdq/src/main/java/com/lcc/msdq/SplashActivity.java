package com.lcc.msdq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.lcc.msdq.choice.ChoiceTypeoneActivity;
import com.lcc.utils.SharePreferenceUtil;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
