package com.lcc.msdq.compony;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.lcc.base.BaseActivity;
import com.lcc.entity.CompanyDescription;
import com.lcc.msdq.R;
import com.lcc.msdq.test.answer.photo.UILImageLoader;
import com.lcc.msdq.test.answer.photo.UILPauseOnScrollListener;
import com.lcc.mvp.view.ComDesAddView;

import java.util.Map;
import java.util.regex.Pattern;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.view.simplearcloader.ArcConfiguration;
import zsbpj.lccpj.view.simplearcloader.SimpleArcDialog;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  CompanyAddActivity
 */
public class CompanyAddActivity extends BaseActivity implements ComDesAddView {

    public static final String NAME = "name";
    private String name;
    private Pattern intPattern = Pattern.compile("^[-\\+]?[\\d]*\\.0*$");
    private String pro, city, dis;
    public FunctionConfig functionConfig;

    private TextView tv_position;
    private EditText editText_name;
    private SimpleArcDialog mDialog;

    @Override
    protected int getLayoutView() {
        return R.layout.com_des_add;
    }

    @Override
    protected void initView() {
        tv_position = (TextView) findViewById(R.id.tv_position);
        editText_name = (EditText) findViewById(R.id.editText_name);
        initData();
    }

    private void initData() {
        name = getIntent().getStringExtra(NAME);
        editText_name.setText(name);
        initPic();

        final Map map = (Map) getIntent().getSerializableExtra("addressInfo");
        pro = getString(map, "provName", "");
        city = getString(map, "cityName", "");
        dis = getString(map, "districtName", "");
        String areaName = String.format("%s %s %s", pro, city, dis);
        tv_position.setText(areaName);
    }

    private void initPic() {
        ThemeConfig themeConfig = ThemeConfig.GREEN;
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .setMutiSelectMaxSize(8)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, new UILImageLoader(), themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new UILPauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    public String getString(Map map, String key, String defaultValue) {
        Object obj = map.get(key);
        return obj == null ? defaultValue : (obj instanceof Number &&
                intPattern.matcher(obj.toString()).matches() ?
                String.valueOf(Long.valueOf(((Number) obj).longValue())) : obj.toString());
    }

    @Override
    public void adding() {
        mDialog = new SimpleArcDialog(this);
        ArcConfiguration arcConfiguration = new ArcConfiguration(this);
        arcConfiguration.setText("正在提交数据...");
        mDialog.setConfiguration(arcConfiguration);
        mDialog.show();
    }

    @Override
    public void addSuccess() {
        closeDialog();
        FrameManager.getInstance().toastPrompt("发布成功");
    }

    @Override
    public void addFail() {
        closeDialog();
        FrameManager.getInstance().toastPrompt("发布失败");
    }

    private void closeDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
