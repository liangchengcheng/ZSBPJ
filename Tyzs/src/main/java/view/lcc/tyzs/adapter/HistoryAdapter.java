package view.lcc.tyzs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.UserInfo;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2017年05月13日16:43:51
 * Description:
 */
public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserInfo> mList = new ArrayList<>();

    public void bind(List<UserInfo> messages) {
        this.mList = messages;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final UserInfo weekData = mList.get(position);
        NormalViewHolder holder = (NormalViewHolder) viewHolder;
        holder.tv_title.setText("测试标签："+weekData.getNickname());
        holder.tv_content.setText("测试时间："+weekData.getCtime());

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
        LinearLayout ll_all;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            ll_all = (LinearLayout) itemView.findViewById(R.id.ll_all);
        }
    }

    public void appendToList(List<UserInfo> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<UserInfo> getList() {
        return mList;
    }

    public interface OnItemClickListener {
        void onItemClick(UserInfo data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }

}
