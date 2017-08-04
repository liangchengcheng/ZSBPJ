package view.lcc.tyzs.ui.jifen;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.mvp.view.JifenTiXianView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:02
 * Description:  |积分提现
 */
public class JifentixianActivity extends BaseActivity implements JifenTiXianView,View.OnClickListener{

    /**
     * 金额,手机号，名字,联系方式，银行卡号,开户行
     */
    private EditText et_gold_value, et_gold_phone, et_gold_name, et_gold_contact, et_gold_backcardid, et_gold_bank;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.jifen_tixian_activity);


        et_gold_value = (EditText) findViewById(R.id.et_gold_value);

        et_gold_name = (EditText) findViewById(R.id.et_gold_name);
        et_gold_contact = (EditText) findViewById(R.id.et_gold_contact);
        et_gold_backcardid = (EditText) findViewById(R.id.et_gold_backcardid);
        et_gold_bank = (EditText) findViewById(R.id.et_gold_bank);
        findViewById(R.id.btn_edit_ok).setOnClickListener(this);
    }

    @Override
    public void JifenTiXianLoading() {
        createDialog(R.string.tx);
    }

    @Override
    public void JifenTiXianSuccess(String msg) {
        closeDialog();
    }

    @Override
    public void JifenTiXianFail(String msg) {
        closeDialog();
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_ok:
                break;
        }
    }
}
