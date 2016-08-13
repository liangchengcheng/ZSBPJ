package com.lcc.msdq.compony.question;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.lcc.base.BaseActivity;
import com.lcc.entity.CompanyDescription;
import com.lcc.frame.Propertity;
import com.lcc.msdq.R;
import com.lcc.msdq.comments.CommentsActivity;
import com.lcc.msdq.compony.content.CodeFragment;
import com.lcc.msdq.description.com.CompanyDesMain;
import com.lcc.mvp.presenter.ComStatePresenter;
import com.lcc.mvp.presenter.impl.ComStatePresenterImpl;
import com.lcc.mvp.view.ComStateView;

import java.util.ArrayList;
import java.util.List;

public class CompanyContentActivity extends BaseActivity implements View.OnClickListener, ComStateView {
    public static final String ID = "id";
    private String fid;
    private CompanyDescription companyDescription;
    private ComStatePresenter presenter;

    private FloatingActionMenu floatingMenu;

    public static void startCompanyContentActivity(CompanyDescription id, Activity startActivity) {
        Intent intent = new Intent(startActivity, CompanyContentActivity.class);
        intent.putExtra(ID, id);
        startActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        presenter = new ComStatePresenterImpl(this);
        companyDescription = (CompanyDescription) getIntent().getSerializableExtra(ID);
        fid = companyDescription.getMid();

        floatingMenu = (FloatingActionMenu) findViewById(R.id.floatingMenu);
        findViewById(R.id.iv_q_add).setOnClickListener(this);
        findViewById(R.id.iv_com_des).setOnClickListener(this);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        findViewById(R.id.floating_comments).setOnClickListener(this);
        setViewPager();
        presenter.getData(companyDescription.getMid());
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
        adapter.addFragment(CodeFragment.newInstance(fid, Propertity.OPTIONS.ZYZS),
                Propertity.OPTIONS.ZYZS);
        adapter.addFragment(CodeFragment.newInstance(fid, Propertity.OPTIONS.RSZS),
                Propertity.OPTIONS.RSZS);
        adapter.addFragment(CodeFragment.newInstance(fid, Propertity.OPTIONS.QT),
                Propertity.OPTIONS.QT);
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
            //发布问题
            case R.id.iv_q_add:
                ComquestionAddActivity.startComquestionActivity(fid, CompanyContentActivity.this);
                break;
            //公司介绍
            case R.id.iv_com_des:
                CompanyDesMain.startCompanyDesMain(companyDescription, CompanyContentActivity.this);
                break;
            //返回
            case R.id.guillotine_hamburger:
                finish();
                break;
            //发布评论
            case R.id.floating_comments:
                CommentsActivity.startUserProfileFromLocation(fid, Propertity.COM.DESCRIPTION,
                        CompanyContentActivity.this);
                break;
            //收藏公司
            case R.id.floatingCollect:
                CommentsActivity.startUserProfileFromLocation(fid, Propertity.COM.DESCRIPTION,
                        CompanyContentActivity.this);
                break;
        }

        if (floatingMenu.isOpened()) {
            floatingMenu.close(false);
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

    @Override
    public void isHaveFav(boolean isfav) {

    }

    @Override
    public void FavSuccess() {

    }

    @Override
    public void FavFail(String msg) {

    }

    @Override
    public void UnFavSuccess() {

    }

    @Override
    public void UnFavFail(String msg) {

    }


}
