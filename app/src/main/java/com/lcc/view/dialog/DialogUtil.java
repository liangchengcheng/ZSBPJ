package com.lcc.view.dialog;

import android.support.v7.app.AppCompatActivity;

import com.lcc.activity.R;

/**
 * TODO 根据 http://www.jianshu.com/p/af6499abd5c2 这篇文章优化 DialogFragment
 */
public class DialogUtil {
    public static void showAbout(AppCompatActivity activity){
        WebDialog.show(activity, activity.getSupportFragmentManager(), "关于", "about.html", "about", R.color.colorAccent);
    }

    public static void showChangelog(AppCompatActivity activity){
        WebDialog.show(activity, activity.getSupportFragmentManager(), "更新日志", "changelog.html", "changelog", R.color.colorAccent);
    }
}
