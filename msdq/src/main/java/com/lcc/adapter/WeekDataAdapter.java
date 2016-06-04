package com.lcc.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcc.entity.Answer;
import com.lcc.msdq.R;
import com.lcc.view.StretchyTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  AnswerIndexAdapter
 */
public class WeekDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_ITEM = 0;
    private static final int HEAD_ITEM = 1;
    public static final int FOOTER_ITEM = 2;

    private List<Object> mList = new ArrayList<>();

    /**
     * 是否设置了footer
     */
    private boolean hasFooter;

    /**
     * 是否继续加载数据
     */
    private boolean hasMoreData = true;

    public void bind(List<Object> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD_ITEM;
        } else if (position == getBasicItemCount() && hasFooter) {
            return FOOTER_ITEM;
        } else {
            return NORMAL_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_ITEM) {
            return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_answer_item, parent, false));
        } else if (viewType == FOOTER_ITEM) {
            return new FootViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foot_loading, parent, false));
        } else {
            return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_answer_header, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeadViewHolder) {
            Object object = mList.get(position);
            HeadViewHolder holder = (HeadViewHolder) viewHolder;
            holder.tv_name.setMaxLineCount(3);
            holder.tv_name.setContent("近些年来，越来越多的行业开始和互联网结合，诞生了越来越多的互联网创业公司。" +
                "互联网创业公司需要面对许多的不确定因素。如果你和你的小伙伴们够幸运，你们的公司可能会在几个星期之内让用户数、商品数" +
                "、订单量增长几十倍上百倍。一次促销可能会带来平时几十倍的访问流量，" +
                "一次秒杀活动可能会吸引平时数百倍的访问用户。这对公司自然是极大的好事，说明产品得到认可，公司未来前景美妙。");

            holder.tv_title.setText("你觉得你的人生的意义是什么");

        } else if (viewHolder instanceof FootViewHolder) {
            if (hasMoreData) {
                ((FootViewHolder) viewHolder).mProgressView.setVisibility(View.VISIBLE);
                ((FootViewHolder) viewHolder).mTextView.setText("正在加载...");
            } else {
                ((FootViewHolder) viewHolder).mProgressView.setVisibility(View.GONE);
                ((FootViewHolder) viewHolder).mTextView.setText("没有更多数据...");
            }
        } else {
            Object object = mList.get(position);
            final Answer answer = (Answer) object;
            NormalViewHolder holder = (NormalViewHolder) viewHolder;
            holder.des_content.setText(answer.getAnswer());
            holder.tv_name.setText(answer.getUserinfo().getNickname());
            ImageManager.getInstance().loadCircleImage(holder.iv_image.getContext(),
                    answer.getUserinfo().getUser_image(), holder.iv_image);

            if(mListener != null) {
                holder.ll_all.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(answer);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + (hasFooter ? 1 : 0);

    }

    public int getBasicItemCount() {
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

        @Bind(R.id.ll_all)
        CardView ll_all;

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

    /**
     * 头部的布局
     */
    class FootViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.mProgressView)
        ProgressBar mProgressView;

        @Bind(R.id.mTextView)
        TextView mTextView;

        public FootViewHolder(View itemView) {
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

    public List<Object> getList() {
        return mList;
    }

    public void setHasFooter(boolean hasFooter) {
        if (this.hasFooter != hasFooter) {
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    public boolean hasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean isMoreData) {
        if (this.hasMoreData != isMoreData) {
            this.hasMoreData = isMoreData;
            notifyDataSetChanged();
        }
    }

    public void setHasMoreDataAndFooter(boolean hasMoreData, boolean hasFooter) {
        if (this.hasMoreData != hasMoreData || this.hasFooter != hasFooter) {
            this.hasMoreData = hasMoreData;
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Answer data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }
}
