package com.lcc.activity.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import com.lcc.activity.R;
import com.lcc.base.BaseActivity;

public class InformationMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle("专升本百科");
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
        return R.layout.information_main;
    }

}
