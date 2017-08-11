package view.lcc.tyzs.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.frame.Frame;

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
                intent = new Intent(SystemActivity.this,KefuActivity.class);
                startActivity(intent);
                break;
            case R.id.cacheLayout:
                Frame.getInstance().toastPrompt("清除缓存成功");
                break;
        }
    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }
}
