package com.lcc.activity.question;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcc.activity.R;
import com.lcc.activity.question.fragment.LqfsFragment;
import com.lcc.activity.question.fragment.ZchFragment;
import com.lcc.activity.question.fragment.ZsjhFragment;
import com.lcc.activity.question.fragment.ZxzxFragment;
import com.lcc.base.BaseActivity;
import com.lcc.view.bottombar.BottomBar;
import com.lcc.view.bottombar.BottomBarFragment;


public class QuestionMainActivity extends BaseActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBottomBar = BottomBar.attach(QuestionMainActivity.this, savedInstanceState);
        mBottomBar.setFragmentItems(getSupportFragmentManager(), R.id.fragmentContainer,
                new BottomBarFragment(ZchFragment.newInstance(), R.drawable.ic_subject_black_36dp, "最新信息"),
                new BottomBarFragment(ZsjhFragment.newInstance(), R.drawable.ic_equalizer_black_36dp, "zs计划"),
                new BottomBarFragment(LqfsFragment.newInstance(1,1), R.drawable.ic_search_black_36dp, "录取分数"),
                new BottomBarFragment(ZxzxFragment.newInstance(1,1), R.drawable.ic_call_black_36dp, "咨询")
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
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
        return R.layout.question_main;
    }
}
