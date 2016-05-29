package com.lcc.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcc.msdq.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AnswerIndexAdapter
 */
public class AnswerIndexAdapter extends RecyclerView.Adapter<AnswerIndexAdapter.ViewHolder> {

    private List<String> messages = new ArrayList<>();

    public void bind(List<String> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_answer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String message = messages.get(position);
        holder.text.setText(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text)
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
