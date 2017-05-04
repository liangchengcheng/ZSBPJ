package view.lcc.wyzsb.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import view.lcc.wyzsb.MainActivity;
import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.frame.Frame;
import view.lcc.wyzsb.mvp.param.UserName;
import view.lcc.wyzsb.mvp.presenter.UserNamePresenter;
import view.lcc.wyzsb.mvp.presenter.impl.UserNamePresenterImpl;
import view.lcc.wyzsb.mvp.view.UserNameView;
import view.lcc.wyzsb.view.EditTextWithDel;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月19日19:40:54
 * Description:
 */
public class UserNameActivity extends BaseActivity implements View.OnClickListener, UserNameView {

    private UserNamePresenter userNamePresenter;
    private EditTextWithDel user;
    private View header;
    private String result = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        result = getIntent().getStringExtra("result");
        header = findViewById(R.id.header);
        userNamePresenter = new UserNamePresenterImpl(this);
        user = (EditTextWithDel) findViewById(R.id.user);
        findViewById(R.id.openbt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openbt:
                String username = user.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    showSnackbar(header, "昵称不能为空");
                }
                if (username.length() > 32) {
                    showSnackbar(header, "昵称长度过长");
                }
                UserName userName = new UserName();
                userName.setUsername(username);
                userNamePresenter.userName(userName);
                break;
        }
    }

    @Override
    public void UserNameLoading() {
        Frame.getInstance().toastPrompt("开始提交基本信息");
    }

    @Override
    public void UserNameSuccess(String msg) {
        showSnackbar(header, "提交昵称成功");
        if (TextUtils.isEmpty(result)) {
            Intent intent = new Intent(UserNameActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade, R.anim.my_alpha_action);
        } else {
            finish();
        }
    }

    @Override
    public void UserNameFail(String msg) {
        showSnackbar(header, "提交昵称失败");
    }

    @Override
    public void NetWorkErr(String msg) {
        showSnackbar(header, "网络发生错误");
    }
}
