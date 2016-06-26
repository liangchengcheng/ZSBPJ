package com.lcc.msdq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.lcc.msdq.choice.ChoiceMainActivity;

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
                Intent intent = new Intent(SplashActivity.this, ChoiceMainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });

    }

}
