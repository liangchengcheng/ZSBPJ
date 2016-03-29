package com.lcc.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lcc.activity.R;
import com.lcc.base.BaseActivity;
import com.lcc.constants.StateConstants;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.rx.RxService;
import com.lcc.view.dialog.LoadingDialog;

import de.greenrobot.event.EventBus;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.LogUtils;

public class RegionActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username, et_pwd, new_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        new_pwd = (EditText) findViewById(R.id.et_newpassword);

    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_region;
    }

    @Override
    public void onClick(View v) {

    }

    private void toRegister() {
        String pwd1 = et_pwd.getText().toString();
        String pwd2 = new_pwd.getText().toString();
        if (TextUtils.isEmpty(et_username.getText().toString()) ||
                TextUtils.isEmpty(pwd1) ||
                TextUtils.isEmpty(pwd2)) {
            FrameManager.getInstance().toastPrompt("输入信息不能为空");
            return;
        }

        if (!pwd1.equals(pwd2)) {
            FrameManager.getInstance().toastPrompt("输入的两次密码不一致");
            return;
        }

        LoadingDialog loadingDialog = new LoadingDialog(RegionActivity.this, "正在注册，请稍后...");
        loadingDialog.show();
        RxService.getInstance().getRegister(getTaskId(), et_username.getText().toString(), pwd1);
    }

    public void onEventMainThread(ResultEntity response) {
        LogUtils.e("lcc", "进入login onEventMainThread");
        if (response == null || response.getState() == StateConstants.FAIL) {
            FrameManager.getInstance().toastPrompt("注册失败，请稍后再试");
        } else {
            if (response.getClass_tag() == StateConstants.REGISTER_CLASS_TAG) {
                MediaEntity bean = (MediaEntity) response.getT();
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
