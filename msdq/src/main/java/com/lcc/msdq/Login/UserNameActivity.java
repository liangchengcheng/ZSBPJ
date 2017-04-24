package com.lcc.msdq.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;

import com.lcc.App;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.MainActivity;
import com.lcc.msdq.R;
import com.lcc.msdq.choice.ChoiceTypeoneActivity;
import com.lcc.msdq.choice.VocationActivity;
import com.lcc.mvp.presenter.SignUpPresenter;
import com.lcc.mvp.presenter.impl.SignUpPresenterImpl;
import com.lcc.mvp.view.SignUpView;
import com.lcc.view.EditTextWithDel;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月19日19:40:54
 * Description:
 */
public class UserNameActivity extends FragmentActivity implements View.OnClickListener, SignUpView {

    private SignUpPresenter mPresenter;
    private EditTextWithDel user;
    private View header;

    private String phone, password, username;
    private String code;
    private String result = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        code = getIntent().getStringExtra("code");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        result = getIntent().getStringExtra("result");

        header = findViewById(R.id.header);
        mPresenter = new SignUpPresenterImpl(this);
        user = (EditTextWithDel) findViewById(R.id.user);
        findViewById(R.id.openbt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openbt:
                username = user.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    showSnackbar(header, "昵称不能为空");
                }
                if (username.length() > 32) {
                    showSnackbar(header, "昵称长度过长");
                }
                mPresenter.signUp(phone, password, code, username);
                break;
        }
    }

    @Override
    public void showVerifyError(String errorMsg) {

    }

    @Override
    public void showVerifySuccess() {

    }

    @Override
    public void showSignUpError(String msg) {
        showSnackbar(header, "注册账号失败");
    }

    @Override
    public void signUpSuccess() {
        showSnackbar(header, "注册账号成功");
        Intent intent = null;
        intent = new Intent(UserNameActivity.this, VocationActivity.class);
        intent.putExtra("result",result);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void checkToken() {

    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }
}
