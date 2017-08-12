package view.lcc.tyzs.ui.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.BaseActivity;
import view.lcc.tyzs.ui.order.OrderMainActivity;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 23:06
 * Description:  |
 */
public class OrderResultActivity extends BaseActivity {

    private TextView
            tv_result_jine,
            tv_result_person,
            tv_result_phone,
            tv_result_address;
    private TextView tv_result;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_result);
        initView();
    }

    private void initView() {
        tv_result_address = (TextView) findViewById(R.id.tv_result_address);
        tv_result_phone = (TextView) findViewById(R.id.tv_result_phone);
        tv_result_person = (TextView) findViewById(R.id.tv_result_person);
        tv_result_jine = (TextView) findViewById(R.id.tv_result_jine);

        String princes = getIntent().getStringExtra("princes");
        String point = getIntent().getStringExtra("point");
        String names = getIntent().getStringExtra("names");
        String phones = getIntent().getStringExtra("phones");
        String dizhis = getIntent().getStringExtra("dizhis");
        tv_result_jine.setText("实付金额：" + princes + "元(" + "使用积分" + point + ")");
        tv_result_person.setText(names);
        tv_result_phone.setText(phones);
        tv_result_address.setText(dizhis);

        tv_result= (TextView) findViewById(R.id.tv_result);
        tv_result.setText("订单结果：恭喜你订单提交成功。");

        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderResultActivity.this, OrderMainActivity.class));
                finish();
            }
        });
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
