package com.lcc.view.video;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;

import io.vov.vitamio.MediaPlayer;

public class VideoControllerView extends Fragment implements View.OnTouchListener, Slider.OnValueChangedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onValueChanged(int value) {

    }
}
