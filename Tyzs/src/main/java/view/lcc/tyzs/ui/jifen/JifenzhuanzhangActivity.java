package view.lcc.tyzs.ui.jifen;

import android.os.Bundle;
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
import view.lcc.tyzs.mvp.presenter.JifenZhuanZhangPresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenZhuanZhangPresenterImpl;
import view.lcc.tyzs.mvp.view.JifenZhuanZhangView;
import view.lcc.tyzs.utils.ErrorLogUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:05
 * Description:  |积分转账
 */
public class JifenzhuanzhangActivity extends BaseActivity implements JifenZhuanZhangView, View.OnClickListener {
    private JifenZhuanZhangPresenter jifenZhuanZhangPresenter;

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
    public void JifenZhuanZhangSuccess(String result) {
        closeDialog();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject object = new JSONObject(jsonObject.getString("resultjson"));

            Note note = new Note();
            note.setID(UUID.randomUUID().toString());
            note.setTime(getCurrentTime());
            note.setType("积分转账");
            note.setSpno(object.getString("SPNO"));
            note.setState("成功");
            note.setChangeValue(et_gold_value.getText().toString());
            note.setBalance(object.getString("balance"));
            BaseApplication.getDaoSession().getNoteDao().insert(note);
            Frame.getInstance().toastPrompt("提交转账信息成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void JifenZhuanZhangFail(String msg) {
        closeDialog();
        String message = ErrorLogUtils.SystemError(msg);
        if (!TextUtils.isEmpty(message)){
            Frame.getInstance().toastPrompt(message);
        }else {
            Frame.getInstance().toastPrompt("提交转账信息失败");
        }
    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不稳定，请稍微再试");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_ok:
                if (!TextUtils.isEmpty(et_gold_name.getText()) && !TextUtils.isEmpty(et_gold_value.getText())) {
                    jifenZhuanZhangPresenter.jifenZhuanZhang(et_gold_name.getText().toString(), et_gold_value.getText().toString());
                } else {
                    Frame.getInstance().toastPrompt("转账信息不能为空");
                }
                break;
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
