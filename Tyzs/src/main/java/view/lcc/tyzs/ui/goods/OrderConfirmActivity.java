package view.lcc.tyzs.ui.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.OrderConfirmAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.Address;
import view.lcc.tyzs.bean.OrderInfo;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.view.JifenYueView;
import view.lcc.tyzs.ui.address.AddressListActivity;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 20:36
 * Description:  |
 */
public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener ,JifenYueView{
    //订单信息
    private ArrayList<OrderInfo> orderInfos;

    //全部数据
    private String shoujianren, addressPhone, address, addressId;
    //价格
    private double prince;


    //tv_order_sum (总额)
    private TextView tv_order_name, tv_order_phone, tv_order_address, tv_order_sum;
    //商品列表
    private ListView lv_products_list;
    //输入的留言
    private EditText et_other;
    //刷新积分点击事件
    private TextView tv_yue;
    //输入的积分
    private EditText et_jisuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirm_activity);
        initView();
    }

    private void initView() {
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        et_other = (EditText) findViewById(R.id.et_other);
        et_jisuan = (EditText) findViewById(R.id.et_jisuan);
        lv_products_list = (ListView) findViewById(R.id.lv_products_list);
        tv_order_sum = (TextView) findViewById(R.id.tv_order_sum);
        //获取上个页面传递过来的页面信息
        orderInfos = (ArrayList<OrderInfo>) getIntent().getSerializableExtra("data");
        if (orderInfos != null) {
            OrderConfirmAdapter adapter = new OrderConfirmAdapter(orderInfos, OrderConfirmActivity.this);
            lv_products_list.setAdapter(adapter);
        }
        //添加收获地址
        initAddress();
        initPrince();
        findViewById(R.id.btn_put_order).setOnClickListener(this);

    }

    //填充价格
    private void initPrince() {
        for (int i = 0; i < orderInfos.size(); i++) {
            prince += Double.parseDouble(orderInfos.get(i).getTrueprice())
                    * Double.parseDouble(orderInfos.get(i).getNumber());
        }
        DecimalFormat df = new DecimalFormat("#.##");
        prince = Double.parseDouble(df.format(prince));
        tv_order_sum.setText(prince + "");
    }

    //添加收获地址
    private void initAddress() {
        shoujianren = SharePreferenceUtil.getAddressPerson();
        addressPhone = SharePreferenceUtil.getAddressPhone();
        address = SharePreferenceUtil.getAddressInfo();
        addressId = SharePreferenceUtil.getAddressId();
        if (!TextUtils.isEmpty(shoujianren)) {
            tv_order_address.setText("地址：" + address);
            tv_order_phone.setText("电话：" + addressPhone);
            tv_order_name.setText("收件人：" + shoujianren);
        } else {
            tv_order_name.setText("请选择一个收货地址");
            tv_order_name.setTextColor(Color.RED);
        }
        findViewById(R.id.ll_order_linkman).setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Address bean = (Address) data.getSerializableExtra("address");
            addressId = bean.getAID();
            if (bean != null){
                tv_order_address.setText("收货地址：" + bean.getAddress());
                tv_order_phone.setText("联系电话：" + bean.getPhone());
                tv_order_name.setText("收货人：" + bean.getAddressee());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择地址
            case R.id.ll_order_linkman:
                Intent intent = new Intent(OrderConfirmActivity.this, AddressListActivity.class);
                intent.putExtra("action", "confirm");
                startActivityForResult(intent, 100);
                break;
            //购买
            case R.id.btn_put_order:
                break;
        }
    }

    @Override
    public void JifenYueLoading() {
        Frame.getInstance().toastPrompt("开始获取积分");
    }

    @Override
    public void JifenYueSuccess(String msg) {

    }

    @Override
    public void JifenYueFail(String msg) {
        Frame.getInstance().toastPrompt("获取积分失败");
    }

    @Override
    public void NetWorkErr(String msg) {
        Frame.getInstance().toastPrompt("网络不可用");
    }
}
