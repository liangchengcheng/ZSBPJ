package view.lcc.wyzsb.ui.activity.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import java.util.ArrayList;
import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.bean.Comments;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.frame.CollapsingToolbarLayoutState;
import view.lcc.wyzsb.frame.SystemBarHelper;
import view.lcc.wyzsb.mvp.presenter.CommentsPresenter;
import view.lcc.wyzsb.mvp.view.CommentsView;
import view.lcc.wyzsb.ui.fragment.CommentFragment;
import view.lcc.wyzsb.ui.fragment.JianjieFragment;
import view.lcc.wyzsb.utils.MediaUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年04月11日13:46:31
 * Description:  |视频的详情的界面
 */
public class VideoDetailsActivity1 extends BaseActivity implements View.OnClickListener,CommentsView {

    private CollapsingToolbarLayoutState state = CollapsingToolbarLayoutState.INTERNEDIATE;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ButtonBarLayout playButton;

    private PlayerView player;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    private View rootView;
    private TabLayout tlUserProfileTabs;
    private Video video;
    private View fab;
    private AppBarLayout app_bar;
    //顶部的播放按钮和状态提示
    private ImageView iv_bf;
    private TextView tv_top_title;
    //下面的评论按钮
    private View llAddComment;
    //评论的p
    private CommentsPresenter presenter;

    public static void startVideoDetailsActivity(Activity startingActivity, Video type) {
        Intent intent = new Intent(startingActivity, VideoDetailsActivity1.class);
        intent.putExtra("url", type);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.video_details1, null);
        setContentView(rootView);
        video = (Video) getIntent().getSerializableExtra("url");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        SystemBarHelper.immersiveStatusBar(this, 0);
        SystemBarHelper.setHeightAndPadding(this, toolbar);

        llAddComment = findViewById(R.id.llAddComment);
        llAddComment.setVisibility(View.GONE);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        iv_bf = (ImageView) findViewById(R.id.iv_bf);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        tlUserProfileTabs = (TabLayout) findViewById(R.id.tlUserProfileTabs);
        tlUserProfileTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        setViewPager();
        initVideo();

        app_bar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        playButton = (ButtonBarLayout) findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        //修改状态标记为展开
                        state = CollapsingToolbarLayoutState.EXPANDED;
                        //设置title为EXPANDED
                        //collapsingToolbarLayout.setTitle("EXPANDED");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        //设置title不显示
                        collapsingToolbarLayout.setTitle("");
                        //隐藏播放按钮
                        playButton.setVisibility(View.VISIBLE);
                        //修改状态标记为折叠
                        state = CollapsingToolbarLayoutState.COLLAPSED;
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            //由折叠变为中间状态时隐藏播放按钮
                            playButton.setVisibility(View.GONE);
                        }
                        //设置title为INTERNEDIATE
                        //collapsingToolbarLayout.setTitle("INTERNEDIATE");
                        //修改状态标记为中间
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }
                }
            }
        });
    }

    private void initVideo() {
        //虚拟按键的隐藏方法
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //比较Activity根布局与当前布局的大小
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                }
            }
        });
        // 常亮
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
        list = new ArrayList<VideoijkBean>();
        // 有部分视频加载有问题，这个视频是有声音显示不出图像的，
        // 没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
        // 这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
        // String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        VideoijkBean m1 = new VideoijkBean();
        m1.setStream("标清");
        m1.setUrl(video.getV_url());
        list.add(m1);
        player = new PlayerView(this, rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
            }

            @Override
            public PlayerView setPlaySource(List<VideoijkBean> list) {
                return super.setPlaySource(list);
            }
        }.setTitle("video").setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                .setScaleType(PlayStateParams.fillparent).forbidTouch(false).hideSteam(true)
                .hideCenterPlayer(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load(video.getV_img())
                                .placeholder(R.color.article_des)
                                .error(R.color.article_title)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(list)
                .setChargeTie(true, 60);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        //demo的内容，恢复系统其它媒体的状态
        MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        //demo的内容，暂停系统其它媒体的状态
        MediaUtils.muteAudioFocus(mContext, false);
        //demo的内容，激活设备常亮状态
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        //demo的内容，恢复设备亮度状态
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    llAddComment.setVisibility(View.VISIBLE);
                } else {
                    llAddComment.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        if (viewPager != null) {
            setupViewPager(viewPager);
            tlUserProfileTabs.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new JianjieFragment(video), "简介");
        adapter.addFragment(new CommentFragment(video), "评论");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void getLoading() {

    }

    @Override
    public void getDataEmpty() {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void refreshOrLoadFail(String msg) {

    }

    @Override
    public void refreshDataSuccess(List<Comments> list) {

    }

    @Override
    public void loadMoreWeekDataSuccess(List<Comments> entities) {

    }

    @Override
    public void rePlaying() {

    }

    @Override
    public void replaySuccess() {

    }

    @Override
    public void replayFail() {

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (player != null) {
                    player.startPlay();
                    this.fab.setVisibility(View.GONE);
                }
                break;
            case R.id.playButton:
                app_bar.setExpanded(true, true);
                if (player != null) {
                    player.startPlay();
                    this.fab.setVisibility(View.GONE);
                }
                break;
        }
    }
}
