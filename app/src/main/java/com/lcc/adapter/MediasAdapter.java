package com.lcc.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lcc.activity.R;
import com.lcc.entity.MediaEntity;
import com.lcc.view.ShadowImageView;
import com.lcc.view.TextImageView;

import zsbpj.lccpj.utils.AppUtils;
import zsbpj.lccpj.view.recyclerview.adapter.LoadMoreRecyclerAdapter;

public class MediasAdapter extends LoadMoreRecyclerAdapter<MediaEntity,MediasAdapter.ViewHolder>{

    private  OnItemClickListener onItemClickListener;
    private Activity mActivity;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public MediasAdapter(Activity activity){
        this.mActivity=activity;
    }

    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, final int position) {
        MediaEntity entity=getItem(position);
        holder.mImageViewCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(getItem(position));
            }
        });
        holder.mTextViewLikesCount.setTextImageStart(18, R.mipmap.ic_thumb_up_gray_18dp, " " + entity.getLikes_count());
        holder.mTextViewRecommendCaption.setText(entity.getCaption());
        Glide.with(mActivity)
                .load(entity.getCover_pic())
                .placeholder(R.mipmap.bg_video_default)
                .into(holder.mImageViewCover);
        //获取用户的头像
        //AppUtils.loadSmallUserAvata(mActivity,getItem(position).getUser(), holder.mImageViewAvatar);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mImageViewAvatar;
        public final ShadowImageView mImageViewCover;
        public final TextImageView mTextViewLikesCount;
        public final TextView mTextViewRecommendCaption;

        public ViewHolder(View view) {
            super(view);
            mImageViewAvatar = (ImageView) view.findViewById(R.id.imageView_avatar);
            mImageViewCover = (ShadowImageView) view.findViewById(R.id.imageview_cover_pic);
            mTextViewLikesCount = (TextImageView) view.findViewById(R.id.textView_likes_count);
            mTextViewRecommendCaption = (TextView) view.findViewById(R.id.textView_recommend_caption);
        }
    }

    /**
     * 设置条目的点击事件
     */
    public interface OnItemClickListener{
        void OnItemClick(MediaEntity entity);
    }
}
