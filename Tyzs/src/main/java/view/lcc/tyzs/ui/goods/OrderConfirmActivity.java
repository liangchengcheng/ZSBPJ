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

import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import view.lcc.tyzs.R;
import view.lcc.tyzs.adapter.OrderConfirmAdapter;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.bean.Address;
import view.lcc.tyzs.bean.OrderInfo;
import view.lcc.tyzs.frame.Frame;
import view.lcc.tyzs.mvp.presenter.JifenYuePresenter;
import view.lcc.tyzs.mvp.presenter.OrderConfirmPresenter;
import view.lcc.tyzs.mvp.presenter.impl.JifenYuePresenterImpl;
import view.lcc.tyzs.mvp.presenter.impl.OrderConfrimPresenterImpl;
import view.lcc.tyzs.mvp.view.JifenYueView;
import view.lcc.tyzs.mvp.view.OrderConfirmView;
import view.lcc.tyzs.ui.address.AddressListActivity;
import view.lcc.tyzs.utils.DialogUtils;
import view.lcc.tyzs.utils.Md5Utils;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.utils.SingleTrueUtils;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 20:36
 * Description:  |
 */
// TODO: 2017/8/2 获取积分加弹窗
public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener, JifenYueView, OrderConfirmView {
    //订单信息
    private ArrayList<OrderInfo> orderInfos;
    //全部数据
    private String shoujianren, addressPhone, address, addressId;
    //价格
    private double prince;
    //获取积分余额
    private JifenYuePresenter jifenPresenter;
    //提交订单
    private OrderConfirmPresenter orderConfirmPresenter;
    //可用积分
    private String point;

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
    //积分
    private TextView tv_jf;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirm_activity);
        jifenPresenter = new JifenYuePresenterImpl(this);
        orderConfirmPresenter = new OrderConfrimPresenterImpl(this);
        initView();
    }

    private void initView() {
        tv_jf = (TextView) findViewById(R.id.tv_jf);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        et_other = (EditText) findViewById(R.id.et_other);
        et_jisuan = (EditText) findViewById(R.id.et_jisuan);
        lv_products_list = (ListView) findViewById(R.id.lv_products_list);
        tv_order_sum = (TextView) findViewById(R.id.tv_order_sum);

        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_order_phone = (TextView) findViewById(R.id.tv_order_phone);
        tv_order_address = (TextView) findViewById(R.id.tv_order_address);
        findViewById(R.id.btn_put_order).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);

        //获取上个页面传递过来的页面信息
        orderInfos = (ArrayList<OrderInfo>) getIntent().getSerializableExtra("data");
        if (orderInfos != null) {
            OrderConfirmAdapter adapter = new OrderConfirmAdapter(orderInfos, OrderConfirmActivity.this);
            lv_products_list.setAdapter(adapter);
        }
        //添加收获地址
        initAddress();
        //计算价格
        if (orderInfos != null){
            initPrince();
        }
        jifenPresenter.jifenYue();
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
            if (bean != null) {
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
                if (TextUtils.isEmpty(point)) {
                    Frame.getInstance().toastPrompt("获取积分失败，请稍后再试");
                    return;
                }
                //收获地址
                if (TextUtils.isEmpty(addressId)) {
                    Frame.getInstance().toastPrompt("收获地址不能为空");
                    return;
                }
                //商品的总价格
                if (prince <= 1) {
                    et_jisuan.setText("0");
                    Frame.getInstance().toastPrompt("商品总额小于1不允许使用积分");
                }
                // TODO: 2017/8/2 这个地方看一下
                point = prince + "";
                prince = 0;

                //计算商品价格
                ArrayList<Map<String, String>> orderInfo = new ArrayList<Map<String, String>>();
                for (int i = 0; i < orderInfos.size(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("GID", orderInfos.get(i).getGID());
                    map.put("number", orderInfos.get(i).getNumber());
                    orderInfo.add(map);
                    prince += Double.parseDouble(orderInfos.get(i).getTrueprice())
                            * Double.parseDouble(orderInfos.get(i).getNumber());
                }
                Gson gson = new Gson();
                String orderinfo = gson.toJson(orderInfo);

                //计算订单内容
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("user", SharePreferenceUtil.getName());
                maps.put("AID", addressId);
                maps.put("msg", et_other.getText().toString().trim());
                maps.put("orderTotal", prince + "");
                maps.put("point", point);
                maps.put("creatTime", SingleTrueUtils.singlechou(System.currentTimeMillis()));
                maps.put("orderinfo", orderinfo);
                String content = gson.toJson(maps);
                orderConfirmPresenter.orderConfirm(Md5Utils.encode(content).toUpperCase(), content);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void JifenYueLoading() {
        createDialog(R.string.get_point);
    }

    @Override
    public void JifenYueSuccess(String result) {
        closeDialog();
        try {
            JSONObject jsonObject = new JSONObject(result);
            String resultJson = jsonObject.getString("resultjson");
            JSONObject js = new JSONObject(resultJson);

            String p = js.getString("balance");
            String[] results = p.split(",");
            if (results.length == 2) {
                String[] cz = results[1].split("=");
                point = cz[1];
            } else {
                String[] xt = results[0].split("=");
                if (xt[0].contains("系统")) {
                    point = "0";
                } else {
                    point = xt[1];
                }
            }

            tv_yue.setText("积分:" + point + "(1积分抵1元)");
            // TODO: 2017/8/7 自己加的
            tv_jf.setText("积分:" + point + "(1积分抵1元)");
            if (Double.parseDouble(point) < prince) {
                DialogUtils.showTip(OrderConfirmActivity.this,"充值积分余额不足，请充值");
            }
            //0积分的话就直接就是不可以用积分的状态
            if (point.equals("0")) {
                et_jisuan.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void JifenYueFail(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("获取积分失败");
    }

    @Override
    public void OrderConfirmLoading() {
        createDialog(R.string.send_info);
    }

    @Override
    public void OrderConfirmSuccess(String result) {
        closeDialog();
        try {
            JSONObject jsonObject = new JSONObject(result);
            String resultjson = jsonObject.getString("resultjson");
            JSONObject j2 = new JSONObject(resultjson);
            String verification = j2.getString("verification").toUpperCase();
            String content = j2.getString("content");
            String contentmd5 = Md5Utils.encode(content);
            if (verification.equals(contentmd5.toUpperCase())) {
                DecimalFormat df = new DecimalFormat("######0.00");
                tv_order_name = (TextView) findViewById(R.id.tv_order_name);
                tv_order_phone = (TextView) findViewById(R.id.tv_order_phone);
                tv_order_address = (TextView) findViewById(R.id.tv_order_address);
                /*sp.edit().putString("dizhis", tv_order_address.getText().toString())
                        .putString("phones", tv_order_phone.getText().toString())
                        .putString("names", tv_order_name.getText().toString())
                        .putString("princes", df.format(prince - Double.parseDouble(point))).commit();*/
                //说明他的验证码是正确的
                JSONObject jsonObject1 = new JSONObject(content);
                String orderinfo = jsonObject1.getString("orderinfo");
                String orderTotal = jsonObject1.getString("orderTotal");

                String OID = jsonObject1.getString("OID");

                if (!orderTotal.equals(prince + "")) {
                    prince = Double.parseDouble(orderTotal);
                }
                Intent intent = new Intent(OrderConfirmActivity.this, OrderResultActivity.class);
                intent.putExtra("point", point);
                //订单id
                intent.putExtra("OID", OID);
                intent.putExtra("dizhis", tv_order_address.getText().toString());
                intent.putExtra("phones", tv_order_phone.getText().toString());
                intent.putExtra("names", tv_order_name.getText().toString());
                //实际支付
                intent.putExtra("princes", df.format(prince - Double.parseDouble(point)));
                startActivity(intent);
            } else {
                Frame.getInstance().toastPrompt("订单可能被篡改了");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OrderConfirmFail(String msg) {
        closeDialog();
        if (!TextUtils.isEmpty(msg)){
            Frame.getInstance().toastPrompt(msg);
        }else {
            Frame.getInstance().toastPrompt("提交订单失败，请稍后再试");
        }

    }

    @Override
    public void NetWorkErr(String msg) {
        closeDialog();
        Frame.getInstance().toastPrompt("网络不可用");
    }


}
