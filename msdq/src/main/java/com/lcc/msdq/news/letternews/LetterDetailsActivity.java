package com.lcc.msdq.news.letternews;

import android.app.Activity;
import android.content.Intent;

import com.lcc.base.BaseActivity;
import com.lcc.entity.Letter;
import com.lcc.msdq.R;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  LetterActivity(个人的私信的界面)
 */
public class LetterDetailsActivity extends BaseActivity {

    public static final String LETTER = "letter";
    private Letter letter;

    public static void startLetterDetailsActivity(Letter letter,
                                                    Activity startingActivity) {
        Intent intent = new Intent(startingActivity, LetterDetailsActivity.class);
        intent.putExtra(LETTER, letter);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        letter= (Letter) getIntent().getSerializableExtra(LETTER);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.latter_edtails;
    }
}
