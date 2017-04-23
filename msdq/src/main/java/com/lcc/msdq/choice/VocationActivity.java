package com.lcc.msdq.choice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lcc.App;
import com.lcc.adapter.LeftAdapter;
import com.lcc.adapter.RightAdapter;
import com.lcc.base.BaseActivity;
import com.lcc.db.test.UserInfo;
import com.lcc.entity.Type1;
import com.lcc.entity.Type2;
import com.lcc.frame.data.DataManager;
import com.lcc.msdq.MainActivity;
import com.lcc.msdq.R;
import com.lcc.mvp.presenter.ChoiceTypePresenter;
import com.lcc.mvp.presenter.impl.ChoicePresenterImpl;
import com.lcc.mvp.view.ChoiceTypeView;
import com.lcc.utils.ACache;
import com.lcc.view.loadview.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import zsbpj.lccpj.utils.GsonUtils;
import zsbpj.lccpj.view.toast.SuperCustomToast;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2017年04月21日16:05:11
 * Description:
 */
public class VocationActivity extends BaseActivity implements ChoiceTypeView ,View.OnClickListener{
    //添加了缓存
    private ACache mACache;
    //左右两侧的布局
    private ListView lv1, lv2;
    private LinearLayout lv1_layout;
    //左侧的数据
    private List<Type1> dataLeft = new ArrayList<>();
    //获取左侧数据
    private ChoiceTypePresenter choiceTypePresenter;
    //动态布局
    private LoadingLayout loading_layout;
    private LoadingLayout loading_layout2;
    //设置职业的变量。
    private String setResult;
    //view
    private View root;
    //是直接选择的还是
    private String result;

    public static void startVocationActivity(Activity startingActivity,String flag) {
        Intent intent = new Intent(startingActivity, VocationActivity.class);
        intent.putExtra("result", flag);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        mACache = ACache.get(VocationActivity.this);
        result = getIntent().getStringExtra("result");

        root = findViewById(R.id.root);
        findViewById(R.id.guillotine_hamburger).setOnClickListener(this);
        findViewById(R.id.iv_add).setOnClickListener(this);
        loading_layout = (LoadingLayout) findViewById(R.id.loading_layout);
        loading_layout2 = (LoadingLayout) findViewById(R.id.loading_layout2);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv1_layout = (LinearLayout) findViewById(R.id.lv_layout);
        choiceTypePresenter = new ChoicePresenterImpl(this);
        //判断是否存在缓存
        String r = mACache.getAsString("resultList");
        if (!TextUtils.isEmpty(r)) {
            Message message = handlers.obtainMessage();
            message.what = 1;
            message.obj = r;
            handlers.sendMessage(message);
        } else {
            choiceTypePresenter.getType1();
        }
    }

    @Override
    protected boolean Open() {
        return false;
    }

    @Override
    protected int getLayoutView() {
        return R.layout.vocation_layout;
    }

    Handler handlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result_json = msg.obj.toString();
            if (!TextUtils.isEmpty(result_json)) {
                try {
                    dataLeft = GsonUtils.fromJsonArray(result_json, Type1.class);
                    if (dataLeft != null && dataLeft.size() > 0) {
                        showPopupWindow();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 返回类型 显示双列的ListView的联动的效果
     */
    public void showPopupWindow() {
        loading_layout.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
        choiceTypePresenter.getType2(dataLeft.get(0).getN_id());
        final LeftAdapter leftAdapter = new LeftAdapter(VocationActivity.this, dataLeft);
        lv1.setAdapter(leftAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter() instanceof LeftAdapter) {
                    leftAdapter.setSelectItem(position);
                    leftAdapter.notifyDataSetChanged();
                    lv2.setVisibility(View.INVISIBLE);
                    rightData.clear();
                    rightData = new ArrayList<>();
                    String ids = dataLeft.get(position).getN_id();
                    choiceTypePresenter.getType2(ids);
                }
            }
        });
    }

    private List<Type2> rightData = new ArrayList<>();

    @Override
    public void getLoading1() {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void getDataEmpty1() {
        loading_layout.setLoadingLayout(LoadingLayout.NO_DATA);
    }

    @Override
    public void getLoading2() {
        loading_layout2.setLoadingLayout(LoadingLayout.NETWORK_LOADING);
    }

    @Override
    public void getDataEmpty2() {
        loading_layout2.setLoadingLayout(LoadingLayout.NO_DATA);
    }


    @Override
    public void getDataFail1(String msg) {
        loading_layout.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }

    @Override
    public void getDataSuccess1(String result_json) {
        try {
            dataLeft = GsonUtils.fromJsonArray(result_json, Type1.class);
            if (dataLeft != null && dataLeft.size() > 0) {
                showPopupWindow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataFail2(String msg) {
        loading_layout2.setLoadingLayout(LoadingLayout.NETWORK_ERROR);
    }

    private RightAdapter rightAdapter;

    @Override
    public void getDataSuccess2(String msg) {
        try {
            rightData = GsonUtils.fromJsonArray(msg, Type2.class);
            if (lv2.getVisibility() == View.INVISIBLE) {
                lv2.setVisibility(View.VISIBLE);
                lv1_layout.getLayoutParams().width = 0;
                rightAdapter = new RightAdapter(VocationActivity.this, rightData);
            }

            if (rightAdapter != null) {
                loading_layout2.setLoadingLayout(LoadingLayout.HIDE_LAYOUT);
                lv2.setAdapter(rightAdapter);
                rightAdapter.notifyDataSetChanged();
                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        setResult= rightData.get(position).getS_name()
                                +"."+ rightData.get(position).getS_id();
                        rightAdapter.setSelectItem(position);
                        rightAdapter.notifyDataSetInvalidated();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLoading() {

    }

    @Override
    public void setDataFail(String msg) {
        showSnackbar(root,"设置职业失败，请稍后再试");
    }

    @Override
    public void setDataSuccess() {
        UserInfo userInfo = DataManager.getUserInfo();
        userInfo.setZy(setResult);
        DataManager.editUser(userInfo);
        SuperCustomToast toasts = SuperCustomToast.getInstance(getApplicationContext());
        toasts.setDefaultTextColor(Color.WHITE);
        toasts.show("设置成功", R.layout.choice_toast_item, R.id.content_toast, VocationActivity.this);
        App.exit();
        if (TextUtils.isEmpty(result)) {
            finish();
        } else {
            startActivity(new Intent(VocationActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_add:
                if (TextUtils.isEmpty(setResult)){
                    showSnackbar(root,"请先选择职业");
                    return;
                }
                choiceTypePresenter.setDataType(setResult);
                break;
            case R.id.guillotine_hamburger:
                finish();
                break;
        }
    }
}
