package com.lcc.msdq.test;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.lcc.base.BaseActivity;
import com.lcc.entity.QuestionAdd;
import com.lcc.frame.Propertity;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.R;
import com.lcc.msdq.test.choice.VocationTestActivity;
import com.lcc.mvp.presenter.QuestionAddPresenter;
import com.lcc.mvp.presenter.impl.QuestionAddPresenterImpl;
import com.lcc.mvp.view.QuestionAddView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import zsbpj.lccpj.view.simplearcloader.ArcConfiguration;
import zsbpj.lccpj.view.simplearcloader.SimpleArcDialog;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  TestAddActivity
 */
public class TestAddActivity extends BaseActivity implements QuestionAddView, View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {
    private String zy;
    private List<File> files = new ArrayList<>();
    private QuestionAddPresenter presenter;
    private QuestionAdd questionAdd;

    private View root_view;
    private SimpleArcDialog mDialog;
    private EditText editText_title;
    private EditText editText_summary;
    private RadioGroup rg_options;
    private TextView tv_zy;

    public static void startTestAddActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, TestAddActivity.class);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        tv_zy = (TextView) findViewById(R.id.tv_zy);
        String zr = DataManager.getZY();
        if (!TextUtils.isEmpty(zr)) {
            zy = zr.substring(zr.length() - 32, zr.length());
            tv_zy.setText(zr.substring(0, zr.length() - 33));
        }else {
            tv_zy.setText("暂未设置职业");
        }
        questionAdd = new QuestionAdd();
        questionAdd.setKeyword("");
        questionAdd.setSource("");
        questionAdd.setOptions(Propertity.OPTIONS.ZYZS);
        presenter = new QuestionAddPresenterImpl(this);

        root_view = findViewById(R.id.root_view);
        rg_options = (RadioGroup) findViewById(R.id.rg_options);
        rg_options.setOnCheckedChangeListener(this);
        editText_summary = (EditText) findViewById(R.id.editText_summary);
        editText_title = (EditText) findViewById(R.id.editText_title);
        findViewById(R.id.bt_fabu).setOnClickListener(this);
        findViewById(R.id.tv_choice).setOnClickListener(this);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.test_add_activity;
    }

    @Override
    public void adding() {
        mDialog = new SimpleArcDialog(this);
        ArcConfiguration arcConfiguration = new ArcConfiguration(this);
        arcConfiguration.setText("正在提交数据...");
        mDialog.setConfiguration(arcConfiguration);
        mDialog.show();
    }

    @Override
    public void addSuccess() {
        closeDialog();
        showSnackbar(root_view, "发布信息成功");
    }

    @Override
    public void addFail() {
        closeDialog();
        showSnackbar(root_view, "发布信息失败");
    }

    private void closeDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fabu:
                String title = editText_title.getText().toString();
                String summary = editText_summary.getText().toString();
                if (TextUtils.isEmpty(zy)){
                    showSnackbar(root_view, "请先选择职业");
                    return;
                }
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(summary)) {
                    showSnackbar(root_view, "请将数据添加完整");
                    return;
                }

                if (title.trim().length() < 6 || summary.length() > 16) {
                    showSnackbar(root_view, "标题长度不合法");
                    return;
                }
                adding();
                questionAdd.setTitle(title);
                questionAdd.setSummary(summary);
                questionAdd.setType(zy);
                presenter.QuestionAdd(questionAdd, files);
                break;
            case R.id.guillotine_hamburger:
                finish();
                break;
            case R.id.tv_choice:
                Intent intent = new Intent(TestAddActivity.this, VocationTestActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || requestCode != 100) {
            return;
        }
        String zr = DataManager.getZY();
        if (!TextUtils.isEmpty(zr)) {
            zy = zr.substring(zr.length() - 32, zr.length());
            tv_zy.setText(zr.substring(0, zr.length() - 33));
        }else {
            tv_zy.setText("暂未设置职业");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_js) {
            questionAdd.setOptions(Propertity.OPTIONS.ZYZS);
        } else if (checkedId == R.id.rb_rs) {
            questionAdd.setOptions(Propertity.OPTIONS.RSZS);
        } else {
            questionAdd.setOptions(Propertity.OPTIONS.QT);
        }
    }

    @Override
    public void checkToken() {
        getToken();
    }
}
