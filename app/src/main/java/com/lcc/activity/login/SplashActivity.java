package com.lcc.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcc.activity.MainActivity;
import com.lcc.activity.R;
import com.lcc.utils.Utils;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealFrameLayout;

public class SplashActivity extends Activity {

    private Context mContext;
    private RevealFrameLayout reveal;
    private LinearLayout ly;
    private ImageView image;
    private boolean activityStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        image = (ImageView) findViewById(R.id.image);
        reveal = (RevealFrameLayout) findViewById(R.id.reveal);
        ly = (LinearLayout) findViewById(R.id.ly);

    }

    private void startCircularReveal() {
        int[] location = new int[2];
        image.getLocationOnScreen(location);
        int cx = location[0] + Utils.dpToPx(24);
        int cy = location[1] + Utils.dpToPx(24);
        int dx = Math.max(cx, ly.getWidth() - cx);
        int dy = Math.max(cy, ly.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(ly, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(2000);
        animator.start();
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            startCircularReveal();
        }
    }

}
