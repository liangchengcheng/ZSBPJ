package view.lcc.tyzs.ui.tizhi.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;


/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-16 21:35
 * Description:  |
 */
public class TanshiTestResultActivity extends BaseActivity {

    private TextView tv_tz;

    private int score;
    private float shengao;
    private float tizhong;

    private View cd_fp;
    private View cd_cz;
    private View cd_tanshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tanshi_result_activity);
        tv_tz = (TextView) findViewById(R.id.tv_tz);
        cd_fp = findViewById(R.id.cd_fp);
        cd_cz = findViewById(R.id.cd_cz);
        cd_tanshi = findViewById(R.id.cd_tanshi);

        shengao = getIntent().getFloatExtra("shengao", 0);
        tizhong = getIntent().getFloatExtra("tizhong", 0);
        score = getIntent().getIntExtra("score", 0);
        String result = getState(score) + getSZState();
        tv_tz.setText(result);
        findViewById(R.id.lv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String getState(int score) {
        if (score > 40) {
            cd_tanshi.setVisibility(View.VISIBLE);
        }

        if (score > 65) {
            return "您的身体是痰湿体质";
        } else if (score > 40 && score <= 65) {
            return "您的身体是倾向痰湿";
        } else {
            return "您的身体不是痰湿";
        }
    }

    public String getSZState() {
        double i = tizhong / Math.pow(shengao / 100, 2);

        if (i >= 23.9) {
            cd_cz.setVisibility(View.VISIBLE);
            cd_tanshi.setVisibility(View.GONE);
        }

        if (i >= 27.9) {
            cd_cz.setVisibility(View.GONE);
            cd_fp.setVisibility(View.VISIBLE);
        }

        if (i >= 39.9) {
            return "并且您的体重是严重肥胖。";
        } else if (i >= 29.9 && i < 39.9) {
            return "并且您的体重是重度肥胖。";
        } else if (i >= 27.9 && i < 29.9) {
            return "并且您的体重是中度肥胖。";
        } else if (i >= 23.9 && i < 27.9) {
            return "并且您的体重是超重。";
        } else if (i >= 18.5 && i < 23.9) {
            return "并且您的体重是正常。";
        } else if (i < 18.5) {
            return "并且您的体重是偏瘦。";
        }
        return "";
    }
}
