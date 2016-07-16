package com.lcc.msdq.compony;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.lcc.base.BaseActivity;
import com.lcc.entity.CompanyDescription;
import com.lcc.msdq.R;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  CompanyAddActivity
 */
public class CompanyAddActivity extends BaseActivity {

    public static final String NAME = "name";
    private String name;
    private Pattern intPattern = Pattern.compile("^[-\\+]?[\\d]*\\.0*$");
    private String pro, city, dis;

    private TextView tv_position;

    @Override
    protected int getLayoutView() {
        return R.layout.com_des_add;
    }

    @Override
    protected void initView() {
        tv_position= (TextView) findViewById(R.id.tv_position);
        initData();
    }

    private void initData() {
        final Map map = (Map) getIntent().getSerializableExtra("addressInfo");
        pro = getString(map, "provName", "");
        city = getString(map, "cityName", "");
        dis = getString(map, "districtName", "");
        String areaName = String.format("%s %s %s", pro, city, dis);
        tv_position.setText(areaName);
    }

    @Override
    protected boolean Open() {
        return false;
    }


    public String getString(Map map, String key, String defaultValue) {
        Object obj = map.get(key);
        return obj == null ? defaultValue : (obj instanceof Number &&
                intPattern.matcher(obj.toString()).matches() ?
                String.valueOf(Long.valueOf(((Number) obj).longValue())) : obj.toString());
    }
}
