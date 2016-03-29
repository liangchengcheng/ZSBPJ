package com.lcc.activity.personinfo;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.lcc.activity.R;
import com.lcc.base.BaseActivity;

public class PersonInfoActivity extends BaseActivity {

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle("个人信息");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_info;
    }
}
