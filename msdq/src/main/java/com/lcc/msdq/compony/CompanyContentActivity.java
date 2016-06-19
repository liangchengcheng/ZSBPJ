package com.lcc.msdq.compony;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lcc.base.BaseActivity;
import com.lcc.msdq.R;
import com.lcc.msdq.compony.content.CodeFragment;
import com.lcc.msdq.compony.content.HrFragment;
import com.lcc.msdq.compony.content.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class CompanyContentActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void initView() {
        findViewById(R.id.iv_q_add).setOnClickListener(this);
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
        adapter.addFragment(CodeFragment.newInstance("1cddd741560e7d90ebf9112b989ba955"), "技术面试");
        adapter.addFragment(new HrFragment(), "人事面试");
        adapter.addFragment(new OtherFragment(), "其他/经验");
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
        switch (v.getId()){
            case R.id.iv_q_add:
                startActivity(new Intent(CompanyContentActivity.this,ComquestionActivity.class));
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
