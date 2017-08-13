package view.lcc.tyzs.ui.jifen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.base.BaseApplication;
import view.lcc.tyzs.bean.Note;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.JifenTixianPresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenTixianPresenterImpl;
import view.lcc.tyzs.mvp.view.JifenTiXianView;
import view.lcc.tyzs.utils.ErrorLogUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:02
 * Description:  |积分提现
 */
public class JifentixianActivity extends BaseActivity implements JifenTiXianView, View.OnClickListener {

    /**
     * 金额,手机号，名字,联系方式，银行卡号,开户行
     */
    private EditText et_gold_value, et_gold_name, et_gold_contact, et_gold_backcardid, et_gold_bank;

    private JifenTixianPresenter jifenTixianPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifen_tixian_activity);
        jifenTixianPresenter = new JifenTixianPresenterImpl(this);

        et_gold_value = (EditText) findViewById(R.id.et_gold_value);
        et_gold_name = (EditText) findViewById(R.id.et_gold_name);
        et_gold_contact = (EditText) findViewById(R.id.et_gold_contact);
        et_gold_backcardid = (EditText) findViewById(R.id.et_gold_backcardid);
        et_gold_bank = (EditText) findViewById(R.id.et_gold_bank);
        findViewById(R.id.btn_edit_ok).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void JifenTiXianLoading() {
        createDialog(R.string.tx);
    }

    @Override
    public void JifenTiXianSuccess(String result) {
        closeDialog();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject object = new JSONObject(jsonObject.getString("resultjson"));

            Note note = new Note();
            note.setID(UUID.randomUUID().toString());
            note.setTime(getCurrentTime());
            note.setType("积分提现");
            note.setSpno(object.getString("SPNO"));
            note.setState("成功");
            note.setChangeValue(et_gold_value.getText().toString());
            note.setBalance(object.getString("balance"));
            BaseApplication.getDaoSession().getNoteDao().insert(note);
            Frame.getInstance().toastPrompt("提交申请信息成功");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(JifentixianActivity.this,JifenShenqingActivity.class));
                    finish();
                }
            },1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void JifenTiXianFail(String msg) {
        closeDialog();
        String message = ErrorLogUtils.SystemError(msg);
        if (!TextUtils.isEmpty(message)){
            Frame.getInstance().toastPrompt(message);
        }else {
            Frame.getInstance().toastPrompt("提现失败，请稍后再试");
        }
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不通畅，请稍后再试");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_ok:
                if (!TextUtils.isEmpty(et_gold_value.getText()) &&
                        !TextUtils.isEmpty(et_gold_name.getText()) &&
                        !TextUtils.isEmpty(et_gold_contact.getText()) &&
                        !TextUtils.isEmpty(et_gold_backcardid.getText()) &&
                        !TextUtils.isEmpty(et_gold_bank.getText())) {
                    jifenTixianPresenter.jifenTixian(
                            et_gold_value.getText().toString(),
                            et_gold_name.getText().toString(),
                            et_gold_contact.getText().toString(),
                            et_gold_backcardid.getText().toString(),
                            et_gold_bank.getText().toString());
                } else {
                    Frame.getInstance().toastPrompt("信息不完整");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
