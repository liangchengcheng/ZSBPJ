package com.lcc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcc.activity.R;
import com.lcc.activity.data.Test;


public class FavoriteTestListAdapter  extends MyBaseAdapter<Test> {

    public FavoriteTestListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Test test = mDatas.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_fav_item, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_test_question.setText(test.getQuestion());
        holder.tv_create_time.setText("创建的时间");

        return convertView;
    }

    private class ViewHolder{
        TextView tv_test_question,tv_create_time;

        public ViewHolder(View view) {
            tv_test_question = (TextView) view.findViewById(R.id.tv_test_question);
            tv_create_time = (TextView) view.findViewById(R.id.tv_create_time);
        }
    }
}
