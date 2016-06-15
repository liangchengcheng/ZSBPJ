package com.lcc.msdq.description.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcc.base.BaseActivity;
import com.lcc.db.test.UserInfo;
import com.lcc.msdq.R;
import com.lcc.msdq.compony.content.CodeFragment;
import com.lcc.msdq.compony.content.HrFragment;
import com.lcc.msdq.compony.content.OtherFragment;
import com.lcc.msdq.flow.FlowIndex;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import zsbpj.lccpj.frame.ImageManager;

public class UserProfileActivity extends BaseActivity implements View.OnClickListener{
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    private static final int USER_OPTIONS_ANIMATION_DELAY = 300;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    @Bind(R.id.tlUserProfileTabs)
    TabLayout tlUserProfileTabs;
    @Bind(R.id.ivUserProfilePhoto)
    ImageView ivUserProfilePhoto;
    @Bind(R.id.vUserDetails)
    View vUserDetails;
    @Bind(R.id.btnFollow)
    Button btnFollow;
    @Bind(R.id.vUserStats)
    View vUserStats;
    @Bind(R.id.vUserProfileRoot)
    View vUserProfileRoot;

    @Bind(R.id.tv_nickname)
    TextView tv_nickname;

    @Bind(R.id.tv_gxqm)
    TextView tv_gxqm;

    @Bind(R.id.tv_me)
    LinearLayout tv_me;

    @Bind(R.id.tv_you)
    LinearLayout tv_you;

    private int avatarSize;
    private String profilePhoto;
    private UserProfileAdapter userPhotosAdapter;

    private UserInfo userInfo;

    public static void startUserProfileFromLocation(int[] startingLocation, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, UserProfileActivity.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        userInfo= (UserInfo) getIntent().getSerializableExtra("data");
        this.avatarSize = getResources().getDimensionPixelSize(R.dimen.user_profile_avatar_size);

        tv_me.setOnClickListener(this);
        tv_you.setOnClickListener(this);
        ImageManager.getInstance().loadCircleImage(UserProfileActivity.this,
                userInfo.getUser_image(),
                ivUserProfilePhoto);
        tv_nickname.setText(userInfo.getNickname());
        if (TextUtils.isEmpty(userInfo.getQm())){
            tv_gxqm.setText("这个家伙很懒，什么也没留下");
        }else {
            tv_gxqm.setText(userInfo.getQm());
        }
        setViewPager();
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
        return R.layout.activity_user_profile;
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
            tlUserProfileTabs.setupWithViewPager(viewPager);
        }
    }

    private void setupTabs() {
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_grid_on_white));
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_list_white));
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_place_white));
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_label_white));
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
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_me:
                FlowIndex.startUserProfileFromLocation("me","18813149871",UserProfileActivity.this);
                break;

            case R.id.tv_you:
                FlowIndex.startUserProfileFromLocation("you","18813149871",UserProfileActivity.this);
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
