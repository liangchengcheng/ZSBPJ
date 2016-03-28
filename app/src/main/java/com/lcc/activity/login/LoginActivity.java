package com.lcc.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.lcc.activity.R;
import com.lcc.base.BaseActivity;
import com.lcc.bean.News;
import com.lcc.rx.RxService;
import com.lcc.service.ApiService;
import com.lcc.view.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;
import retrofit.Call;
import zsbpj.lccpj.app.activity.StartNetActivity;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  登录界面的简单的实现
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle("用户登录");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegionActivity.class));
                break;

            case R.id.btn_login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
//        mMaterialDialog = new MaterialDialog(LoginActivity.this);
//        View view = LayoutInflater.from(LoginActivity.this)
//                .inflate(R.layout.progressbar_item,
//                        null);
//        mMaterialDialog.setCanceledOnTouchOutside(true);
//        mMaterialDialog.setView(view).show();
        LoadingDialog loadingDialog=new LoadingDialog(LoginActivity.this,"正在登录...");
        loadingDialog.show();
    }
}
