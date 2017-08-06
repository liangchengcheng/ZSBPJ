package view.lcc.tyzs.ui.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.OrderInfo;
import view.lcc.tyzs.bean.ShoppingBean;
import view.lcc.tyzs.frame.ImageManager;
import view.lcc.tyzs.ui.login.LoginMainActivity;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.view.GoodsToCarDialog;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 22:02
 * Description:  |
 */
public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener, GoodsToCarDialog.NumChangeListener {
    //显示的图片
    private ImageView iv_head_image;
    //名字
    private TextView good_name;
    //零售价格
    private TextView tv_prince;
    //代理的价格
    private TextView tv_prince_vip;
    //商品的介绍
    private TextView tv_decription;

    //传递过来的数据
    private ShoppingBean shoppingBean;
    //购买的价格
    private String total_prince;
    private ArrayList<OrderInfo> orderlist = new ArrayList<>();
    //自己的权限
    private String rate;
    //订单
    private OrderInfo info = new OrderInfo();
    //数量
    private String num = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details_activity);

        iv_head_image = (ImageView) findViewById(R.id.iv_head_image);
        good_name = (TextView) findViewById(R.id.good_name);
        tv_prince = (TextView) findViewById(R.id.tv_prince);
        tv_prince_vip = (TextView) findViewById(R.id.tv_prince_vip);
        tv_decription = (TextView) findViewById(R.id.tv_decription);

        findViewById(R.id.add_car).setOnClickListener(this);
        findViewById(R.id.add_buy).setOnClickListener(this);

        shoppingBean = (ShoppingBean) getIntent().getSerializableExtra("bean");
        ImageManager.getInstance().loadUrlImage(GoodsDetailsActivity.this, AppConstants.PIC_URL + shoppingBean.getGoodImgUrl(), iv_head_image);
        good_name.setText(shoppingBean.getGoodName());
        tv_prince.setText(shoppingBean.getGoodPrice());
        tv_decription.setText(shoppingBean.getGoodDescription());

        //获取权限
        rate = SharePreferenceUtil.getRate();
        if (!TextUtils.isEmpty(rate)) {
            double sum = Double.parseDouble(shoppingBean.getGoodCost()) + Double.parseDouble(shoppingBean.getGoodProfit()) * Double.parseDouble(rate);
            DecimalFormat df = new DecimalFormat("######0.00");
            tv_prince_vip.setText(df.format(sum) + "");
            total_prince = df.format(sum) + "";
        } else {
            tv_prince_vip.setText("登录后显示");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加购物车
            case R.id.add_car:
                GoodsToCarDialog carDialog = new GoodsToCarDialog(GoodsDetailsActivity.this,num,shoppingBean);
                carDialog.setOnNumChangeListener(this);
                carDialog.show();
                break;
            //购买
            case R.id.add_buy:
                //判断用户是否登入
                String name = SharePreferenceUtil.getName();
                if (TextUtils.isEmpty(name)) {
                    LoginMainActivity.startLoginMainActivity("", GoodsDetailsActivity.this);
                    return;
                }

                orderlist = new ArrayList<>();
                //此处需要再次判断
                if (TextUtils.isEmpty(rate)) {
                    info.setTrueprice(total_prince);
                } else {
                    info.setTrueprice(shoppingBean.getGoodPrice());
                }
                info.setGID(shoppingBean.getGoodID());
                info.setName(shoppingBean.getGoodName());
                info.setNumber(num);
                orderlist.add(info);

                Intent intent = new Intent(GoodsDetailsActivity.this, OrderConfirmActivity.class);
                intent.putExtra("data", orderlist);
                intent.putExtra("number", num);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNumChange(String num) {
        if (!TextUtils.isEmpty(num)) {
            this.num = num;
        }
    }
}
