package com.lcc.activity.data;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.lcc.activity.R;
import com.lcc.activity.data.fragment.EnglishFragment;
import com.lcc.activity.data.fragment.MethodFragment;
import com.lcc.activity.data.fragment.MnDataFragment;
import com.lcc.activity.data.fragment.OldDataFragment;
import com.lcc.activity.data.fragment.ProfessionalFragment;
import com.lcc.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class DataMainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void initToolBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getColorPrimary());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
        adapter.addFragment(EnglishFragment.newInstance(1,2), "线路查询");
        adapter.addFragment(MethodFragment.newInstance(1,2), "站点查询");
        adapter.addFragment(MnDataFragment.newInstance(1,2), "站点查询");
        adapter.addFragment(OldDataFragment.newInstance(1,2), "站点查询");
        adapter.addFragment(ProfessionalFragment.newInstance(1,2), "站点查询");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        initToolBar();
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_data_main;
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
