package view.lcc.wyzsb.ui.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.mvp.presenter.FeedBackPresenter;
import view.lcc.wyzsb.mvp.presenter.impl.FeedBackPresenterImpl;
import view.lcc.wyzsb.mvp.view.FeedBackView;
import view.lcc.wyzsb.ui.activity.video.VideoDetailsActivity;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class FeedBackActivity extends BaseActivity implements FeedBackView,View.OnClickListener{

    public static void startFeedBackActivity(Activity startingActivity, String type) {
        Intent intent = new Intent(startingActivity, FeedBackActivity.class);
        intent.putExtra("", type);
        startingActivity.startActivity(intent);
    }

    private FeedBackPresenter feedBackPresenter;
    private EditText et_message;
    private View toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_back_layout);
        toolbar = findViewById(R.id.toolbar);
        et_message = (EditText) findViewById(R.id.et_message);
        feedBackPresenter = new FeedBackPresenterImpl(this);
        findViewById(R.id.tv_post).setOnClickListener(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void FeekFail(String msg) {
        showSnackbar(toolbar,"提交信息失败");
    }

    @Override
    public void FeekSuccess() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_post:
                String message = et_message.getText().toString().trim();
                if(TextUtils.isEmpty(message)){
                    showSnackbar(toolbar,"提交的信息不能为空");
                    return;
                }
                feedBackPresenter.postMessage(message);
                break;
        }
    }
}
