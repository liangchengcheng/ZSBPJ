package com.lcc.msdq.compony;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.lcc.base.BaseActivity;
import com.lcc.entity.CompanyDescription;
import com.lcc.msdq.R;
import com.lcc.msdq.test.answer.photo.UILImageLoader;
import com.lcc.msdq.test.answer.photo.UILPauseOnScrollListener;
import com.lcc.mvp.presenter.ComDesAddPresenter;
import com.lcc.mvp.presenter.impl.ComDesAddPresenterImpl;
import com.lcc.mvp.view.ComDesAddView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import zsbpj.lccpj.frame.FrameManager;
import zsbpj.lccpj.frame.ImageManager;
import zsbpj.lccpj.view.simplearcloader.ArcConfiguration;
import zsbpj.lccpj.view.simplearcloader.SimpleArcDialog;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  CompanyAddActivity
 */
public class CompanyAddActivity extends BaseActivity implements ComDesAddView,View.OnClickListener {

    public static final String NAME = "name";
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    private String name;
    private Pattern intPattern = Pattern.compile("^[-\\+]?[\\d]*\\.0*$");
    private String pro, city, dis;
    public FunctionConfig functionConfig;
    private List<File> files = new ArrayList<>();
    private ComDesAddPresenter presenter;

    private TextView tv_position;
    private EditText editText_name,editText_phone,editText_add,editText_summary;
    private SimpleArcDialog mDialog;
    private ImageView iv_question_des;
    private CompanyDescription companyDescription;

    @Override
    protected int getLayoutView() {
        return R.layout.com_des_add;
    }

    @Override
    protected void initView() {
        presenter=new ComDesAddPresenterImpl(this);
        companyDescription=new CompanyDescription();
        iv_question_des = (ImageView) findViewById(R.id.iv_question_des);
        iv_question_des.setOnClickListener(this);
        findViewById(R.id.tv_add_pic).setOnClickListener(this);
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        tv_position = (TextView) findViewById(R.id.tv_position);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_phone = (EditText) findViewById(R.id.editText_phone);
        editText_add = (EditText) findViewById(R.id.editText_add);
        editText_summary = (EditText) findViewById(R.id.editText_summary);
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

    private void showPopMenu() {
        initImageLoader(CompanyAddActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_layout, null);
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(CompanyAddActivity.this.findViewById(R.id.rootview),
                Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
            }
        });
        view.findViewById(R.id.ll_qx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        view.findViewById(R.id.ll_xc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                window.dismiss();
            }
        });
        view.findViewById(R.id.ll_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                window.dismiss();
            }
        });
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        ImageLoader.getInstance().init(config.build());
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback =
            new GalleryFinal.OnHanlderResultCallback() {
                @Override
                public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                    if (resultList != null && resultList.size() > 0) {
                        ImageManager.getInstance().loadLocalImage(CompanyAddActivity.this,
                                resultList.get(0).getPhotoPath(), iv_question_des);
                        for (int i = 0; i < resultList.size(); i++) {
                            File file = new File(resultList.get(i).getPhotoPath());
                            files.add(file);
                        }
                    }
                }

                @Override
                public void onHanlderFailure(int requestCode, String errorMsg) {
                    Toast.makeText(CompanyAddActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_question_des:
            case R.id.tv_add_pic:
                showPopMenu();
                break;

            case R.id.buttonSignUp:
                if (TextUtils.isEmpty(editText_name.getText().toString())||
                        TextUtils.isEmpty(editText_phone.getText().toString())||
                        TextUtils.isEmpty(editText_add.getText().toString())||
                        TextUtils.isEmpty(editText_summary.getText().toString())){
                    FrameManager.getInstance().toastPrompt("有未填写的数据");
                    return;
                }
                adding();
                companyDescription.setAuthor("18813149871");
                companyDescription.setCompany_name(editText_name.getText().toString());
                companyDescription.setCompany_phone(editText_phone.getText().toString());
                companyDescription.setLocation(editText_add.getText().toString());
                companyDescription.setCompany_description(editText_summary.getText().toString());
                presenter.ComDesAdd(companyDescription, files);
                break;
        }
    }
}
