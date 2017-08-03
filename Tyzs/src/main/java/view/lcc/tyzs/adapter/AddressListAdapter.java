package view.lcc.tyzs.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.Address;
import view.lcc.tyzs.utils.SharePreferenceUtil;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |08-03 08:20
 * Description:  |
 */
public class AddressListAdapter extends BaseAdapter {
    private Context context;
    private List<Address> list = new ArrayList<Address>();

    public AddressListAdapter(Context context) {
        this.context = context;
    }

    public void setData( List<Address> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Address getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.address_item, null);
            holder = new ViewHolder();
            holder.selected = (ImageView) convertView.findViewById(R.id.iv_ad_selected);
            holder.name = (TextView) convertView.findViewById(R.id.tv_ad_linkman);
            holder.phone = (TextView) convertView.findViewById(R.id.tv_ad_phone);
            holder.address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.ad_layout = (FrameLayout) convertView.findViewById(R.id.fl_ad_select);
            holder.ad_edit = (FrameLayout) convertView.findViewById(R.id.fl_ad_edit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //是默认地址的话
        String isDefault = list.get(position).getIsdefault();
        if (isDefault.equals("是")) {
            holder.selected.setVisibility(View.VISIBLE);
            SharePreferenceUtil.setAddressId(list.get(position).getAID());
            SharePreferenceUtil.setAddressInfo(list.get(position).getAddress());
            SharePreferenceUtil.setAddressPerson(list.get(position).getAddressee());
            SharePreferenceUtil.setAddressPhone(list.get(position).getPhone());

        } else {
            holder.selected.setVisibility(View.GONE);
        }

        holder.ad_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editListener != null){
                    editListener.edit(list.get(position));
                }
            }
        });
        holder.name.setText(list.get(position).getAddressee());
        holder.phone.setText(list.get(position).getPhone());
        holder.address.setText(list.get(position).getAddress());
        return convertView;
    }

    static class ViewHolder {
        private ImageView selected;
        private TextView name;
        private TextView phone;
        private TextView address;
        private FrameLayout ad_layout;
        private FrameLayout ad_edit;
    }
    public EditListener editListener;

    public void setOnEditListener (EditListener editListener){
        this.editListener = editListener;
    }

    public interface EditListener{
        void edit(Address address);
    }
}
