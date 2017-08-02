package view.lcc.tyzs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.OrderInfo;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-02 21:14
 * Description:  |
 */
public class OrderConfirmAdapter extends BaseAdapter {
    private ArrayList<OrderInfo> bean;
    private Context context;

    public OrderConfirmAdapter(ArrayList<OrderInfo> bean,Context context){
        this.bean = bean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public OrderInfo getItem(int i) {
        return bean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.order_list_items, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.price_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText("名称:" + getItem(position).getName());
        holder.tv_price.setText("价格:" + getItem(position).getTrueprice() + "元  X " + getItem(position).getNumber() + "件");
        return convertView;
    }

    static class ViewHolder {
        TextView tv_name;
        TextView tv_price;
    }
}
