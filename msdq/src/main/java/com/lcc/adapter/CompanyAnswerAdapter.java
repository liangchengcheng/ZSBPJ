package com.lcc.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcc.entity.Answer;
import com.lcc.entity.CompanyAnswer;
import com.lcc.entity.CompanyTest;
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
public class CompanyAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_ITEM = 0;
    private static final int HEAD_ITEM = 1;
    public static final int FOOTER_ITEM = 2;

    private List<Object> mList = new ArrayList<>();

    private boolean hasFooter;
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
            return new NormalViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_answer_item, parent, false));
        } else if (viewType == FOOTER_ITEM) {
            return new FootViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_foot_loading, parent, false));
        } else {
            return new HeadViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.company_answer_header, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeadViewHolder) {
            CompanyTest object = (CompanyTest) mList.get(0);
            HeadViewHolder holder = (HeadViewHolder) viewHolder;
            holder.tv_name.setMaxLineCount(3);
            holder.tv_name.setContent(object.getSummary());
            holder.tv_name.setContentTextColor(Color.parseColor("#6D6D6D"));
            holder.tv_title.setText(object.getTitle());

            String image_url=object.getQuestion_image();
            if (!TextUtils.isEmpty(image_url)){
                holder.tv_phone.setVisibility(View.VISIBLE);
                ImageManager.getInstance().loadUrlImage(holder.tv_phone.getContext(),
                        image_url,holder.tv_phone);
            }else {
                holder.tv_phone.setVisibility(View.GONE);
            }
            String head_url=object.getUser_image();
            ImageManager.getInstance().loadCircleImage(holder.iv_head.getContext(),
                    head_url,holder.iv_head);
            holder.tv_nickname.setText(object.getNickname());
            holder.tv_month.setText(object.getCreated_time());

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
            final CompanyAnswer answer = (CompanyAnswer) object;
            NormalViewHolder holder = (NormalViewHolder) viewHolder;
            holder.des_content.setText(Html.fromHtml(answer.getAnswer_content()));
            holder.tv_name.setText(answer.getNickname());
            ImageManager.getInstance().loadCircleImage(holder.iv_image.getContext(),
                    answer.getUser_image(), holder.iv_image);

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

        @Bind(R.id.tv_phone)
        ImageView tv_phone;

        @Bind(R.id.iv_head)
        ImageView iv_head;

        @Bind(R.id.tv_nickname)
        TextView tv_nickname;

        @Bind(R.id.tv_month)
        TextView tv_month;

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

    public void appendToList(List<CompanyAnswer> list) {
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
        void onItemClick(CompanyAnswer data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }
}
