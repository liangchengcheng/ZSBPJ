package view.lcc.tyzs.ui.tizhi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.ui.tizhi.history.HistoryActivity;
import view.lcc.tyzs.ui.tizhi.main.TanshiTestMainActivity;
import view.lcc.tyzs.ui.tizhi.result.TestMainActivity;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.utils.TimeUtils;
import view.lcc.tyzs.view.ColorArcProgressBar;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-11 07:22
 * Description:  |
 */
public class TizhiActivity extends BaseActivity implements View.OnClickListener {
    private ColorArcProgressBar bar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tizhi_home_activity);

        bar2 = (ColorArcProgressBar) findViewById(R.id.bar2);
        //获取上次体检的时间
        String updateTime = SharePreferenceUtil.getUpdateTime();
        if (!TextUtils.isEmpty(updateTime)) {
            //获取当前时间
            String localtime = TimeUtils.StrTime(System.currentTimeMillis());
            int day = 21 - (int) (Long.parseLong(localtime) - Long.parseLong(updateTime)) / (60 * 60 * 24);
            bar2.setCurrentValues(day);
        } else {
            bar2.setCurrentValues(0);
        }
        findViewById(R.id.mszb).setOnClickListener(this);
        findViewById(R.id.msjq).setOnClickListener(this);
        findViewById(R.id.msjl).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            //体质测试
            case R.id.mszb:
                intent = new Intent(TizhiActivity.this, TestMainActivity.class);
                startActivity(intent);
                break;
            //痰湿与体重控制检测
            case R.id.msjl:
                intent = new Intent(TizhiActivity.this, TanshiTestMainActivity.class);
                startActivity(intent);
                break;
            //测试记录
            case R.id.msjq:
                intent = new Intent(TizhiActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
