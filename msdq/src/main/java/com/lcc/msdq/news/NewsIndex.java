package com.lcc.msdq.news;

import android.app.Activity;
import android.content.Intent;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  开始或者结束轮训器
 */
public class NewsIndex extends BaseActivity {

    public static void startNewsIndex(Activity startingActivity, String type) {
        Intent intent = new Intent(startingActivity, NewsIndex.class);
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
        return R.layout.activity_news;
    }
}
