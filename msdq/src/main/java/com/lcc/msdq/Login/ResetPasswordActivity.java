package com.lcc.msdq.Login;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.ResetPasswordPresenter;
import com.lcc.mvp.presenter.impl.ResetPasswordPresenterImpl;
import com.lcc.mvp.view.ResetPasswordView;
import com.lcc.utils.FormValidation;
import com.lcc.utils.KeyboardUtils;
import com.lcc.utils.TextWatcher;
import com.lcc.utils.WidgetUtils;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordView, View.OnClickListener {

    private static final int DELAY_MILLIS = 1000;
    private TextInputLayout mTextInputLayoutPhone;
    private TextInputLayout mTextInputLayoutPassword;
    private EditText mEditTextVerifyCode;
    private Button mButtonSendVerifyCode;
    private Button mButtonSignUp;
    private ResetPasswordPresenter mPresenter;
    private int verifyCodeCountdown = 30;
    protected Handler taskHandler = new Handler();
    private String phone,password,verify_code;

    @Override
    protected void initView() {
        SMSSDK.initSDK(this,"11cc5d753865c","3c6cdfb8371e181a03f8a27f217e2043",true);
        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
        TextView iv_head= (TextView) findViewById(R.id.iv_head);
        iv_head.setText("重置密码");
        mPresenter = new ResetPasswordPresenterImpl(this);
        mTextInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayout_phone);
        mTextInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayout_password);
        mEditTextVerifyCode = (EditText) findViewById(R.id.editText_verify_code);
        mButtonSendVerifyCode = (Button) findViewById(R.id.button_send_verify_code);
        mButtonSendVerifyCode.setOnClickListener(this);
        mButtonSignUp = (Button) findViewById(R.id.buttonSignUp);
        mButtonSignUp.setOnClickListener(this);
        mTextInputLayoutPhone.getEditText().addTextChangedListener(new TextWatcher(mTextInputLayoutPhone) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 10)
                    if (FormValidation.isMobile(getEditString())) {
                        mTextInputLayoutPhone.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
                    }
            }
        });
        mTextInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayout_password);
        mTextInputLayoutPassword.getEditText().addTextChangedListener(new TextWatcher(mTextInputLayoutPassword) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 5)
                    if (FormValidation.isSimplePassword(getEditString())) {
                        mTextInputLayoutPassword.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
                    }
            }
        });
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_signup;
    }

    @Override
    public void onClick(View v) {
        KeyboardUtils.hide(this);
        phone = mTextInputLayoutPhone.getEditText().getText().toString();
        password = mTextInputLayoutPassword.getEditText().getText().toString();
        if (valid(phone, password))
            return;
        switch (v.getId()) {
            case R.id.button_send_verify_code:
                //mPresenter.getVerifySMS(phone, password);
                SMSSDK.getVerificationCode("86",phone);
                break;
            case R.id.buttonSignUp:
                verify_code = mEditTextVerifyCode.getText().toString();
                if (FormValidation.isVerifyCode(verify_code)) {
                    //mPresenter.resetPassword(verify_code, phone, password);
                    SMSSDK.submitVerificationCode("86", phone, verify_code);
                } else {
                    WidgetUtils.requestFocus(mEditTextVerifyCode);
                }
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
        mButtonSendVerifyCode.setClickable(false);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (verifyCodeCountdown == 0) {
                    mButtonSendVerifyCode.setClickable(true);
                    mButtonSendVerifyCode.setText(R.string.msg_get_verify_code);
                    return;
                }
                mButtonSendVerifyCode.setText(verifyCodeCountdown + getString(R.string.msg_verify_code_point));
                verifyCodeCountdown--;
                taskHandler.postDelayed(this, DELAY_MILLIS);
            }
        }, DELAY_MILLIS);
    }

    @Override
    public void showMsg(String msg) {

    }

    public boolean valid(String phone, String password) {
        if (!FormValidation.isMobile(phone)) {
            WidgetUtils.requestFocus(mTextInputLayoutPhone.getEditText());
            setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
            return true;
        }
        if (!FormValidation.isSimplePassword(password)) {
            WidgetUtils.requestFocus(mTextInputLayoutPassword.getEditText());
            setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
            return true;
        }
        return false;
    }

    private void setEditTextError(TextInputLayout layout, int msgId) {
        layout.setErrorEnabled(true);
        layout.setError(getString(msgId));
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "验证成功", Toast.LENGTH_SHORT).show();
                    mPresenter.resetPassword(phone, password, verify_code);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(), "短信发送成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(ResetPasswordActivity.this, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
