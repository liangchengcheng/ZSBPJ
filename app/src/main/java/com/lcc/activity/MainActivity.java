package com.lcc.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lcc.activity.main.activity.CityPickerActivity;
import com.lcc.activity.main.fragment.AllKnowFragment;
import com.lcc.activity.main.fragment.HomeFragment;
import com.lcc.activity.main.fragment.IndexFragment;
import com.lcc.activity.main.fragment.OnlineClassFragment;
import com.lcc.activity.personinfo.PersonInfoActivity;
import com.lcc.activity.setting.SettingActivity;
import com.lcc.adapter.MaterialSimpleListAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.entity.ShareListItem;
import com.lcc.view.dialog.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        intNavigationView();
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
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
        adapter.addFragment(new IndexFragment(), "快速导航");
        adapter.addFragment(new AllKnowFragment(), "考试资料");
        Fragment onlineClassFragment = OnlineClassFragment.newInstance(1, 1);
        adapter.addFragment(onlineClassFragment, "在线课程");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    private void intNavigationView() {
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        MainNavigationHeader mMainNavigationHeader = new MainNavigationHeader(this, mNavigationView);
        mMainNavigationHeader.bindData();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            //去我的信息中心的界面
            Intent intent = new Intent(MainActivity.this, PersonInfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(MainActivity.this, "nav_gallery", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(MainActivity.this, "nav_slideshow", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        } else if (id == R.id.nav_share) {
            showShareDialog();
        } else if (id == R.id.nav_send) {
            Toast.makeText(MainActivity.this, "nav_send", Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                break;

            case R.id.action_change:
                Intent intent = new Intent(MainActivity.this, CityPickerActivity.class);
                startActivityForResult(intent, 100);
                break;

            case R.id.action_about:
                DialogUtil.showAbout(this);
                break;

            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(Integer event) {
        switch (event) {
            case 0x02:
                this.recreate();
                break;
        }
    }

    private void showShareDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ShareDialog);
        builder.setTitle("分享到");
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(this);
        String[] array = getResources().getStringArray(R.array.share_dialog_text);
        adapter.add(new ShareListItem.Builder(this)
                .content(array[0])
                .icon(R.drawable.ic_wx_logo)
                .build());
        adapter.add(new ShareListItem.Builder(this)
                .content(array[1])
                .icon(R.drawable.ic_wx_moments)
                .build());
        adapter.add(new ShareListItem.Builder(this)
                .content(array[2])
                .icon(R.drawable.ic_wx_collect)
                .build());
        adapter.add(new ShareListItem.Builder(this)
                .content(array[3])
                .icon(R.drawable.ic_sina_logo)
                .build());
        adapter.add(new ShareListItem.Builder(this)
                .content(array[4])
                .icon(R.drawable.ic_share_more)
                .build());
        builder.setAdapter(adapter, (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //shareToWeChatSession();
                        break;
                    case 1:
                        //shareToWeChatTimeline();
                        break;
                    case 2:
                        //shareToWeChatFavorite();
                        break;
                    case 3:
                        //shareToWeibo();
                        break;

                }
            }
        }));
        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        Display display = getWindowManager().getDefaultDisplay();
        Point out = new Point();
        display.getSize(out);
        lp.width = out.x;
        window.setAttributes(lp);
        final View decorView = window.getDecorView();
        decorView.setBackgroundColor(getResources().getColor(R.color.window_background));
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Animator animator = ObjectAnimator
                        .ofFloat(decorView, "translationY", decorView.getMeasuredHeight() / 1.5F, 0);
                animator.setDuration(200);
                animator.start();
            }
        });
        dialog.show();
    }

}
