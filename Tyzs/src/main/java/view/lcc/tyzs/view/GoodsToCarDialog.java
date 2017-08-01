package view.lcc.tyzs.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.ShoppingBean;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-01 22:34
 * Description:  |购物车的数量变化
 */
public class GoodsToCarDialog extends Dialog implements View.OnClickListener {
    private Button btn_ok;
    private Button btn_cancel;

    private TextView tv_price;
    private TextView name;
    private TextView tv_count;

    private NumEditText net_numedit;

    private int count;
    private ShoppingBean shoppingBean;


    public GoodsToCarDialog(Context context) {
        super(context);
    }

    // TODO: 2017/8/1 用计划巡查的样式
    public GoodsToCarDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected GoodsToCarDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_ok = (Button) findViewById(R.id.btn_cancel);
        btn_cancel = (Button) findViewById(R.id.btn_ok);
        tv_price = (TextView) findViewById(R.id.tv_dialog_cart_price);
        name = (TextView) findViewById(R.id.name);
        tv_count = (TextView) findViewById(R.id.tv_dialog_cart_count);
        net_numedit = (NumEditText) findViewById(R.id.net_dialog_count);
        setContentView(R.layout.dialog_edit_cart_num);
        setCanceledOnTouchOutside(false);


        net_numedit.setNum(count);
        name.setText(shoppingBean.getGoodName());
        tv_count.setText(String.valueOf(count));

        String rate = SharePreferenceUtil.getRate();
        if (!TextUtils.isEmpty(rate)) {
            tv_price.setText(shoppingBean.getGoodPrice());
        } else {
            double sum = Double.parseDouble(shoppingBean.getGoodCost()) + Double.parseDouble(shoppingBean.getGoodProfit()) * Double.parseDouble(rate);
            DecimalFormat df = new DecimalFormat("######0.00");
            tv_price.setText(df.format(sum) + "元");
        }
        net_numedit.setNumChangedListener(new NumChangedListener() {
            @Override
            public void numChanged(int num) {
                count = num;
                tv_count.setText(String.valueOf(num));
            }
        });

        btn_cancel.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                int num = net_numedit.getNum();
                if (listener != null){
                    listener.onNumChange(num+"");
                }
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;

        }
    }

    public NumChangeListener listener;

    public void setOnNumChangeListener(NumChangeListener listener){
        this.listener = listener;
    }

    public interface NumChangeListener{
        void onNumChange(String num);
    }
}
