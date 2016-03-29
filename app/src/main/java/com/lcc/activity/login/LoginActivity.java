package com.lcc.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.lcc.activity.R;
import com.lcc.activity.video.VideoUtils;
import com.lcc.base.BaseActivity;
import com.lcc.bean.News;
import com.lcc.constants.StateConstants;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.entity.VideoItemEntity;
import com.lcc.rx.RxService;
import com.lcc.service.ApiService;
import com.lcc.view.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import me.drakeet.materialdialog.MaterialDialog;
import retrofit.Call;
import zsbpj.lccpj.app.activity.StartNetActivity;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.LogUtils;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年12月15日10:47:52
 * Description:  登录界面的简单的实现
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private MaterialDialog mMaterialDialog;
    private EditText name, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
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

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
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
        if (TextUtils.isEmpty(name.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString())) {
            FrameManager.getInstance().toastPrompt("用户名或密码不能为空");
            return;
        }
        LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this, "正在登录...");
        loadingDialog.show();
        RxService.getInstance().getLogin(getTaskId(), name.getText().toString(), password.getText().toString());
    }

    public void onEventMainThread(ResultEntity response) {
        LogUtils.e("lcc", "进入login onEventMainThread");
        if (response == null || response.getState()== StateConstants.FAIL) {
            FrameManager.getInstance().toastPrompt("登录失败，请稍后再试");
        } else {
            if (response.getClass_tag()==StateConstants.LOGIN_CLASS_TAG){
                MediaEntity bean = (MediaEntity)response.getT();
                //对bean进行一些列的操作
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
