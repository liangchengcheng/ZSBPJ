package view.lcc.wyzsb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.bean.Book;
import view.lcc.wyzsb.bean.News;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_ITEM = 0;
    public static final int FOOTER_ITEM = 2;
    private List<Book> mList = new ArrayList<>();
    private boolean hasFooter;
    private boolean hasMoreData = true;

    public void bind(List<Book> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getBasicItemCount() && hasFooter) {
            return FOOTER_ITEM;
        } else {
            return NORMAL_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_ITEM) {
            return new  NormalViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_item, parent, false));
        } else {
            return new  FootViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_foot_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof FootViewHolder) {
            if (hasMoreData) {
                ((FootViewHolder) viewHolder).mProgressView.setVisibility(View.VISIBLE);
                ((FootViewHolder) viewHolder).mTextView.setText("正在加载...");
            }
        } else {
            final Book weekData = mList.get(position);
            NormalViewHolder holder = (NormalViewHolder) viewHolder;
            holder.tv_title.setText(weekData.getB_t());
            holder.tv_rank.setText(""+weekData.getB_js());
            Glide.with(holder.giv_image.getContext())
                    .load(weekData.getB_i())
                    .placeholder(R.color.article_des)
                    .error(R.color.article_title)
                    .into(holder.giv_image);
            if (mListener != null) {
                holder.ll_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(weekData);
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
        TextView tv_title;
        TextView tv_rank;
        ImageView giv_image;
        LinearLayout ll_all;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_rank = (TextView) itemView.findViewById(R.id.tv_rank);
            ll_all = (LinearLayout) itemView.findViewById(R.id.ll_all);
            giv_image = (ImageView) itemView.findViewById(R.id.giv_image);
        }
    }

    /**
     * 头部的布局
     */
    class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressView;
        TextView mTextView;

        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressView = (ProgressBar) itemView.findViewById(R.id.mProgressView);
            mTextView = (TextView) itemView.findViewById(R.id.mTextView);
        }
    }

    public void appendToList(List<Book> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<Book> getList() {
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
        void onItemClick(Book data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }

    public interface OnFavClickListener {
        void onOnFavClick(News data);
    }

    private ArticleAdapter.OnFavClickListener favListener;

    public void setOnFavClickListener(ArticleAdapter.OnFavClickListener li) {
        this.favListener = li;
    }


}
