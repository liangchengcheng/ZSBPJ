package view.lcc.tyzs.adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.bean.ShoppingCarBean;
import view.lcc.tyzs.frame.ImageManager;
import view.lcc.tyzs.utils.SharePreferenceUtil;
import view.lcc.tyzs.view.CartProductItemChangedListener;
import view.lcc.tyzs.view.NumChangedListener;
import view.lcc.tyzs.view.NumEditText;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-05 15:17
 * Description:  |
 */
public class CarAdapter extends BaseAdapter {
    private CartProductItemChangedListener itemChangedListener;
    private String Rate;

    private Context context;
    public List<ShoppingCarBean> beans;

    public CarAdapter(Context context) {
        super();
        this.context = context;
        beans = new ArrayList<ShoppingCarBean>();
        Rate = SharePreferenceUtil.getRate();
    }

    public void updateAdapter(ArrayList<ShoppingCarBean> beana) {
        this.beans = beana;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public ShoppingCarBean getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.car_item, null);
        ImageView ivPreview = (ImageView) convertView.findViewById(R.id.iv_page);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_price = (TextView) convertView.findViewById(R.id.tv_price_vip);
        CheckBox cb_cart_all_check = (CheckBox) convertView.findViewById(R.id.cb_cart_all_check);
        NumEditText net_cart_count = (NumEditText) convertView.findViewById(R.id.net_cart_count);

        tv_name.setText("名称:" + getItem(position).getName());
        if (TextUtils.isEmpty(Rate)) {
            tv_price.setText("￥"+getItem(position).getPrice() );

        } else {
            double sums = Double.parseDouble(getItem(position).getCost()) + Double.parseDouble(getItem(position).getProfit()) * Double.parseDouble(Rate);
            DecimalFormat df = new DecimalFormat("######0.00");
            tv_price.setText("￥"+df.format(sums));

        }

        net_cart_count.setNum(Integer.parseInt(getItem(position).getNumber()));

        ImageManager.getInstance().loadUrlImage(context, AppConstants.PIC_URL+"/"+getItem(position).getImageUrl(),ivPreview);

        cb_cart_all_check.setChecked(getItem(position).isCheck());
        cb_cart_all_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {
                beans.get(position).setCheck(isCheck);
                if (itemChangedListener != null) {
                    itemChangedListener.itemCheckChanged(position, isCheck);
                }
                notifyDataSetChanged();
            }
        });

        //单个数字点击时候发生的变化
        net_cart_count.setNumChangedListener(new NumChangedListener() {
            @Override
            public void numChanged(int num) {
                beans.get(position).setNumber(num + "");
                if (itemChangedListener != null) {
                    itemChangedListener.itemNumChanged(position, num);
                }
                notifyDataSetChanged();
            }
        });

        net_cart_count.setNumEditClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //  showDialog(position);
            }
        });
        return convertView;
    }

    public void setCartProductItemChangedListener(CartProductItemChangedListener itemChangedListener) {
        this.itemChangedListener = itemChangedListener;
    }

    // 向list里面添加新的数据
    public void addDate(List<ShoppingCarBean> bean) {
        beans.addAll(bean);
        notifyDataSetChanged();
    }

    // 这个是同步数据用的
    public void newData(List<ShoppingCarBean> bean) {
        this.beans = bean;
        notifyDataSetChanged();
    }


}

