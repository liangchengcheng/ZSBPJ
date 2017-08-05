package view.lcc.tyzs.ui.jifen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.JifenZhuanZhangPresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenZhuanZhangPresenterImpl;
import view.lcc.tyzs.mvp.view.JifenZhuanZhangView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:05
 * Description:  |积分转账
 */
public class JifenzhuanzhangActivity extends BaseActivity implements JifenZhuanZhangView,View.OnClickListener{
    private JifenZhuanZhangPresenter jifenZhuanZhangPresenter;

    private Button btn_edit_ok;
    private EditText et_gold_name, et_gold_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifenz_zhuanzhang_activity);

        et_gold_name = (EditText) findViewById(R.id.et_gold_name);
        et_gold_value = (EditText) findViewById(R.id.et_gold_value);
        findViewById(R.id.btn_edit_ok).setOnClickListener(this);

        jifenZhuanZhangPresenter = new JifenZhuanZhangPresenterImpl(this);
    }

    @Override
    public void JifenZhuanZhangLoading() {
        createDialog(R.string.zz);
    }

    @Override
    public void JifenZhuanZhangSuccess(String msg) {
        closeDialog();
    }

    @Override
    public void JifenZhuanZhangFail(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("提交转账信息失败");
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不稳定，请稍微再试");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_ok:
                if (!TextUtils.isEmpty(et_gold_name.getText()) && !TextUtils.isEmpty(et_gold_value.getText())) {
                    jifenZhuanZhangPresenter.jifenZhuanZhang( et_gold_name.getText().toString(),et_gold_value.getText().toString());
                }else {
                    Frame.getInstance().toastPrompt("转账信息不能为空");
                }
                break;
        }
    }
}
