package com.lcc.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcc.entity.Answer;
import com.lcc.msdq.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AnswerIndexAdapter
 */
public class AnswerIndexAdapter extends RecyclerView.Adapter<AnswerIndexAdapter.ViewHolder> {

    private List<Answer> mList = new ArrayList<>();

    public void bind(List<Answer> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_answer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Answer answer = mList.get(position);
        holder.tv_content.setText(answer.getAnswer());
        holder.tv_name.setText(answer.getUserinfo().getNickname());
        ImageManager.getInstance().loadCircleImage(holder.iv_image.getContext(),
                answer.getUserinfo().getUser_image(),holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_content)
        TextView tv_content;

        @Bind(R.id.tv_name)
        TextView tv_name;

        @Bind(R.id.iv_image)
        ImageView iv_image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void appendToList(List<Answer> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<Answer> getList() {
        return mList;
    }

}
