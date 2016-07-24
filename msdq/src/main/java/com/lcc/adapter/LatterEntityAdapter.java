package com.lcc.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcc.db.test.UserInfo;
import com.lcc.entity.LatterEntity;
import com.lcc.entity.Letter;
import com.lcc.msdq.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import zsbpj.lccpj.frame.ImageManager;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  FavAdapter(收藏的适配器)
 */
public class LatterEntityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LatterEntity> mList = new ArrayList<>();
    private UserInfo userInfo = new UserInfo();
    private Letter letter = new Letter();

    public LatterEntityAdapter(UserInfo userInfo, Letter letter) {
        this.userInfo = userInfo;
        this.letter = letter;
    }

    public void bind(List<LatterEntity> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_one, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        final LatterEntity latterEntity = mList.get(position);
        NormalViewHolder holder = (NormalViewHolder) viewHolder;
        holder.ll_you.setVisibility(View.VISIBLE);
        holder.rl_me.setVisibility(View.VISIBLE);
        String message_body = latterEntity.getMessage_body();
        if (latterEntity.getFrom_w().equals(letter.getFrom_w())){
            String URL = letter.getUser_image();
            if (!TextUtils.isEmpty(URL)) {
                ImageManager.getInstance().loadCircleImage(holder.iv_you.getContext(),
                        URL, holder.iv_you);
            }
            holder.tv_you.setText(message_body);
            holder.rl_me.setVisibility(View.GONE);
        }else {
            String URL = letter.getUser_image();
            if (!TextUtils.isEmpty(URL)) {
                ImageManager.getInstance().loadCircleImage(holder.iv_me.getContext(),
                        URL, holder.iv_me);
            }
            holder.tv_me.setText(message_body);
            holder.ll_you.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return getBasicItemCount();
    }

    public int getBasicItemCount() {
        return mList.size();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_you)
        TextView tv_you;
        @Bind(R.id.tv_me)
        TextView tv_me;
        @Bind(R.id.iv_you)
        ImageView iv_you;
        @Bind(R.id.iv_me)
        ImageView iv_me;

        @Bind(R.id.ll_you)
        LinearLayout ll_you;
        @Bind(R.id.rl_me)
        RelativeLayout rl_me;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void appendToList(List<LatterEntity> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<LatterEntity> getList() {
        return mList;
    }

    public interface OnItemClickListener {
        void onItemClick(Letter data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }
}
