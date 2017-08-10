package view.lcc.tyzs.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private TextView tv_price;
    private TextView name;


    private NumEditText net_numedit;

    private String count;
    private ShoppingBean shoppingBean;
    private Context context;


    public GoodsToCarDialog(Context context, String count, ShoppingBean shoppingBean) {
        super(context, R.style.Dialog2);
        this.count = count;
        this.shoppingBean = shoppingBean;
        this.context = context;
    }

    public GoodsToCarDialog(Context context, int themeResId) {
        super(context, R.style.Dialog2);
    }

    protected GoodsToCarDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_cart_num);

        tv_price = (TextView) findViewById(R.id.tv_dialog_cart_price);
        name = (TextView) findViewById(R.id.name);
        net_numedit = (NumEditText) findViewById(R.id.net_dialog_count);

        setCanceledOnTouchOutside(false);


        net_numedit.setNum(Integer.parseInt(count));
        name.setText(shoppingBean.getGoodName());

        String rate = SharePreferenceUtil.getRate();
        if (TextUtils.isEmpty(rate)) {
            tv_price.setText(shoppingBean.getGoodPrice());
        } else {
            double sum = Double.parseDouble(shoppingBean.getGoodCost()) + Double.parseDouble(shoppingBean.getGoodProfit()) * Double.parseDouble(rate);
            DecimalFormat df = new DecimalFormat("######0.00");
            tv_price.setText(df.format(sum) + "元");
        }
        net_numedit.setNumChangedListener(new NumChangedListener() {
            @Override
            public void numChanged(int num) {
                count = num + "";
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        Window window = getWindow();
        window.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                int num = net_numedit.getNum();
                if (listener != null) {
                    listener.onNumChange(num + "");
                }
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;

        }
    }

    public NumChangeListener listener;

    public void setOnNumChangeListener(NumChangeListener listener) {
        this.listener = listener;
    }

    public interface NumChangeListener {
        void onNumChange(String num);
    }
}
