package view.lcc.tyzs.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.ShoppingBean;


@SuppressLint("InflateParams")
public class RightAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ShoppingBean> list;

    public RightAdapter(Context context, List<ShoppingBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = layoutInflater.inflate(R.layout.vocation_items, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == selectItem) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.textView.setText(list.get(position).getGoodName());
        viewHolder.textView.setTextColor(Color.BLACK);
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

    private int selectItem = -1;

    public void setSelectItem(int p) {
        selectItem = p;
    }
}
