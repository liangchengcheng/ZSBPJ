package com.lcc.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lcc.entity.Answer;
import com.lcc.msdq.R;
import com.lcc.view.StretchyTextView;

import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
public class AnswerIndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_ITEM = 0;
    private static final int HEAD_ITEM = 1;

    private List<Answer> mList = new ArrayList<>();

    public void bind(List<Answer> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD_ITEM;
        return NORMAL_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType== NORMAL_ITEM) {
            return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_answer_item, parent, false));        } else {
        }
        return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_answer_header, parent, false));
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Answer answer = mList.get(position);

        if (viewHolder instanceof HeadViewHolder) {
            HeadViewHolder holder = (HeadViewHolder) viewHolder;
            holder.tv_title.setText(answer.getAnswer());
            holder.tv_name.setContent(answer.getUserinfo().getNickname());

        } else {
            NormalViewHolder holder = (NormalViewHolder) viewHolder;
            holder.des_content.setText(answer.getAnswer());
            holder.tv_name.setText(answer.getUserinfo().getNickname());
            ImageManager.getInstance().loadCircleImage(holder.iv_image.getContext(),
                    answer.getUserinfo().getUser_image(),holder.iv_image);

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 正常的布局
     */
    class NormalViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.des_content)
        TextView des_content;

        @Bind(R.id.tv_name)
        TextView tv_name;

        @Bind(R.id.iv_image)
        ImageView iv_image;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 头部的布局
     */
    class HeadViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView tv_title;

        @Bind(R.id.spread_textview)
        StretchyTextView tv_name;

        @Bind(R.id.tv_llrs)
        TextView tv_llrs;

        public HeadViewHolder(View itemView) {
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
