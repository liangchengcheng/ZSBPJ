package com.lcc.msdq.setting;

import android.view.View;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.FeedBackPresenter;
import com.lcc.mvp.presenter.impl.FeedBackPresenterImpl;
import com.lcc.mvp.view.FeedBackView;

import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.view.simplearcloader.ArcConfiguration;
import zsbpj.lccpj.view.simplearcloader.SimpleArcDialog;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  FeedBackActivity
 */
public class FeedBackActivity extends BaseActivity implements FeedBackView, View.OnClickListener {

    private FeedBackPresenter feedBackPresenter;
    private SimpleArcDialog mDialog;

    @Override
    protected void initView() {
        feedBackPresenter = new FeedBackPresenterImpl(this);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        findViewById(R.id.tv_post).setOnClickListener(this);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.feed_back_layout;
    }

    @Override
    public void showLoading() {
        mDialog = new SimpleArcDialog(this);
        ArcConfiguration arcConfiguration = new ArcConfiguration(this);
        arcConfiguration.setText("正在登录...");
        mDialog.setConfiguration(arcConfiguration);
        mDialog.show();
    }

    @Override
    public void FeekFail(String msg) {
        FrameManager.getInstance().toastPrompt("提交失败");
        closeDialog();
    }

    @Override
    public void FeekSuccess() {
        FrameManager.getInstance().toastPrompt("提交成功");
        closeDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guillotine_hamburger:
                finish();
                break;
            case R.id.tv_post:
                feedBackPresenter.postMessage("");
                break;
        }
    }

    private void closeDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
