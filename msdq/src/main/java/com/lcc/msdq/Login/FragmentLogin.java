package com.lcc.msdq.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcc.App;
import com.lcc.db.test.UserInfo;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.MainActivity;
import com.lcc.msdq.R;
import com.lcc.msdq.choice.ChoiceTypeoneActivity;
import com.lcc.msdq.compony.content.CodeFragment;
import com.lcc.mvp.presenter.LoginPresenter;
import com.lcc.mvp.presenter.impl.LoginPresenterImpl;
import com.lcc.mvp.view.LoginView;
import com.lcc.utils.CheckUtils;
import com.lcc.utils.Tools;
import com.lcc.view.EditTextWithDel;
import com.lcc.view.PaperButton;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class FragmentLogin extends Fragment implements LoginView, View.OnClickListener{
    private LoginPresenter presenter;
    private String result;

    public static FragmentLogin newInstance(String r) {
        FragmentLogin mFragment = new FragmentLogin();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        presenter = new LoginPresenterImpl(this);
        initView(view);
        initLogin();
        textListener();
        return view;
    }

    private View rl_root_view;
    private EditTextWithDel userphone;
    private EditTextWithDel userpass;
    private PaperButton bt_login;
    private ProgressBar login_progress;
    private TextView tv_forgetcode;
    private ImageView loginusericon;
    private ImageView codeicon;
    private RelativeLayout rela_name;
    private RelativeLayout rela_pass;

    private Handler handler = new Handler() {
    };

    private void initView(View view) {
        userphone = (EditTextWithDel) view.findViewById(R.id.userph);
        userpass = (EditTextWithDel) view.findViewById(R.id.userpass);
        bt_login = (PaperButton) view.findViewById(R.id.bt_login);
        login_progress = (ProgressBar) view.findViewById(R.id.login_progress);
        tv_forgetcode = (TextView) view.findViewById(R.id.tv_forgetcode);
        loginusericon = (ImageView) view.findViewById(R.id.loginusericon);
        codeicon = (ImageView) view.findViewById(R.id.codeicon);
        rela_name = (RelativeLayout) view.findViewById(R.id.rela_name);
        rela_pass = (RelativeLayout) view.findViewById(R.id.rela_pass);
        rl_root_view = view.findViewById(R.id.rl_root_view);
    }

    private void textListener() {
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
                if(CheckUtils.isMobile(text)){
                    //抖动
                    rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
                }
            }
        });
        userpass.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                //rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }
        });
    }

    private void initLogin() {
        SharedPreferences userinfo = getActivity().getSharedPreferences("userinfo", 0);
        userphone.setText(userinfo.getString("username",null));
        userpass.setText(userinfo.getString("password",null));
        bt_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                final String phone = userphone.getText().toString();
                final String passwords = userpass.getText().toString();
                final View view= v;
                if (TextUtils.isEmpty(phone)){
                    rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    loginusericon.setAnimation(Tools.shakeAnimation(2));
                    showSnackbar(v,"提示：请输入手机号码");
                    return;
                }
                if(!CheckUtils.isMobile(phone)){
                    //抖动
                    rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    loginusericon.setAnimation(Tools.shakeAnimation(2));
                    showSnackbar(v,"提示：用户名不正确");
                    return;
                }
                if (TextUtils.isEmpty(passwords)){
                    rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    codeicon.setAnimation(Tools.shakeAnimation(2));
                    showSnackbar(v,"提示：请输入密码");
                    return;
                }
                login_progress.setVisibility(View.VISIBLE);
                presenter.login(phone, passwords);
            }
        });
    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void loginSuccess() {
        rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
        rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
        showSnackbar(rl_root_view,"提示：登陆成功");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                login_progress.setVisibility(View.GONE);
                Intent intent = null;
                //直接登录
                if (!TextUtils.isEmpty(result)){
                    UserInfo userInfo = DataManager.getUserInfo();
                    if (userInfo == null || TextUtils.isEmpty(userInfo.getZy())) {
                        intent = new Intent(getActivity(), ChoiceTypeoneActivity.class);
                        intent.putExtra("result",result);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.fade,R.anim.my_alpha_action);
                        getActivity().finish();
                    } else {
                        getActivity().finish();
                    }
                }else {
                    UserInfo userInfo = DataManager.getUserInfo();
                    if (userInfo == null || TextUtils.isEmpty(userInfo.getZy())) {
                        intent = new Intent(getActivity(), ChoiceTypeoneActivity.class);
                    } else {
                        intent = new Intent(getActivity(), MainActivity.class);
                    }
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade,R.anim.my_alpha_action);
                    getActivity().finish();
                }
            }
        },1500);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showLoginFail(String msg) {
        loginFail();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loginFail(){
        login_progress.setVisibility(View.GONE);
        rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
        codeicon.setAnimation(Tools.shakeAnimation(2));
        showSnackbar(rl_root_view,"IYO提示：登陆失败");
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void checkToken() {

    }
}
