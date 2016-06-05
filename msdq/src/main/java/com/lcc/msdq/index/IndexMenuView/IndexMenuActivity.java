package com.lcc.msdq.index.IndexMenuView;

import android.app.Activity;
import android.content.Intent;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  IndexMenuActivity
 */
public class IndexMenuActivity extends BaseActivity {

    public static final String TYPE = "type";

    public static void startIndexMenuActivity(Activity startingActivity,String type) {
        Intent intent = new Intent(startingActivity, IndexMenuActivity.class);
        intent.putExtra(TYPE, type);
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
        return R.layout.index_menu_activity;
    }
}
