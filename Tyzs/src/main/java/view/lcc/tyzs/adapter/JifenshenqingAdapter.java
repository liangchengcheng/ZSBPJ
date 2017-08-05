package view.lcc.tyzs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.Note;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-05 10:57
 * Description:  |
 */
public class JifenshenqingAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Note> beans;

    public JifenshenqingAdapter(Context context) {
        super();
        this.context = context;
        beans = new ArrayList<Note>();
    }

    public void updateAdapter(ArrayList<Note> beana) {
        this.beans = beana;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Note getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.change_list_item, null);
            holder = new ViewHolder();
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_jine = (TextView) convertView.findViewById(R.id.tv_prince);
            holder.tv_region = (TextView) convertView.findViewById(R.id.tv_region);
            holder.tv_orderid = (TextView) convertView.findViewById(R.id.tv_orderid);
            holder.tv_balance = (TextView) convertView.findViewById(R.id.tv_balance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_time.setText("申请类型：" + getItem(position).getType());
        holder.tv_id.setText("申请状态：" + getItem(position).getState());
        holder.tv_jine.setText("变动积分：" + getItem(position).getChangeValue() + "");
        holder.tv_region.setText("申请时间：" + getItem(position).getTime());
        holder.tv_balance.setText("系统余额：" + getItem(position).getBalance());
        holder.tv_orderid.setText("申请单号：" + getItem(position).getSpno());
        return convertView;
    }

    // 向list里面添加新的数据
    public void addDate(ArrayList<Note> bean) {
        beans.addAll(bean);
    }

    // 这个是同步数据用的
    public void newdata(ArrayList<Note> bean) {
        this.beans = bean;
    }


    static class ViewHolder {
        TextView tv_time;
        TextView tv_id;
        TextView tv_jine;
        TextView tv_region;
        TextView tv_orderid;
        TextView tv_balance;
    }
}
