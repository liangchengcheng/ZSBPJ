package com.lcc.msdq.compony;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lcc.base.BaseActivity;
import com.lcc.entity.CompanyDescription;
import com.lcc.msdq.R;
import com.lcc.msdq.compony.content.CodeFragment;
import com.lcc.msdq.description.com.CompanyDesMain;

import java.util.ArrayList;
import java.util.List;

public class CompanyContentActivity extends BaseActivity implements View.OnClickListener {

    public static final String ID = "id";
    private String fid;
    private CompanyDescription companyDescription;

    public static void startCompanyContentActivity(CompanyDescription id, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, CompanyContentActivity.class);
        intent.putExtra(ID, id);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        companyDescription = (CompanyDescription) getIntent()
                .getSerializableExtra(ID);
        fid = companyDescription.getMid();

        findViewById(R.id.iv_q_add).setOnClickListener(this);
        findViewById(R.id.iv_com_des).setOnClickListener(this);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        setViewPager();
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(CodeFragment.newInstance("1cddd741560e7d90ebf9112b989ba955", "技术面试"),
                "技术面试");
        adapter.addFragment(CodeFragment.newInstance("1cddd741560e7d90ebf9112b989ba955", "人事面试"),
                "人事面试");
        adapter.addFragment(CodeFragment.newInstance("1cddd741560e7d90ebf9112b989ba955", "其他/经验"),
                "其他/经验");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_company_content;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_q_add:
                ComquestionAddActivity.startComquestionActivity(fid, CompanyContentActivity.this);
                break;

            case R.id.iv_com_des:
                CompanyDesMain.startCompanyDesMain(companyDescription, CompanyContentActivity.this);
                break;

            case R.id.guillotine_hamburger:
                finish();
                break;
        }
    }

    static class Adapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
