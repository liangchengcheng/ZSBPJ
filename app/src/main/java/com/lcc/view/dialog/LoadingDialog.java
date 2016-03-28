package com.lcc.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lcc.activity.R;

public class LoadingDialog extends Dialog {

    private String text;

    public LoadingDialog(Context context, String text) {
        super(context, R.style.progress_dialog);
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.progressbar_item);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) findViewById(R.id.tv_title);
        msg.setText(text);
        this.setCancelable(false);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        lp.alpha = 1.0f;
        dialogWindow.setAttributes(lp);
    }
}
