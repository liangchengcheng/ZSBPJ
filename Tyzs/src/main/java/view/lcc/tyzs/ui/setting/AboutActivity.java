package view.lcc.tyzs.ui.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月15日12:34:59
 * Description:  关于软件的界面
 */
public class AboutActivity extends BaseActivity {
    private TextView tv_version_name;

    public static void startAboutActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, AboutActivity.class);
        startingActivity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
        tv_version_name.setText("v"+getVerName(AboutActivity.this));
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo("view.lcc.wyzsb", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
