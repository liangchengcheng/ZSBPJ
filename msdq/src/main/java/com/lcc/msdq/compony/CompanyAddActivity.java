package com.lcc.msdq.compony;

import android.app.Activity;
import android.content.Intent;

import com.lcc.base.BaseActivity;
import com.lcc.entity.CompanyDescription;
import com.lcc.msdq.R;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  CompanyAddActivity
 */
public class CompanyAddActivity extends BaseActivity {

    public static final String NAME = "name";
    private String name;

    public static void startCompanyAddActivity(String cn, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, CompanyAddActivity.class);
        intent.putExtra(NAME, cn);
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
        return R.layout.com_des_add;
    }
}
