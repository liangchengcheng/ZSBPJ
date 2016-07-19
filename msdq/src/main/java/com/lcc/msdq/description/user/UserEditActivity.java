package com.lcc.msdq.description.user;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lcc.base.BaseActivity;
import com.lcc.entity.UserInfo;
import com.lcc.msdq.R;
import com.lcc.msdq.test.answer.photo.UILImageLoader;
import com.lcc.msdq.test.answer.photo.UILPauseOnScrollListener;
import com.lcc.mvp.presenter.UserEditPresenter;
import com.lcc.mvp.presenter.impl.UserEditPresenterImpl;
import com.lcc.mvp.view.UserEditView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
 * Description:  UserEditActivity
 */
public class UserEditActivity extends BaseActivity implements View.OnClickListener, UserEditView {

    private ImageView iv_question_des;
    private ImageView iv_save;
    private SimpleArcDialog mDialog;
    //昵称 性别 签名 邮箱
    private EditText edit_nickname, et_xb, et_qm, et_email;

    private List<File> files = new ArrayList<>();
    public FunctionConfig functionConfig;
    private UserEditPresenter userEditPresenter;
    private UserInfo userInfo = new UserInfo();
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    @Override
    protected void initView() {
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

        edit_nickname = (EditText) findViewById(R.id.edit_nickname);
        et_xb = (EditText) findViewById(R.id.et_xb);
        et_qm = (EditText) findViewById(R.id.et_qm);
        et_email = (EditText) findViewById(R.id.et_email);

        iv_question_des = (ImageView) findViewById(R.id.iv_question_des);
        iv_question_des.setOnClickListener(this);
        iv_save = (ImageView) findViewById(R.id.iv_save);
        iv_save.setOnClickListener(this);
        userEditPresenter = new UserEditPresenterImpl(this);
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_info;
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
                public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {
                    if (resultList != null && resultList.size() > 0) {
                        ImageManager.getInstance().loadLocalImage(UserEditActivity.this,
                                resultList.get(0).getPhotoPath(), iv_question_des);
                        for (int i = 0; i < resultList.size(); i++) {
                            File file = new File(resultList.get(i).getPhotoPath());
                            files.add(file);
                        }
                    }
                }

                @Override
                public void onHanlderFailure(int requestCode, String errorMsg) {
                    Toast.makeText(UserEditActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                }
            };

    private void showPopMenu() {
        initImageLoader(UserEditActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_layout, null);
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(UserEditActivity.this.findViewById(R.id.rootview),
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
                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig,
                        mOnHanlderResultCallback);
                window.dismiss();
            }
        });

        view.findViewById(R.id.ll_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig,
                        mOnHanlderResultCallback);
                window.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_question_des:
                showPopMenu();
                break;

            case R.id.iv_save:
                sendUserInfo();
                break;
        }
    }

    private void sendUserInfo() {
        userInfo.setEmail("");
        userInfo.setNickname("");
        userInfo.setQm("");
        userInfo.setXb("");

        String nickname = edit_nickname.getText().toString();
        String xb = et_xb.getText().toString();
        String qm = et_qm.getText().toString();
        String email = et_email.getText().toString();

        userInfo.setEmail(email);
        userInfo.setNickname(nickname);
        userInfo.setQm(qm);
        userInfo.setXb(xb);
        userEditPresenter.userEdit(userInfo, files);
    }

    @Override
    public void showLoading() {
        mDialog = new SimpleArcDialog(this);
        ArcConfiguration arcConfiguration = new ArcConfiguration(this);
        arcConfiguration.setText("正在登录...");
        mDialog.setConfiguration(arcConfiguration);
        mDialog.show();
    }

    private void closeDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void UserEditFail(String msg) {
        closeDialog();
        FrameManager.getInstance().toastPrompt("提交信息失败" + msg);
    }

    @Override
    public void UserEditSuccess() {
        closeDialog();
        FrameManager.getInstance().toastPrompt("提交信息成功");
    }
}
