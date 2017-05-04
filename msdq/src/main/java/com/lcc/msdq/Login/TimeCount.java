package com.lcc.msdq.login;

import android.os.CountDownTimer;
import android.widget.Button;

import com.lcc.view.PaperButton;

/**
 * Created by Administrator on 2017/1/17.
 */

public class TimeCount extends CountDownTimer {
    private PaperButton sendsmscode;

    public TimeCount(PaperButton sendsmscode, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.sendsmscode = sendsmscode;
    }

    @Override
    public void onFinish() {//计时完毕时触发
        sendsmscode.setText("重新发送");
        sendsmscode.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {//计时过程显示
        sendsmscode.setClickable(false);
        sendsmscode.setText(millisUntilFinished / 1000 + "秒");
    }
}
