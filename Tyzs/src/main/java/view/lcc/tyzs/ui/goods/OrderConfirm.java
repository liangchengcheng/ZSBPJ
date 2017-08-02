package view.lcc.tyzs.ui.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.OrderInfo;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 20:36
 * Description:  |
 */
public class OrderConfirm extends BaseActivity {

    //订单信息
    private ArrayList<OrderInfo> orderInfos;
    //商品列表
    private ListView lv_products_list;
    //全部数据
    private String shoujianren, phone, address, orderId;
    //价格
    private double prince;
    private TextView tv_order_name, tv_order_phone, tv_order_address, tv_order_sum;

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

        //获取上个页面传递过来的页面信息
        orderInfos = (ArrayList<OrderInfo>) getIntent().getSerializableExtra("data");
    }

}
