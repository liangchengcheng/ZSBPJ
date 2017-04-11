package view.lcc.wyzsb.ui.activity.video;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.base.BaseActivity;
import view.lcc.wyzsb.utils.MediaUtils;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月11日13:46:31
 * Description:
 */
public class VideoDetailsActivity extends BaseActivity{
    private PlayerView player;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.video_details, null);
        setContentView(rootView);
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

        //常亮
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
        list = new ArrayList<VideoijkBean>();
        // 有部分视频加载有问题，这个视频是有声音显示不出图像的，
        // 没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
        // 这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
        String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        VideoijkBean m1 = new VideoijkBean();
        m1.setStream("标清");
        m1.setUrl(url1);
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
        }.setTitle("梁铖城").setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                .setScaleType(PlayStateParams.fillparent).forbidTouch(false).hideSteam(true)
                .hideCenterPlayer(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.article_des)
                                .error(R.color.article_title)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(list)
                .setChargeTie(true,60);
        player.startPlay();

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
}
