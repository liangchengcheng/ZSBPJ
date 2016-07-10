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
import android.widget.LinearLayout;
import com.lcc.App;
import com.lcc.msdq.login.LoginActivity;
import com.lcc.msdq.login.SignUpActivity;
import com.lcc.utils.SharePreferenceUtil;
import java.util.Calendar;
import zsbpj.lccpj.frame.ImageManager;

public class SplashActivity extends Activity implements View.OnClickListener {

    private boolean have_set;
    private LinearLayout ll_bottom_view;
    private String user_tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        App.addActivity(this);

        have_set = SharePreferenceUtil.getGuide();
        user_tk = SharePreferenceUtil.getUserTk();

        ImageView ly = (ImageView) findViewById(R.id.ly);
        ll_bottom_view = (LinearLayout) findViewById(R.id.ll_bottom_view);
        findViewById(R.id.tv_toregister).setOnClickListener(this);
        findViewById(R.id.tv_tologin).setOnClickListener(this);
        FrameLayout reveal = (FrameLayout) findViewById(R.id.reveal);
        changePic(ly);
        if (!have_set || TextUtils.isEmpty(user_tk)) {
            ll_bottom_view.setVisibility(View.VISIBLE);
            SharePreferenceUtil.setGuide();
        } else {
            ll_bottom_view.setVisibility(View.GONE);
        }
        getWelcomeView(reveal);
    }

    private void changePic(ImageView ly){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 6 && hour < 12) {
            ImageManager.getInstance().loadResImage(SplashActivity.this,
                    R.drawable.morning, ly);
        } else if (hour > 12 && hour < 18) {
            ImageManager.getInstance().loadResImage(SplashActivity.this,
                    R.drawable.afternoon, ly);
        } else {
            ImageManager.getInstance().loadResImage(SplashActivity.this,
                    R.drawable.night, ly);
        }
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
        if (TextUtils.isEmpty(user_tk)){
            return;
        }
        Intent intent  = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_tologin:
                intent = new Intent(SplashActivity.this, LoginActivity.class);
                intent.putExtra("from","welcome");
                startActivity(intent);
                break;

            case R.id.tv_toregister:
                intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
