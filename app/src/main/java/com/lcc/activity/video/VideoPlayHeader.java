package com.lcc.activity.video;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.lcc.activity.R;
import com.lcc.entity.MediaEntity;
import com.lcc.utils.DateUtils;
import com.lcc.view.TextImageView;
import com.lcc.view.video.VideoControllerView;

public class VideoPlayHeader {

    private ImageView mBgaBadgeImageView;
    private TextView mTextViewScreenName;
    private TextView mTextViewCreatedAt;
    private TextImageView mTextViewPlaysCount;
    private VideoControllerView mVideoControllerView;
    private TextView mTextViewCaption;
    private ImageView mImageViewCommunicationMessage;
    private TextView mTextViewCommunicationMessage;
    private ImageView mImageViewRepeat;
    private ImageView mImageViewThumbUp;
    private TextView mTextViewThumbUp;
    private TextView mTextViewRepeat;
    private Activity mActivity;

    public VideoPlayHeader(Activity activity, View view) {
        this.mActivity = activity;
        mBgaBadgeImageView = (ImageView) view.findViewById(R.id.imageView_avatar);
        mTextViewScreenName = (TextView) view.findViewById(R.id.textView_screen_name);
        mTextViewCreatedAt = (TextView) view.findViewById(R.id.textView_created_at);
        mTextViewPlaysCount = (TextImageView) view.findViewById(R.id.textView_plays_count);
        mVideoControllerView = (VideoControllerView) view.findViewById(R.id.videoControllerView);
        mTextViewCaption = (TextView) view.findViewById(R.id.textView_caption);
        mImageViewCommunicationMessage = (ImageView) view.findViewById(R.id.imageView_communication_message);
        mTextViewCommunicationMessage = (TextView) view.findViewById(R.id.textView_communication_message);
        mImageViewRepeat = (ImageView) view.findViewById(R.id.imageView_repeat);
        mImageViewThumbUp = (ImageView) view.findViewById(R.id.imageView_thumb_up);
        mTextViewThumbUp = (TextView) view.findViewById(R.id.textView_thumb_up);
        mTextViewRepeat = (TextView) view.findViewById(R.id.textView_repeat);
    }

    public void bindData(final MediaEntity mediaEntity) {
        // TODO: 16/3/6 加载图片
        //AppUtils.loadSmallUserAvata(mActivity, mediaEntity.getUser(), mBgaBadgeImageView);
        mTextViewScreenName.setText(mediaEntity.getUser().getScreen_name());
        mTextViewCreatedAt.setText(DateUtils.getDifference(mediaEntity.getCreated_at()));
        mTextViewPlaysCount.setTextImageStart(15, R.mipmap.ic_visibility_white_24dp, " " + mediaEntity.getPlays_count());
        mTextViewCaption.setText(mediaEntity.getCaption());
        mTextViewCommunicationMessage.setText(mediaEntity.getComments_count() + "");
        mTextViewThumbUp.setText(mediaEntity.getLikes_count() + "");
        mTextViewRepeat.setText(mediaEntity.getReposts_count() + "");
        mVideoControllerView.setVideoPath(mediaEntity.getVideo());
        mVideoControllerView.start();
        mImageViewThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.thumb_up_scale);
                if (mediaEntity.getLiked()) {
                    mediaEntity.setLiked(false);
                    mediaEntity.setLikes_count(mediaEntity.getLikes_count() - 1);
                    mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_white_18dp);
                    mImageViewThumbUp.startAnimation(animation);
                } else {
                    mediaEntity.setLiked(true);
                    mediaEntity.setLikes_count(mediaEntity.getLikes_count() + 1);
                    mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_blue_18dp);
                    mImageViewThumbUp.startAnimation(animation);
                }
            }
        });
        if (mediaEntity.getLiked()) {
            mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_blue_18dp);
        } else {
            mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_white_18dp);
        }
    }
    public VideoControllerView getVideoControllerView() {
        return mVideoControllerView;
    }

}
