package view.lcc.tyzs.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import view.lcc.tyzs.R;
import view.lcc.tyzs.mvp.presenter.RegisterPresenter;
import view.lcc.tyzs.mvp.presenter.impl.RegisterPresenterImpl;
import view.lcc.tyzs.mvp.view.RegisterView;
import view.lcc.tyzs.ui.home.MainActivity;
import view.lcc.tyzs.utils.CheckUtils;
import view.lcc.tyzs.utils.Tools;
import view.lcc.tyzs.view.EditTextWithDel;
import view.lcc.tyzs.view.PaperButton;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月28日16:24:47
 * Description:  注册相关的界面
 */
public class FragmentRegister extends Fragment implements RegisterView {
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

    private String code;
    private String phone;
    private String password;


    private RegisterPresenter registerPresenter;
    private String flag = "";

    public static FragmentRegister newInstance(String r) {
        FragmentRegister mFragment = new FragmentRegister();
        Bundle bundle = new Bundle();
        bundle.putString("result", r);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        flag = getArguments().getString("result");
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
        View view = inflater.inflate(R.layout.register_fragment, null);
        registerPresenter = new RegisterPresenterImpl(this);
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

            @Override
            public void afterTextChanged(Editable s) {
                String text = userphone.getText().toString();
                if (CheckUtils.isMobile(text)) {
                    //抖动
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
                }
            }
        });
        //验证码改变背景变
        smscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }
        });
        //密码改变背景变
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }
        });
    }



    private void initView() {
        //下一步的点击事件
        nextBt.setOnClickListener(new View.OnClickListener() {
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
                    showSnackbar(view, "提示：请输入密码");
                    return;
                }

            }
        });
    }

    @Override
    public void RegisterLoading() {
        //正在注册
    }

    @Override
    public void RegisterSuccess(String msg) {
        //注册成功
        showSnackbar(root_view, "提示：注册账号成功");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("code", code);
        intent.putExtra("phone", phone);
        intent.putExtra("password", password);
        intent.putExtra("result", flag);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void RegisterFail(String msg) {
        //注册失败
        showSnackbar(root_view, "提示：注册账号失败");
    }

    @Override
    public void NetWorkErr(String msg) {
        //网络错误
        showSnackbar(root_view, "提示：网络错误，请稍后");
    }


    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
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
                sendsmscode.setText(verifyCodeCountdown + "秒后获取");
                verifyCodeCountdown--;
                taskHandler.postDelayed(this, DELAY_MILLIS);
            }
        }, DELAY_MILLIS);
    }

    //回收timer
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
