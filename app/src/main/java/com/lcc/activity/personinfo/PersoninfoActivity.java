package com.lcc.activity.personinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.lcc.activity.R;
import com.lcc.base.BaseActivity;
import com.lcc.constants.StateConstants;
import com.lcc.entity.MediaEntity;
import com.lcc.entity.ResultEntity;
import com.lcc.rx.RxService;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;

import de.greenrobot.event.EventBus;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.utils.LogUtils;

public class PersonInfoActivity extends BaseActivity {

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
        toolbar.setTitle("个人信息");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_info;
    }

    private void postDataImage() {

        File file = new File("/storage/emulated/0/sc/share.png");
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        RxService.getInstance().getPostDataImage(getTaskId(), "head_image", requestBody, "name", "password");

    }

    public void onEventMainThread(ResultEntity response) {
        LogUtils.e("lcc", "进入login onEventMainThread");
        if (response == null || response.getState() == StateConstants.FAIL) {
            FrameManager.getInstance().toastPrompt("登录失败，请稍后再试");
        } else {
            if (response.getClass_tag() == StateConstants.LOGIN_CLASS_TAG) {
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
