package com.hsfcompany.tzcs.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.hsfcompany.tzcs.R;
import com.hsfcompany.tzcs.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
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
public class ResultActivity extends BaseActivity {

    private ColumnChartView chart_top;
    public final static String[] NAME = new String[]{"痰湿质", "阳虚质", "阴虚质", "气郁质", "湿热质",
            "气虚质", "血瘀质", "特禀质"};

    int[] score_top= {50,42,90,33,10,33,31,33};

    int[] colors= {Color.parseColor("#37C930"),Color.parseColor("#7CC4F7"),Color.parseColor("#FB6E47")
            ,Color.parseColor("#FB6E47"),Color.parseColor("#FB6E47"),Color.parseColor("#FB6E47"),
            Color.parseColor("#5884E7"),Color.parseColor("#FB53A8")};
    private ColumnChartData columnData_TOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        chart_top = (ColumnChartView) findViewById(R.id.chart_top);
        generateColumnDataTop();
    }

    private void generateColumnDataTop() {
        int numColumns = NAME.length;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(score_top[i], colors[i]));
            axisValues.add(new AxisValue(i).setLabel(NAME[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true).setHasLabels(true));
        }

        columnData_TOP = new ColumnChartData(columns);
        columnData_TOP.setAxisXBottom(new Axis(axisValues).setHasLines(true).setName("体质名称"));
        columnData_TOP.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2).setName("体质分数"));
        chart_top.setColumnChartData(columnData_TOP);
        chart_top.setValueSelectionEnabled(true);
        chart_top.setZoomType(ZoomType.HORIZONTAL);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prepareDataAnimation();
                chart_top.startDataAnimation();
            }
        }, 1500);
    }

    private void prepareDataAnimation() {
        for (Column column : columnData_TOP.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 100);
            }
        }
    }
}
