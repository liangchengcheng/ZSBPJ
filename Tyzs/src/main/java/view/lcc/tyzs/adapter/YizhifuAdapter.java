package view.lcc.tyzs.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import view.lcc.tyzs.R;
import view.lcc.tyzs.bean.OrderInfoData;
import view.lcc.tyzs.frame.Frame;

/**
 * Author:       梁铖城
 * Email:        1038127753@qq.com
 * Date:         2015年11月21日15:28:25
 * Description:  IndexMenuAdapter
 */
public class YizhifuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_ITEM = 0;
    public static final int FOOTER_ITEM = 2;
    private List<OrderInfoData> mList = new ArrayList<>();
    private boolean hasFooter;
    private boolean hasMoreData = true;

    public void bind(List<OrderInfoData> messages) {
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
            return new NormalViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.yizhifu_item, parent, false));
        } else {
            return new FootViewHolder(LayoutInflater.from(parent.getContext())
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
            final OrderInfoData weekData = mList.get(position);
            final NormalViewHolder holder = (NormalViewHolder) viewHolder;
            holder.tv_id.setText("编号:" + weekData.getOID().toString().trim());
            holder.tv_shijian.setText("创建时间:" + weekData.getCreattime());
            holder.tv_name.setText("订单总额" + weekData.getTotal() + "元");
            holder.tv_price.setText("是否支付:" + weekData.getIspay());
            // getItem(position).getTotal()
            holder.btn_tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String liYou = holder.ed_reson.getText().toString();
                    if (!TextUtils.isEmpty(liYou) && liYou.length() > 5) {
                        if (listener != null){
                            listener.OnTuihuoClick(weekData,liYou);
                        }
                    }else {
                        Frame.getInstance().toastPrompt("退款原因大于5个字");
                    }
                }
            });

            holder.ll_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(weekData);
                    }
                }
            });
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
        TextView tv_name;
        TextView tv_price;
        TextView tv_shijian;
        TextView tv_id;
        TextView btn_tr;
        EditText ed_reson;
        View ll_content;

        public NormalViewHolder(View convertView) {
            super(convertView);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_price = (TextView) convertView.findViewById(R.id.tv_prince);
            tv_shijian = (TextView) convertView.findViewById(R.id.tv_time);
            tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            btn_tr = (TextView) convertView.findViewById(R.id.btn_tr);
            ed_reson = (EditText) convertView.findViewById(R.id.et_yuanyin);
            ll_content = convertView.findViewById(R.id.ll_content);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressView;
        TextView mTextView;

        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressView = (ProgressBar) itemView.findViewById(R.id.mProgressView);
            mTextView = (TextView) itemView.findViewById(R.id.mTextView);
        }
    }

    public void appendToList(List<OrderInfoData> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    public List<OrderInfoData> getList() {
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
        void onItemClick(OrderInfoData data);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        this.mListener = li;
    }

    public interface OnTuihuoClickListener {
        void OnTuihuoClick(OrderInfoData data,String liYou);
    }

    private OnTuihuoClickListener listener;

    public void setOnTuihuoClickListener(OnTuihuoClickListener li) {
        this.listener = li;
    }


}
