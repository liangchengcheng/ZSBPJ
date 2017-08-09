package view.lcc.tyzs.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.bean.OrderDetails;
import view.lcc.tyzs.frame.ImageManager;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-09 16:33
 * Description:  |
 */
public class OrderDetailsAdapter extends BaseAdapter {

    private String Rate;

    static class ViewHolder {
        ImageView iv_page;
        TextView tv_name;
        TextView tv_prince;
        TextView tv_num;
        TextView tv_xiajia;
        TextView miaoshu;
    }

    private Context context;
    private List<OrderDetails> beans;

    public OrderDetailsAdapter(Context context) {
        super();
        this.context = context;
        beans = new ArrayList<OrderDetails>();
        Rate = SharePreferenceUtil.getRate();
    }

    public void updateAdapter(ArrayList<OrderDetails> beana) {
        this.beans = beana;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public OrderDetails getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.order_details_item, null);
            holder = new ViewHolder();
            holder.iv_page = (ImageView) convertView.findViewById(R.id.iv_page);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_prince = (TextView) convertView.findViewById(R.id.tv_prince);
            holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            holder.tv_xiajia = (TextView) convertView.findViewById(R.id.tv_xiajia);
            holder.miaoshu = (TextView) convertView.findViewById(R.id.miaoshu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(getItem(position).getGName());
        holder.tv_xiajia.setText("下架:" + getItem(position).getGIsDelete());
        holder.miaoshu.setText("描述:" + getItem(position).getGDescription());
        holder.tv_num.setText(getItem(position).getGnumber() + "件");
        if (TextUtils.isEmpty(Rate)) {
            holder.tv_prince.setText("请先登录");
        } else {
            double sum = Double.parseDouble(getItem(position).getCost())
                    + Double.parseDouble(getItem(position).getProfit()) * Double.parseDouble(Rate);
            DecimalFormat df = new DecimalFormat("######0.00");
            holder.tv_prince.setText("￥" + df.format(sum));
        }
        ImageManager.getInstance().loadUrlImage(context, AppConstants.PIC_URL + "/" + getItem(position).getGImageURL(), holder.iv_page);
        return convertView;
    }

    // 向list里面添加新的数据
    public void addDate(List<OrderDetails> bean) {
        beans.addAll(bean);
    }

    // 这个是同步数据用的
    public void newData(ArrayList<OrderDetails> bean) {
        this.beans = bean;
    }
}
