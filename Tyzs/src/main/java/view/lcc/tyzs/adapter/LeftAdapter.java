package view.lcc.tyzs.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import view.lcc.tyzs.R;

@SuppressLint({"ResourceAsColor", "InflateParams"})
public class LeftAdapter extends BaseAdapter {
    private List<String> l;
    private Context context;
    private int selectItem = -1;

    public LeftAdapter(Context context, List<String> l) {
        this.context = context;
        this.l = l;
    }

    @Override
    public int getCount() {
        return l.size();
    }

    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.vocation_items, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.name.setText(l.get(position));
        if (position == selectItem) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.m29c741));
        }
        return convertView;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    class Holder {
        TextView name;
    }

}
