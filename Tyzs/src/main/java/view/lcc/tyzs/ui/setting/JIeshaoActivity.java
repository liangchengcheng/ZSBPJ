package view.lcc.tyzs.ui.setting;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.libs.PileLayout;
import view.lcc.tyzs.libs.entity.ItemEntity;
import view.lcc.tyzs.libs.util.Utils;
import view.lcc.tyzs.libs.widget.FadeTransitionImageView;
import view.lcc.tyzs.libs.widget.HorizontalTransitionLayout;
import view.lcc.tyzs.libs.widget.VerticalTransitionLayout;

/**
 * Author:       | 梁铖城
 * Email:        | 1038127753@qq.com
 * Date:         | 08-05 20:53
 * Description:  | JIeshaoActivity
 */
public class JIeshaoActivity extends BaseActivity{



    private View positionView;
    private PileLayout pileLayout;
    private List<ItemEntity> dataList;

    private int lastDisplay = -1;

    private ObjectAnimator transitionAnimator;
    private float transitionValue;
    private HorizontalTransitionLayout countryView, temperatureView;
    private VerticalTransitionLayout addressView, timeView;
    private FadeTransitionImageView bottomView;
    private Animator.AnimatorListener animatorListener;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jieshao_layout);

        positionView = findViewById(R.id.positionView);
        countryView = (HorizontalTransitionLayout) findViewById(R.id.countryView);
        temperatureView = (HorizontalTransitionLayout) findViewById(R.id.temperatureView);
        pileLayout = (PileLayout) findViewById(R.id.pileLayout);
        addressView = (VerticalTransitionLayout) findViewById(R.id.addressView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);
        timeView = (VerticalTransitionLayout) findViewById(R.id.timeView);
        bottomView = (FadeTransitionImageView) findViewById(R.id.bottomImageView);

        // 1. 状态栏侵入
        boolean adjustStatusHeight = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            adjustStatusHeight = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        // 2. 状态栏占位View的高度调整
        String brand = Build.BRAND;
        if (brand.contains("Xiaomi")) {
            Utils.setXiaomiDarkMode(this);
        } else if (brand.contains("Meizu")) {
            Utils.setMeizuDarkMode(this);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            adjustStatusHeight = false;
        }
        if (adjustStatusHeight) {
            adjustStatusBarHeight(); // 调整状态栏高度
        }

        animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                countryView.onAnimationEnd();
                temperatureView.onAnimationEnd();
                addressView.onAnimationEnd();
                bottomView.onAnimationEnd();
                timeView.onAnimationEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };


        // 3. PileLayout绑定Adapter
        initDataList();
        pileLayout.setAdapter(new PileLayout.Adapter() {
            @Override
            public int getLayoutId() {
                return R.layout.item_layout;
            }

            @Override
            public void bindView(View view, int position) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                    view.setTag(viewHolder);
                }

                Glide.with(JIeshaoActivity.this).load(dataList.get(position).getCoverImageUrl()).into(viewHolder.imageView);
            }

            @Override
            public int getItemCount() {
                return dataList.size();
            }

            @Override
            public void displaying(int position) {
                descriptionView.setText(dataList.get(position).getDescription());
                if (lastDisplay < 0) {
                    initSecene(position);
                    lastDisplay = 0;
                } else if (lastDisplay != position) {
                    transitionSecene(position);
                    lastDisplay = position;
                }
            }

            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
            }
        });
    }

    private void initSecene(int position) {
        countryView.firstInit(dataList.get(position).getCountry());
        temperatureView.firstInit(dataList.get(position).getTemperature());
        addressView.firstInit(dataList.get(position).getAddress());
        bottomView.firstInit(dataList.get(position).getMapImageUrl());
        timeView.firstInit(dataList.get(position).getTime());
    }

    private void transitionSecene(int position) {
        if (transitionAnimator != null) {
            transitionAnimator.cancel();
        }

        countryView.saveNextPosition(position, dataList.get(position).getCountry());
        temperatureView.saveNextPosition(position, dataList.get(position).getTemperature());
        addressView.saveNextPosition(position, dataList.get(position).getAddress());
        bottomView.saveNextPosition(position, dataList.get(position).getMapImageUrl());
        timeView.saveNextPosition(position, dataList.get(position).getTime());

        transitionAnimator = ObjectAnimator.ofFloat(this, "transitionValue", 0.0f, 1.0f);
        transitionAnimator.setDuration(300);
        transitionAnimator.start();
        transitionAnimator.addListener(animatorListener);

    }

    /**
     * 调整沉浸状态栏
     */
    private void adjustStatusBarHeight() {
        int statusBarHeight = Utils.getStatusBarHeight(this);
        ViewGroup.LayoutParams lp = positionView.getLayoutParams();
        lp.height = statusBarHeight;
        positionView.setLayoutParams(lp);
    }


    /**
     * 从asset读取文件json数据
     */
    private void initDataList() {
        dataList = new ArrayList<>();
        try {

            ItemEntity entity = new ItemEntity();
            entity.setAddress("公司简介");
            entity.setCountry("海斯富生物科技有限公司");
            entity.setCoverImageUrl(R.drawable.gsjs);
            entity.setDescription("海斯福集团健康产业下设烟台海斯富生物科技有限公司之一。" +
                    "具有研产销一体化的食疗产业平台，体质养生应用技术和产品开发中心等。通过体质辨识提供精准的体质养生服务，是中华体质养生产业的创领者");
            entity.setMapImageUrl(R.drawable.gsjs);
            entity.setTemperature("海斯福");
            entity.setTime("全国服务热线:4006-0535-00");
            dataList.add(entity);

            ItemEntity entity0 = new ItemEntity();
            entity0.setAddress("公司文化");
            entity0.setCountry("海斯富生物科技有限公司");
            entity0.setCoverImageUrl(R.drawable.ysheng);
            entity0.setDescription("海斯福是传播养生之道的事业平台，需要更多的仁人义士来参与经营，“格物，正心，修身，齐家，治国，平天下”是每位海斯福人学习、" +
                    "生活、事业能否成就的根本保证，通过以此树人让每个参与体质养生事业的人都能够成就人生。");
            entity0.setMapImageUrl(R.drawable.ysheng);
            entity0.setTemperature("海斯福");
            entity0.setTime("官方网站:http://www.healthfull.cn");
            dataList.add(entity0);

            ItemEntity entity1 = new ItemEntity();
            entity1.setAddress("关注我们");
            entity1.setCountry("海斯富生物科技有限公司");
            entity1.setCoverImageUrl(R.drawable.chapin);
            entity1.setDescription("可以通过官网关注我们或者微信添加公众号 haisifu 添加关注也可在APP商店搜索《海斯富健康生活商城》关注我们的产品");
            entity1.setMapImageUrl(R.drawable.chapin);
            entity1.setTemperature("海斯福");
            entity1.setTime("微信添加公众号 haisifu 关注我们");
            dataList.add(entity1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 属性动画
     */
    public void setTransitionValue(float transitionValue) {
        this.transitionValue = transitionValue;
        countryView.duringAnimation(transitionValue);
        temperatureView.duringAnimation(transitionValue);
        addressView.duringAnimation(transitionValue);
        bottomView.duringAnimation(transitionValue);
        timeView.duringAnimation(transitionValue);
    }

    public float getTransitionValue() {
        return transitionValue;
    }

    class ViewHolder {
        ImageView imageView;
    }


}

