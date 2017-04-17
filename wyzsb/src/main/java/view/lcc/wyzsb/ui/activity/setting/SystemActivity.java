package view.lcc.wyzsb.ui.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.frame.DataCleanManager;
import view.lcc.wyzsb.utils.SharePreferenceUtil;
import view.lcc.wyzsb.utils.TimeUtils;
import view.lcc.wyzsb.utils.UpdateApkTask;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class SystemActivity extends BaseActivity implements View.OnClickListener{

    private View toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_layout);

        TextView code = (TextView) findViewById(R.id.version_tv);
        code.setText("v"+getVerName(SystemActivity.this));
        toolBar = findViewById(R.id.toolBar);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.about).setOnClickListener(this);
        findViewById(R.id.checkUpgrade).setOnClickListener(this);
        findViewById(R.id.cacheLayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.about:
                intent = new Intent(SystemActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.checkUpgrade:
                update();
                break;
            case R.id.cacheLayout:
                DataCleanManager.cleanSharedPreference(SystemActivity.this);
                showSnackbar(toolBar,"清除缓存成功");
                break;
        }
    }

    private void update(){
        String updateTime = SharePreferenceUtil.getUpdateTime();
        if (!TextUtils.isEmpty(updateTime)) {
            String localtime = TimeUtils.StrTime(System.currentTimeMillis());
            if ((Long.parseLong(localtime) - Long.parseLong(updateTime)) / (60 * 60 * 24) > 7) {
                updateAPK();
            }
        } else {
            updateAPK();
        }
    }

    public void updateAPK() {
        UpdateApkTask task = new UpdateApkTask(SystemActivity.this, false);
        task.detectionVersionInfo();
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo("com.hdsx.dtjc", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }
}
