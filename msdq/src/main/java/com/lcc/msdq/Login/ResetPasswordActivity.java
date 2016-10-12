package com.lcc.msdq.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.ResetPasswordPresenter;
import com.lcc.mvp.presenter.impl.ResetPasswordPresenterImpl;
import com.lcc.mvp.view.ResetPasswordView;
import com.lcc.utils.FormValidation;
import com.lcc.utils.KeyboardUtils;
import com.lcc.utils.WidgetUtils;
import zsbpj.lccpj.frame.FrameManager;

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordView, View.OnClickListener {

    private Button mButtonSignUp;
    private EditText editText_pwd, editText_pwd2;

    private ResetPasswordPresenter mPresenter;
    private String  password, new_password;

    @Override
    protected void initView() {
        TextView iv_head = (TextView) findViewById(R.id.iv_head);
        iv_head.setText("重置密码");
        mPresenter = new ResetPasswordPresenterImpl(this);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
        editText_pwd2 = (EditText) findViewById(R.id.editText_pwd2);
        mButtonSignUp = (Button) findViewById(R.id.buttonSignUp);
        mButtonSignUp.setOnClickListener(this);
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_changepassword;
    }

    @Override
    public void onClick(View v) {
        KeyboardUtils.hide(this);
        password = editText_pwd.getText().toString();
        new_password = editText_pwd2.getText().toString();

        if (valid( password, new_password))
            return;

        switch (v.getId()) {
            case R.id.buttonSignUp:
                mPresenter.resetPassword(password, new_password);
                break;
        }
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showResetError(String msg) {

    }

    @Override
    public void showSmsSuccess() {

    }

    @Override
    public void showMsg(String msg) {

    }

    public boolean valid( String password, String new_password) {
        if (TextUtils.isEmpty(password)||TextUtils.isEmpty(new_password)) {
            WidgetUtils.requestFocus(editText_pwd);
            FrameManager.getInstance().toastPrompt("密码不能为空");
            return true;
        }
        if (!FormValidation.isSimplePassword(password)) {
            WidgetUtils.requestFocus(editText_pwd);
            FrameManager.getInstance().toastPrompt("请输入6到16位数字或者字母");
            return true;
        }
        if (!FormValidation.isSimplePassword(new_password)) {
            WidgetUtils.requestFocus(editText_pwd);
            FrameManager.getInstance().toastPrompt("请输入6到16位数字或者字母");
            return true;
        }
        if (!password.equals(new_password)) {
            WidgetUtils.requestFocus(editText_pwd);
            FrameManager.getInstance().toastPrompt("两次的密码输入不一致");
            return true;
        }
        return false;
    }

}
