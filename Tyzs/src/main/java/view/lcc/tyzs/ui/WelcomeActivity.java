package view.lcc.tyzs.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import view.lcc.tyzs.R;
import view.lcc.tyzs.ui.home.MainActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月21日09:38:13
 * Description:  欢迎界面
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        initView();
    }

    private void initView() {
        View view = findViewById(R.id.iv_Launcher);
        getWelcomeView(view);
    }

    private void getWelcomeView(View view) {
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(3000);
        view.startAnimation(aa);

        aa.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
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
