package com.lcc.activity.setting;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lcc.activity.R;
import com.lcc.adapter.ColorsListAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.utils.DialogUtils;
import com.lcc.utils.PreferenceUtils;
import com.lcc.utils.ThemeUtils;

import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    protected BaseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceUtils = PreferenceUtils.getInstance(SettingActivity.this);
        activity = (BaseActivity) SettingActivity.this;
    }

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle("系统设置");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        findViewById(R.id.tx_changetheme).setOnClickListener(this);
    }

    @Override
    protected boolean Open() {
        return true;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_setting;
    }

    private void showThemeDialog() {

        android.support.v7.app.AlertDialog.Builder builder = DialogUtils.makeDialogBuilder(activity);
        builder.setTitle("更换主题");
        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
                R.drawable.pink_round, R.drawable.green_round};
        List<Integer> list = Arrays.asList(res);
        ColorsListAdapter adapter = new ColorsListAdapter(SettingActivity.this, list);
        adapter.setCheckItem(ThemeUtils.getCurrentTheme(activity).getIntValue());
        GridView gridView = (GridView)LayoutInflater.from(activity).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final android.support.v7.app.AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                int value = getCurrentTheme().getIntValue();
                if (value != position) {
                    preferenceUtils.saveParam(getString(R.string.change_theme_key), position);
                    changeTheme(ThemeUtils.Theme.mapValueToTheme(position));
                }
            }
        });
//
//        AlertDialog.Builder builder = generateDialogBuilder();
//        builder.setTitle("主题颜色");
//        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
//                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
//                R.drawable.pink_round, R.drawable.green_round};
//        List<Integer> list = Arrays.asList(res);
//        ColorsListAdapter adapter = new ColorsListAdapter(SettingActivity.this, list);
//        adapter.setCheckItem(getCurrentTheme().getIntValue());
//        GridView gridView = (GridView) LayoutInflater.from(SettingActivity.this).inflate(R.layout.colors_panel_layout, null);
//        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//        gridView.setCacheColorHint(0);
//        gridView.setAdapter(adapter);
//        builder.setView(gridView);
//        final AlertDialog dialog = builder.show();
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                dialog.dismiss();
//                int value = getCurrentTheme().getIntValue();
//                if (value != position) {
//                    preferenceUtils.saveParam(getString(R.string.change_theme_key), position);
//                    changeTheme(ThemeUtils.Theme.mapValueToTheme(position));
//                }
//            }
//        });
    }

    protected AlertDialog.Builder generateDialogBuilder() {
        ThemeUtils.Theme theme = getCurrentTheme();
        AlertDialog.Builder builder;
        switch (theme) {
            case BROWN:
                builder = new AlertDialog.Builder(SettingActivity.this, R.style.BrownDialogTheme);
                break;
            case BLUE:
                builder = new AlertDialog.Builder(SettingActivity.this, R.style.BlueDialogTheme);
                break;
            case BLUE_GREY:
                builder = new AlertDialog.Builder(SettingActivity.this, R.style.BlueGreyDialogTheme);
                break;
            default:
                builder = new AlertDialog.Builder(SettingActivity.this, R.style.RedDialogTheme);
                break;
        }
        return builder;
    }

    private void changeTheme(ThemeUtils.Theme theme) {
        if (activity == null)
            return;
        EventBus.getDefault().post(0x02);
        activity.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_changetheme:
                showThemeDialog();
                break;
        }
    }
}
