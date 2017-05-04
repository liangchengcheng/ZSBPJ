package view.lcc.wyzsb.ui.activity.login;

import android.os.CountDownTimer;

import view.lcc.wyzsb.view.PaperButton;

/**
 * Created by Administrator on 2017/1/17.
 */

public class TimeCount extends CountDownTimer {

    private PaperButton sendsmscode;

    public TimeCount(PaperButton sendsmscode, long millisInFuture, long countDownInterval) {
        //参数依次为总时长,和计时的时间间隔
        super(millisInFuture, countDownInterval);
        this.sendsmscode = sendsmscode;
    }

    @Override
    public void onFinish() {
        //计时完毕时触发
        sendsmscode.setText("重新发送");
        sendsmscode.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //计时过程显示
        sendsmscode.setClickable(false);
        sendsmscode.setText(millisUntilFinished / 1000 + "秒");
    }
}
