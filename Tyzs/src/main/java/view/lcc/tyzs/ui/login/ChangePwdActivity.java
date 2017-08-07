package view.lcc.tyzs.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.ChangePwdPresenter;
import view.lcc.tyzs.mvp.presenter.impl.ChangePwdPresenterImpl;
import view.lcc.tyzs.mvp.view.ChangePwdView;
import view.lcc.tyzs.utils.Md5Utils;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 08:31
 * Description:  |
 */
public class ChangePwdActivity extends BaseActivity implements ChangePwdView ,View.OnClickListener{

    private ChangePwdPresenter changePwdPresenter;
    private EditText passwod;
    private EditText newpassword;
    private EditText newpassword_new;

    private String save_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);
        changePwdPresenter = new ChangePwdPresenterImpl(this);
        findViewById(R.id.btn_changepassword).setOnClickListener(this);
        newpassword_new = (EditText) findViewById(R.id.newpassword_new);
        passwod = (EditText) findViewById(R.id.passwod);
        newpassword = (EditText) findViewById(R.id.newpassword);
        save_pwd = SharePreferenceUtil.getPwd();
    }

    @Override
    public void ChangePwdLoading() {
        createDialog(R.string.send_info);
    }

    @Override
    public void ChangePwdSuccess(String msg) {
        closeDialog();
    }

    @Override
    public void ChangePwdFail(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("提交失败，请稍后再试");
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不稳定，请稍后再试");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_changepassword:
                if (TextUtils.isEmpty(passwod.getText().toString())|| TextUtils.isEmpty(newpassword.getText().toString()) ||
                       TextUtils.isEmpty(newpassword_new.getText().toString())) {
                    Frame.getInstance().toastPrompt("密码不能为空");
                    return;
                }
                if (passwod.getText().toString().length() < 6 || newpassword.getText().toString().length() < 6 ||
                        newpassword_new.getText().length() < 6) {
                    Frame.getInstance().toastPrompt("密码长度为6位");
                    return;
                }

                if (!newpassword.getText().toString().equals(newpassword_new.getText().toString())){
                    Frame.getInstance().toastPrompt("两次密码长度不一致");
                    return;
                }
                if (!save_pwd.equals(passwod.getText().toString())) {
                    Frame.getInstance().toastPrompt("原始密码输入错误");
                    return;
                }
                changePwdPresenter.changePwd(newpassword.getText().toString().trim(),SharePreferenceUtil.getName());
                break;
        }
    }
}
