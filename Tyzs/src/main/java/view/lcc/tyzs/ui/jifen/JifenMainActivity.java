package view.lcc.tyzs.ui.jifen;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.mvp.presenter.JifenYuePresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenYuePresenterImpl;
import view.lcc.tyzs.mvp.view.JifenYueView;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-04 21:47
 * Description:  |
 */
public class JifenMainActivity extends BaseActivity implements JifenYueView, View.OnClickListener {

    private TextView tv_balance, tv_name, tv_balance1;
    private String balance;

    private JifenYuePresenter jifenYuePresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jifen_main_activity);
        jifenYuePresenter = new JifenYuePresenterImpl(this);

        String name = SharePreferenceUtil.getName();

        findViewById(R.id.btn_zz).setOnClickListener(this);
        findViewById(R.id.btn_tx).setOnClickListener(this);
        findViewById(R.id.jfzh).setOnClickListener(this);
        findViewById(R.id.jfzh).setOnClickListener(this);
        findViewById(R.id.ll_changelist).setOnClickListener(this);
        findViewById(R.id.tv_change).setOnClickListener(this);
        findViewById(R.id.mingxi).setOnClickListener(this);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_balance1 = (TextView) findViewById(R.id.tv_balance1);

        // TODO: 2017/8/4 返回按钮
        jifenYuePresenter.jifenYue();
    }

    @Override
    public void JifenYueLoading() {
        createDialog(R.string.get_point);
    }

    @Override
    public void JifenYueSuccess(String r) {
        closeDialog();
        try {
            JSONObject jsonObject = new JSONObject(r);
            String result_json = jsonObject.getString("resultjson");
            JSONObject js = new JSONObject(result_json);
            String balance = js.getString("balance");

            if (balance.equals("")) {
                tv_balance.setText("您的余额：0 ");
            }
            String[] result = balance.split(",");

            if (result.length == 2) {
                String[] xt = result[0].split("=");
                String[] cz = result[1].split("=");
                balance = xt[1];
                tv_balance.setText("系统余额:" + xt[1]);
                tv_balance1.setText("充值余额:" + cz[1]);
            } else if (result.length == 1) {
                String[] xt = result[0].split("=");
                if (xt[0].contains("系统")) {
                    balance = "0";
                    tv_balance.setText("系统余额:" + xt[1]);
                    tv_balance1.setText("充值余额:0");
                } else {
                    balance = xt[1];
                    tv_balance.setText("系统余额:" + 0);
                    tv_balance1.setText("充值余额:" + balance);
                }
            } else {
                balance = "0";
                tv_balance.setText("系统余额:0");
                tv_balance1.setText("充值余额:0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void JifenYueFail(String msg) {
        closeDialog();

    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            //积分提现
            case R.id.btn_tx:
                intent = new Intent(JifenMainActivity.this, JifentixianActivity.class);
                intent.putExtra("b", balance);
                startActivity(intent);
                break;
            //积分转账
            case R.id.btn_zz:
                intent = new Intent(JifenMainActivity.this, JifenzhuanzhangActivity.class);
                intent.putExtra("b", balance);
                startActivity(intent);
                break;
            //积分转换
            case R.id.jfzh:
                intent = new Intent(JifenMainActivity.this, JifenzhuanhuanActivity.class);
                intent.putExtra("b", balance);
                startActivity(intent);
                break;
            //积分申请
            case R.id.tv_change:
                intent = new Intent(JifenMainActivity.this, JifenShenqingActivity.class);
                intent.putExtra("b", balance);
                startActivity(intent);
                break;
            //变动列表
            case R.id.ll_changelist:
                startActivity(new Intent(JifenMainActivity.this, JifenListActivity.class));
                break;
            //变动列表
            case R.id.mingxi:
                startActivity(new Intent(JifenMainActivity.this, JifenListActivity.class));
                break;

        }
    }
}
