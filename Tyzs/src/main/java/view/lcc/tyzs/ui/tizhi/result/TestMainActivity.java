package view.lcc.tyzs.ui.tizhi.result;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.base.BaseApplication;
import view.lcc.tyzs.bean.UserInfo;
import view.lcc.tyzs.ui.tizhi.main.TanShiActivity;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-14 18:35
 * Description:  |
 */
public class TestMainActivity extends BaseActivity implements View.OnClickListener{

    private RadioButton rb_nan;
    private RadioButton rb_nv;
    private EditText user;
    private String sex = "男";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_activity);
        BaseApplication.addActivity(this);

        user = (EditText) findViewById(R.id.user);
        rb_nan = (RadioButton) findViewById(R.id.rb_nan);
        rb_nv = (RadioButton) findViewById(R.id.rb_nv);

        findViewById(R.id.pb_next).setOnClickListener(this);
        RadioGroup rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_nan){
                    sex = "男";
                    rb_nan.setTextColor(Color.parseColor("#FFAD5B"));
                    rb_nv.setTextColor(Color.parseColor("#000000"));
                }else {
                    sex = "女";
                    rb_nv.setTextColor(Color.parseColor("#FFAD5B"));
                    rb_nan.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        //返回
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pb_next:
                UserInfo userInfo = new UserInfo();
                String nickName = user.getText().toString();
                if (TextUtils.isEmpty(nickName)){
                    userInfo.setNickname("无标签");
                }else {
                    userInfo.setNickname(nickName);
                }
                userInfo.setSex(sex);
                Intent intent = new Intent(TestMainActivity.this,TanShiActivity.class);
                intent.putExtra("data",userInfo);
                startActivity(intent);
                break;
        }
    }
}
