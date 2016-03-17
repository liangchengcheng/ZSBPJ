package com.lcc.activity;

import android.app.Activity;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lcc on 16/3/17.
 */
public class test extends Activity {

    private void showBSDialog() {
        final BottomSheetDialog dialog = new BottomSheetDialog(test.this);
        View view = LayoutInflater.from(test.this).inflate(R.layout.dialog_layout, null);
        dialog.setContentView(view);
        dialog.show();
    }
}
