package com.lcc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lcc.msdq.R;

import java.util.List;

@SuppressLint("InflateParams") public class ChoiceAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	private List<String> list;

	public ChoiceAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			convertView = layoutInflater.inflate(R.layout.choice_item, null);
			viewHolder = new ViewHolder();
			viewHolder.btn_name = (Button) convertView
					.findViewById(R.id.btn_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (position == selectItem) {
			convertView.setBackgroundColor(Color.RED);
		}
		else {
			convertView.setBackgroundColor(Color.BLUE);
		}
		viewHolder.btn_name.setText(list.get(position));
		viewHolder.btn_name.setTextColor(Color.BLACK);
		return convertView;
	}

	public static class ViewHolder {
		public Button btn_name;
	}
	private int  selectItem=-1;
	public void setSelectItem(int p)
	{
		selectItem=p;
	}
}
