package com.lcc.msdq.test;

import android.app.Activity;
import android.content.Intent;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;


/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  TestAddActivity
 */
public class TestAddActivity extends BaseActivity {

    public static final String ZY = "zy";

    public static void startTestAddActivity(Activity startingActivity, String zy) {
        Intent intent = new Intent(startingActivity, TestAddActivity.class);
        intent.putExtra(ZY, zy);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.test_add_activity;
    }
}
