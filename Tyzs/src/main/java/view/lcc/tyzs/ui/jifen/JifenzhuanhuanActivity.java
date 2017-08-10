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
import view.lcc.tyzs.mvp.presenter.JifenZhuanHuanPresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenZhuanHuanPresenterImpl;
import view.lcc.tyzs.mvp.view.JifenZhuanHuanView;
import view.lcc.tyzs.utils.ErrorLogUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 22:06
 * Description:  |
 */
public class JifenzhuanhuanActivity extends BaseActivity implements JifenZhuanHuanView, View.OnClickListener {

    private JifenZhuanHuanPresenter jifenZhuanHuanPresenter;
    private EditText et_gold_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifenz_zhuanhuan_activity);
        jifenZhuanHuanPresenter = new JifenZhuanHuanPresenterImpl(this);
        et_gold_value = (EditText) findViewById(R.id.et_gold_value);
        findViewById(R.id.btn_edit_ok).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void JifenZhuanRangLoading() {
        createDialog(R.string.zh);
    }

    @Override
    public void JifenZhuanRangSuccess(String result) {
        closeDialog();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject object = new JSONObject(jsonObject.getString("resultjson"));

            Note note = new Note();
            note.setID(UUID.randomUUID().toString());
            note.setTime(getCurrentTime());
            note.setType("积分转换");
            note.setSpno(object.getString("SPNO"));
            note.setState("成功");
            note.setChangeValue(et_gold_value.getText().toString());
            note.setBalance(object.getString("balance"));
            BaseApplication.getDaoSession().getNoteDao().insert(note);
            Frame.getInstance().toastPrompt("提交转换申请成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    @Override
    public void JifenZhuanRangFail(String msg) {
        closeDialog();
        String message = ErrorLogUtils.SystemError(msg);
        if (!TextUtils.isEmpty(message)){
            Frame.getInstance().toastPrompt(message);
        }else {
            Frame.getInstance().toastPrompt("提交申请失败，请稍微再试");
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
                if (!TextUtils.isEmpty(et_gold_value.getText())) {
                    jifenZhuanHuanPresenter.jifenZhuanHuan(et_gold_value.getText().toString());
                } else {
                    Frame.getInstance().toastPrompt("转换积分值不能为空");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
