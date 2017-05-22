package com.hsfcompany.tzcs.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.hsfcompany.tzcs.R;
import com.hsfcompany.tzcs.base.BaseActivity;
import com.hsfcompany.tzcs.dao.UserInfo;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |05-13 15:30
 * Description:  |
 */
public class ResultActivity extends BaseActivity implements View.OnClickListener{

    private ColumnChartView chart_top;

    private UserInfo userInfo;

    public final static String[] NAME = new String[]{"痰湿质", "阳虚质", "阴虚质", "气郁质", "湿热质",
            "气虚质", "血瘀质", "特禀质"};

    int[] score_top;
    private ColumnChartData columnData_TOP;


    private View tv_fa1, tv_fa2, tv_fa3, tv_fa4, tv_fa5;

    private View rzky;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        rzky = findViewById(R.id.rzky);
        findViewById(R.id.lv_back).setOnClickListener(this);
        tv_fa1 = findViewById(R.id.tv_fa1);
        tv_fa2 = findViewById(R.id.tv_fa2);
        tv_fa3 = findViewById(R.id.tv_fa3);
        tv_fa4 = findViewById(R.id.tv_fa4);
        tv_fa5 = findViewById(R.id.tv_fa5);

        userInfo = (UserInfo) getIntent().getSerializableExtra("data");
        score_top = new int[]{userInfo.getTanshizhi(), userInfo.getYangxuzhi(), userInfo.getYinxuzhi()
                , userInfo.getQiyuzhi(), userInfo.getShirezhi(), userInfo.getQixuzhi(), userInfo.getXueyuzhi(), userInfo.getTebingzhi()};
        chart_top = (ColumnChartView) findViewById(R.id.chart_top);
        generateColumnDataTop();
        setState();
    }

    private void setState() {
        if (userInfo.getTebingzhi() > 40 || (userInfo.getYangxuzhi() <= 40 &&
                userInfo.getYinxuzhi() <= 40 &&
                userInfo.getTanshizhi() <= 40 &&
                userInfo.getQiyuzhi() <= 40 &&
                userInfo.getShirezhi() <= 40 &&
                userInfo.getQixuzhi() <= 40)) {
            tv_fa2.setVisibility(View.VISIBLE);
        }else {
            rzky.setVisibility(View.VISIBLE);
        }

        if (userInfo.getYangxuzhi() > 40) {
            if (userInfo.getSex().equals("男")) {
                tv_fa3.setVisibility(View.VISIBLE);
            } else {
                tv_fa4.setVisibility(View.VISIBLE);
            }
        }

        if (userInfo.getYinxuzhi() > 40 || userInfo.getTanshizhi() > 40
                || userInfo.getQiyuzhi() > 40 || userInfo.getShirezhi() > 40 ||
                userInfo.getQixuzhi() > 40) {
            tv_fa5.setVisibility(View.VISIBLE);
        }
    }

    private void generateColumnDataTop() {
        int numColumns = NAME.length;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(score_top[i], getDataColor(score_top[i])));
            axisValues.add(new AxisValue(i).setLabel(NAME[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true).setHasLabels(false));
        }

        columnData_TOP = new ColumnChartData(columns);
        columnData_TOP.setAxisXBottom(new Axis(axisValues).setHasLines(true).setName("体质名称").setTextColor(Color.parseColor("#000000")));
        columnData_TOP.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2).setName("").setTextColor(Color.parseColor("#000000")));
        chart_top.setColumnChartData(columnData_TOP);

        chart_top.setValueSelectionEnabled(true);
        chart_top.setZoomType(ZoomType.HORIZONTAL);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //prepareDataAnimation();
                //chart_top.startDataAnimation();
            }
        }, 1500);
    }

    private void prepareDataAnimation() {
        for (Column column : columnData_TOP.getColumns()) {
            for (int i = 0; i < column.getValues().size(); i++) {
                if (i == 0) {
                    column.getValues().get(0).setTarget(userInfo.getTanshizhi());
                }
                if (i == 1) {
                    column.getValues().get(1).setTarget(userInfo.getYangxuzhi());
                }
                if (i == 2) {
                    column.getValues().get(2).setTarget(userInfo.getYinxuzhi());
                }
                if (i == 3) {
                    column.getValues().get(3).setTarget(userInfo.getQiyuzhi());
                }
                if (i == 4) {
                    column.getValues().get(4).setTarget(userInfo.getShirezhi());
                }
                if (i == 5) {
                    column.getValues().get(5).setTarget(userInfo.getQixuzhi());
                }
                if (i == 6) {
                    column.getValues().get(6).setTarget(userInfo.getXueyuzhi());
                }
                if (i == 7) {
                    column.getValues().get(7).setTarget(userInfo.getTebingzhi());
                }
            }
        }
    }

    public int getDataColor(int score) {
        if (score > 65) {
            return Color.parseColor("#ed4444");
        } else if (score > 40 && score <= 65) {
            return Color.parseColor("#FFAD5B");
        } else {
            return Color.parseColor("#29c741");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lv_back:
                finish();
                break;
        }
    }
}
