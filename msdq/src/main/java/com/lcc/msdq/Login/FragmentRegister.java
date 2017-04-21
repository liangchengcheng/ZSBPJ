package com.lcc.msdq.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lcc.msdq.R;
import com.lcc.mvp.presenter.CheckVcodePresenter;
import com.lcc.mvp.presenter.impl.CheckVcodePresenterImpl;
import com.lcc.mvp.view.CheckVcodeView;
import com.lcc.utils.CheckUtils;
import com.lcc.utils.Tools;
import com.lcc.view.EditTextWithDel;
import com.lcc.view.PaperButton;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class FragmentRegister extends Fragment implements CheckVcodeView {
    EditTextWithDel userpassword;
    PaperButton sendsmscode;
    EditTextWithDel userphone;
    EditTextWithDel smscode;
    LinearLayout fg_regist;
    RelativeLayout rela_rephone;
    RelativeLayout rela_recode;
    RelativeLayout rela_repass;
    ImageView phoneIv;
    ImageView keyIv;
    ImageView passIv;
    PaperButton nextBt;
    private View root_view;

    private CheckVcodePresenter presenter;

    private String result;

    public static FragmentRegister newInstance(String r) {
        FragmentRegister mFragment = new FragmentRegister();
        Bundle bundle = new Bundle();
        bundle.putString("result", r);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        result = getArguments().getString("result");
        super.onViewCreated(view, savedInstanceState);
    }

    private void initData(View view) {
        root_view = view.findViewById(R.id.fg_regist);
        userpassword = (EditTextWithDel) view.findViewById(R.id.userpassword);
        sendsmscode = (PaperButton) view.findViewById(R.id.send_smscode);
        userphone = (EditTextWithDel) view.findViewById(R.id.userphone);
        smscode = (EditTextWithDel) view.findViewById(R.id.smscode);
        fg_regist = (LinearLayout) view.findViewById(R.id.fg_regist);
        rela_rephone = (RelativeLayout) view.findViewById(R.id.rela_rephone);
        rela_recode = (RelativeLayout) view.findViewById(R.id.rela_recode);
        rela_repass = (RelativeLayout) view.findViewById(R.id.rela_repass);
        phoneIv = (ImageView) view.findViewById(R.id.usericon);
        keyIv = (ImageView) view.findViewById(R.id.keyicon);
        passIv = (ImageView) view.findViewById(R.id.codeicon);
        nextBt = (PaperButton) view.findViewById(R.id.next);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist, null);
        presenter = new CheckVcodePresenterImpl(this);
        SMSSDK.initSDK(getActivity(), "11cc5d753865c", "3c6cdfb8371e181a03f8a27f217e2043", true);
        EventHandler eh2 = new EventHandler() {

            @Override
            public void afterEvent(int event, int re, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = re;
                msg.obj = data;
                mHandler2.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh2);
        initData(view);
        initView();
        TextListener();
        return view;
    }

    private void TextListener() {
        //用户名改变背景变
        userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                String text = userphone.getText().toString();
                if (CheckUtils.isMobile(text)) {
                    //抖动
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
                }
            }
        });

        /**
         * 验证码改变背景变
         */
        smscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }
        });

        /**
         * 密码改变背景变
         */
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }
        });
    }

    private String code;
    private String phone;
    private String password;

    /**
     * 发送验证码点击事件
     */
    private void initView() {
        sendsmscode.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                final View view = v;
                String phone = userphone.getText().toString();
                boolean mobile = CheckUtils.isMobile(phone);
                if (!TextUtils.isEmpty(phone)) {
                    if (mobile) {
                        showVerifySuccess();
                        SMSSDK.getVerificationCode("86", phone);
                    } else {
                        rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                        phoneIv.setAnimation(Tools.shakeAnimation(2));
                        showSnackbar(view, "提示：输入手机号码");
                    }
                } else {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackbar(view, "提示：手机号码不正确");
                }

            }
        });


        //下一步的点击事件
        nextBt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                final View view = v;
                password = userpassword.getText().toString();
                code = smscode.getText().toString();
                phone = userphone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    // fg_regist.setBackgroundResource(R.color.colorAccent);
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackbar(view, "提示：请输入手机号码");

                    return;
                }
                if (!CheckUtils.isMobile(phone)) {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackbar(view, "提示：手机号不正确");
                    // fg_regist.setBackgroundResource(R.color.colorAccent);
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    keyIv.setAnimation(Tools.shakeAnimation(2));
                    // fg_regist.setBackgroundResource(R.color.colorAccent);
                    showSnackbar(view, "提示：请输入验证码");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    passIv.setAnimation(Tools.shakeAnimation(2));
                    // fg_regist.setBackgroundResource(R.color.colorAccent);
                    showSnackbar(view, "IYO提示：请输入密码");
                    return;
                }
                presenter.checkVerifySMS(phone, code);
            }
        });
    }

    @Override
    public void CheckVerifyCode(String phone, String code) {

    }

    @Override
    public void CheckVerifyCodeSuccess() {
        showSnackbar(root_view, "提示：短信验证成功成功了");
        Intent intent = new Intent(getActivity(), UserNameActivity.class);
        intent.putExtra("code", code);
        intent.putExtra("phone", phone);
        intent.putExtra("password",password);
        intent.putExtra("result",result);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade, R.anim.my_alpha_action);
        getActivity().finish();
    }

    @Override
    public void CheckVerifyCodeError(String msg) {
        showSnackbar(root_view, "提示：短信验证成功失败");
    }

    protected Handler taskHandler = new Handler();
    private int verifyCodeCountdown = 60;
    private static final int DELAY_MILLIS = 1 * 1000;

    public void showVerifySuccess() {
        sendsmscode.setClickable(false);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (verifyCodeCountdown == 0) {
                    sendsmscode.setClickable(true);
                    sendsmscode.setText("重新发送");
                    return;
                }
                sendsmscode.setText(verifyCodeCountdown + getString(R.string.msg_verify_code_point));
                verifyCodeCountdown--;
                taskHandler.postDelayed(this, DELAY_MILLIS);
            }
        }, DELAY_MILLIS);
    }


    //回收timer
    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }

    Handler mHandler2 = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    showSnackbar(root_view, "提示：短信发送成功");
                }

                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    showSnackbar(root_view, "提示：短信验证成功");
                    Intent intent = new Intent(getActivity(), UserNameActivity.class);
                    intent.putExtra("code", code);
                    intent.putExtra("phone", phone);
                    intent.putExtra("password",password);
                    intent.putExtra("result",result);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade, R.anim.my_alpha_action);
                    getActivity().finish();
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
                        rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                        keyIv.setAnimation(Tools.shakeAnimation(2));
                        showSnackbar(root_view, "提示：验证码错误");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
