package com.lcc.view.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.lcc.activity.R;
import com.lcc.utils.DataTypeUtils;
import com.lcc.utils.MediaPlayerUtils;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;
import zsbpj.lccpj.view.progress.VideoProgressWheel;

public class VideoControllerView extends FrameLayout implements View.OnTouchListener, Slider.OnValueChangedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final long delayMillis = 1500;
    private VideoView mVideoView;
    private VideoProgressWheel mProgressWheel;
    private ImageButton viewPlay;
    private Slider slider;
    private Handler handler = new Handler();
    private Context context;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slider.setVisibility(View.GONE);
        }
    };

    public VideoControllerView(Context context) {
        super(context);
        initView(context);
    }

    public VideoControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public VideoControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View v = LayoutInflater.from(context).inflate(zsbpj.lccpj.R.layout.view_video_controller, null);
        addView(v);
        mVideoView = (VideoView) findViewById(zsbpj.lccpj.R.id.videoView);
        mVideoView.setOnTouchListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnCompletionListener(this);

        mProgressWheel = (VideoProgressWheel) findViewById(zsbpj.lccpj.R.id.progressWheel);
        mProgressWheel.startSpinning();
        viewPlay = (ImageButton) findViewById(zsbpj.lccpj.R.id.button_play);

        slider = (Slider) findViewById(zsbpj.lccpj.R.id.slider);
        slider.setOnValueChangedListener(this);
        slider.setOnNumberIndicatorConvert(new Slider.OnNumberIndicatorConvert() {
            @Override
            public String covert(long val) {
                //对时间的格式化
                return MediaPlayerUtils.getVideoDisplayTime(val);
            }
        });
        setVideoPlayButton();
    }

    private void setVideoPlayButton() {
        viewPlay.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_circle_selector));
        viewPlay.setVisibility(GONE);
        viewPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playStateChange();
            }
        });
    }

    /**
     * 播放器是否暂停
     */
    private boolean isVideoPause() {
        return viewPlay.getVisibility() == View.VISIBLE;
    }

    private void updateTimeTask() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delayMillis);
    }

    /**
     * 播放器的状态的改变
     */
    private void playStateChange() {
        updateTimeTask();
        if (!isVideoPause()) {
            slider.setVisibility(View.VISIBLE);
            slider.setValue(DataTypeUtils.toInt(mVideoView.getCurrentPosition()));

            mProgressWheel.setVisibility(View.INVISIBLE);
            viewPlay.setVisibility(View.VISIBLE);
            mVideoView.pause();
        } else {
            viewPlay.setVisibility(View.INVISIBLE);
            mVideoView.start();
        }
    }

    public void setVideoPath(String videoUrl) {
        mVideoView.setVideoPath(videoUrl);
    }

    /**
     * 开始播放
     */
    public void start() {
        mVideoView.start();
    }

    /**
     * 暂停播放
     */
    public void pause() {
        mVideoView.pause();
    }

    /**
     * 释放资源
     */
    public void release() {
        mVideoView.stopPlayback();
    }

    /**
     * 获取VideoView
     */
    public VideoView getVideoView() {
        return mVideoView;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        //视频暂停中不显示缓冲的进度
        if (isVideoPause()) {
            return;
        }
        if (percent >= 90) {
            mProgressWheel.setVisibility(View.INVISIBLE);
        } else {
            mProgressWheel.setVisibility(View.VISIBLE);
            mProgressWheel.setText(percent + "%");
        }
    }
    @TargetApi(21)
    @Override
    public void onCompletion(MediaPlayer mp) {
        viewPlay.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_replay_white_48dp, null));
        viewPlay.setVisibility(View.VISIBLE);
        viewPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.seekTo(0);
                setVideoPlayButton();
            }
        });

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        slider.setMin(0);
        slider.setMax(DataTypeUtils.toInt(mp.getDuration()));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        playStateChange();
        return false;
    }

    @Override
    public void onValueChanged(int value) {
        updateTimeTask();
        //当播放器的值改变
        mVideoView.seekTo(value);
        mVideoView.start();
        viewPlay.setVisibility(View.INVISIBLE);
    }
}
