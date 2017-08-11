package view.lcc.tyzs.ui.tizhi.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;


/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-16 07:24
 * Description:  |
 */
public class TanshiTestMain2Activity extends BaseActivity implements View.OnClickListener{
    private CheckBox cb_1;
    private CheckBox cb_2;
    private CheckBox cb_3;
    private CheckBox cb_4;
    private CheckBox cb_5;
    private CheckBox cb_6;
    private CheckBox cb_7;
    private CheckBox cb_8;

    private int score;
    private float shengao;
    private float tizhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tanshi_test_activity2);
        shengao = getIntent().getFloatExtra("shengao",0);
        tizhong = getIntent().getFloatExtra("tizhong",0);

        cb_1 = (CheckBox) findViewById(R.id.cb_1);
        cb_2 = (CheckBox) findViewById(R.id.cb_2);
        cb_3 = (CheckBox) findViewById(R.id.cb_3);
        cb_4 = (CheckBox) findViewById(R.id.cb_4);
        cb_5 = (CheckBox) findViewById(R.id.cb_5);
        cb_6 = (CheckBox) findViewById(R.id.cb_6);
        cb_7 = (CheckBox) findViewById(R.id.cb_7);
        cb_8 = (CheckBox) findViewById(R.id.cb_8);
        findViewById(R.id.pb_next).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        score = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pb_next:
                if (!cb_1.isChecked() && !cb_2.isChecked() && !cb_3.isChecked() && !cb_4.isChecked()
                        && !cb_5.isChecked()
                        && !cb_6.isChecked()
                        && !cb_7.isChecked()
                        && !cb_8.isChecked()) {
                    score = 5;
                } else {
                    if (cb_1.isChecked()) {
                        score += 65;
                        if (cb_2.isChecked()) {
                            score += 5;
                        }
                        if (cb_3.isChecked()) {
                            score += 5;
                        }
                        if (cb_4.isChecked()) {
                            score += 5;
                        }
                        if (cb_5.isChecked()) {
                            score += 5;
                        }
                        if (cb_6.isChecked()) {
                            score += 5;
                        }
                        if (cb_7.isChecked()) {
                            score += 5;
                        }
                        if (cb_8.isChecked()) {
                            score += 5;
                        }
                    } else {
                        if (cb_8.isChecked()) {
                            score += 65;
                            if (cb_2.isChecked()) {
                                score += 5;
                            }
                            if (cb_3.isChecked()) {
                                score += 5;
                            }
                            if (cb_4.isChecked()) {
                                score += 5;
                            }
                            if (cb_5.isChecked()) {
                                score += 5;
                            }
                            if (cb_6.isChecked()) {
                                score += 5;
                            }
                            if (cb_7.isChecked()) {
                                score += 5;
                            }
                        } else {
                            if (cb_7.isChecked()) {
                                score += 65;
                                if (cb_2.isChecked()) {
                                    score += 5;
                                }
                                if (cb_3.isChecked()) {
                                    score += 5;
                                }
                                if (cb_4.isChecked()) {
                                    score += 5;
                                }
                                if (cb_5.isChecked()) {
                                    score += 5;
                                }
                                if (cb_6.isChecked()) {
                                    score += 5;
                                }
                            } else {
                                if (cb_6.isChecked()) {
                                    score += 45;
                                    if (cb_2.isChecked()) {
                                        score += 5;
                                    }
                                    if (cb_3.isChecked()) {
                                        score += 5;
                                    }
                                    if (cb_4.isChecked()) {
                                        score += 5;
                                    }
                                    if (cb_5.isChecked()) {
                                        score += 5;
                                    }
                                }else{
                                    if (cb_5.isChecked()) {
                                        score += 45;
                                        if (cb_2.isChecked()) {
                                            score += 5;
                                        }
                                        if (cb_3.isChecked()) {
                                            score += 5;
                                        }
                                        if (cb_4.isChecked()) {
                                            score += 5;
                                        }
                                    }else {
                                        if (cb_4.isChecked()) {
                                            score += 45;
                                            if (cb_2.isChecked()) {
                                                score += 5;
                                            }
                                            if (cb_3.isChecked()) {
                                                score += 5;
                                            }
                                        } else {
                                            if (cb_3.isChecked()) {
                                                score += 45;
                                                if (cb_2.isChecked()) {
                                                    score += 5;
                                                }
                                            }else {
                                                if (cb_2.isChecked()) {
                                                    score += 35;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Intent intent = new Intent(TanshiTestMain2Activity.this, TanshiTestResultActivity.class);
                intent.putExtra("score",score);
                intent.putExtra("shengao",shengao);
                intent.putExtra("tizhong",tizhong);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
