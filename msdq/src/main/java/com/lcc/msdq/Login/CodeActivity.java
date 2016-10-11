package com.lcc.msdq.login;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.utils.FormValidation;
import com.lcc.utils.KeyboardUtils;
import com.lcc.utils.WidgetUtils;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import zsbpj.lccpj.frame.FrameManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2016年10月11日08:08:36
 * Description:  CodeActivity
 */
public class CodeActivity extends BaseActivity implements View.OnClickListener{
    private int verifyCodeCountdown = 60;
    protected Handler taskHandler = new Handler();
    private TextView mButtonSendVerifyCode;
    private static final int DELAY_MILLIS = 1 * 1000;
    private TextInputLayout mTextInputLayoutPhone;

    @Override
    protected void initView() {
        SMSSDK.initSDK(this, "11cc5d753865c", "3c6cdfb8371e181a03f8a27f217e2043", true);
        EventHandler eh = new EventHandler() {

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
        mButtonSendVerifyCode = (TextView) findViewById(R.id.button_send_verify_code);
        mButtonSendVerifyCode.setOnClickListener(this);
        mTextInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayout_phone);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_code;
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        boolean smart = (Boolean) data;
                        if (smart) {
                            // 通过智能验证
                        } else {
                            // 依然走短信验证
                            FrameManager.getInstance().toastPrompt("短信发送成功");
                        }
                    }
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
                        Toast.makeText(CodeActivity.this, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void showVerifySuccess() {

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
    public void onClick(View v) {
        KeyboardUtils.hide(this);
        String phone = mTextInputLayoutPhone.getEditText().getText().toString();
        if (TextUtils.isEmpty(phone)) {
            FrameManager.getInstance().toastPrompt("手机号不能为空");
            return;
        }
        if (valid(phone)){
            FrameManager.getInstance().toastPrompt("手机号格式不正确");
            return;
        }
        switch (v.getId()) {
            case R.id.button_send_verify_code:
                SMSSDK.getVerificationCode("86", phone);
                break;
        }
    }

    public boolean valid(String phone) {
        if (!FormValidation.isMobile(phone)) {
            WidgetUtils.requestFocus(mTextInputLayoutPhone.getEditText());
            FrameManager.getInstance().toastPrompt(getResources().getString(R.string.msg_error_phone));
            return true;
        }
        return false;
    }
}
