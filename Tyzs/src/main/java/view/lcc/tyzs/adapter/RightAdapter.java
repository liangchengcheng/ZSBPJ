package view.lcc.tyzs.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.base.AppConstants;
import view.lcc.tyzs.bean.ShoppingBean;
import view.lcc.tyzs.utils.SharePreferenceUtil;


@SuppressLint("InflateParams")
public class RightAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ShoppingBean> list;
    private String rate;

    public RightAdapter(Context context, List<ShoppingBean> list, String rate) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rate = rate;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.right_items, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tv_price_vip = (TextView) convertView.findViewById(R.id.tv_price_vip);
            viewHolder.iv_content_image = (ImageView) convertView.findViewById(R.id.iv_content_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == selectItem) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.tv_name.setText(list.get(position).getGoodName());
        viewHolder.tv_price.setText("零售价:" + list.get(position).getGoodPrice() + "元");
        viewHolder.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        Glide.with(context)
                .load(AppConstants.PIC_URL + list.get(position).getGoodImgUrl()).centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(viewHolder.iv_content_image);
        if (TextUtils.isEmpty(rate)) {
            viewHolder.tv_price_vip.setText("代理价:请先登入");
        } else {
            double sum = Double.parseDouble(list.get(position).getGoodCost())
                    + Double.parseDouble(list.get(position).getGoodProfit())
                    * Double.parseDouble(rate);

            DecimalFormat df = new DecimalFormat("######0.00");
            viewHolder.tv_price_vip.setText("代理价:" + df.format(sum) + "元");
        }
        return convertView;
    }

    public static class ViewHolder {
        TextView tv_name;
        TextView tv_price;
        TextView tv_price_vip;
        ImageView iv_content_image;
    }

    public void updateRate() {
        rate = SharePreferenceUtil.getRate();
    }

    public void setData(List<ShoppingBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private int selectItem = -1;

    public void setSelectItem(int p) {
        selectItem = p;
    }
}
