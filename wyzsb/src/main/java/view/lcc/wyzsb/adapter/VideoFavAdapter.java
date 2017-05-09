package view.lcc.wyzsb.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.bean.History;
import view.lcc.wyzsb.bean.News;
import view.lcc.wyzsb.bean.Videofav;
import view.lcc.wyzsb.view.home.GildeImageView.GlideImageView;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:
 * Description:
 */
public class VideoFavAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_ITEM = 0;
    public static final int FOOTER_ITEM = 2;
    private List<Videofav> mList = new ArrayList<>();
    private boolean hasFooter;
    private boolean hasMoreData = true;

    public void bind(List<Videofav> messages) {
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
                    .inflate(R.layout.item_travel, parent, false));
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
            final Videofav entity = mList.get(position);
            NormalViewHolder holder = (NormalViewHolder) viewHolder;
            final String title = entity.getV_t() ;
            holder.tvTitle.setText(title);
            holder.tvRank.setText(  entity.getV_js());

            String images = entity.getV_img();
            if (!TextUtils.isEmpty(images)){
                holder.givImage.loadNetImage(images, R.color.font_black_6);
            }

            if (mListener != null) {
                holder.llRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(entity);
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
        LinearLayout llRootView;
        GlideImageView givImage;
        TextView tvTitle;
        TextView tvRank;

        public NormalViewHolder(View view) {
            super(view);
            llRootView = (LinearLayout) view.findViewById(R.id.ll_root_view);
            givImage = (GlideImageView) view.findViewById(R.id.giv_image);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvRank = (TextView) view.findViewById(R.id.tv_rank);
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

    public void appendToList(List<Videofav> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<Videofav> getList() {
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
        void onItemClick(Videofav data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }

    public interface OnFavClickListener {
        void onOnFavClick(News data);
    }

    private OnFavClickListener favListener;

    public void setOnFavClickListener(OnFavClickListener li) {
        this.favListener = li;
    }


}
