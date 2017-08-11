package view.lcc.tyzs.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.News;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年05月13日16:43:51
 * Description:
 */
public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News> mList = new ArrayList<>();

    public void bind(List<News> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        final News weekData = mList.get(position);
        NormalViewHolder holder = (NormalViewHolder) viewHolder;
        holder.tv_title.setText(weekData.getTitle());
        holder.tv_content.setText(weekData.getDes());
        Glide.with(holder.giv_image.getContext())
                .load(weekData.getRid())
                .placeholder(R.color.bg_textmy)
                .error(R.color.bg_textmy)
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

    @Override
    public int getItemCount() {
        return getBasicItemCount();
    }

    public int getBasicItemCount() {
        return mList.size();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        ImageView giv_image;
        CardView ll_all;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            ll_all = (CardView) itemView.findViewById(R.id.ll_all);
            giv_image = (ImageView) itemView.findViewById(R.id.tv_comment_count);
        }
    }

    public void appendToList(List<News> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<News> getList() {
        return mList;
    }

    public interface OnItemClickListener {
        void onItemClick(News data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }

}
