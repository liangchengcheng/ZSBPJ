package com.hsfcompany.tzcs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.hsfcompany.tzcs.R;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 19:40
 * Description:  |
 */
public class QiXuActivity extends AppCompatActivity implements View.OnClickListener{
    private CheckBox cb_1;
    private CheckBox cb_2;
    private CheckBox cb_3;
    private CheckBox cb_4;

    private int score;

    @Override
    protected void onResume() {
        super.onResume();
        score = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qixu_activity);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.pb_next).setOnClickListener(this);

        cb_1 = (CheckBox) findViewById(R.id.cb_1);
        cb_2 = (CheckBox) findViewById(R.id.cb_2);
        cb_3 = (CheckBox) findViewById(R.id.cb_3);
        cb_4 = (CheckBox) findViewById(R.id.cb_4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pb_next:
                if (!cb_1.isChecked()&&!cb_2.isChecked()&&!cb_3.isChecked()&&!cb_4.isChecked()){
                    score = 5;
                }else {
                    if (cb_3.isChecked()){
                        score += 65;
                        if (cb_1.isChecked()){
                            score += 10;
                        }
                        if (cb_2.isChecked()){
                            score += 10;
                        }
                        if (cb_4.isChecked()){
                            score += 15;
                        }
                    }else {
                        if (cb_2.isChecked()){
                            score += 55;
                            if (cb_1.isChecked()){
                                score += 10;
                            }
                            if (cb_4.isChecked()){
                                score += 15;
                            }
                        }else {
                            if (cb_4.isChecked()){
                                score += 55;
                                if (cb_1.isChecked()){
                                    score += 10;
                                }
                            }else {
                                if (cb_1.isChecked()){
                                    score +=35 ;
                                }
                            }
                        }
                    }
                }
                Intent intent = new Intent(QiXuActivity.this,XueYuActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
