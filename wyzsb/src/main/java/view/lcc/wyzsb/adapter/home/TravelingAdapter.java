package view.lcc.wyzsb.adapter.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.bean.Article;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.bean.model.TravelingEntity;
import view.lcc.wyzsb.utils.ToastUtil;
import view.lcc.wyzsb.view.home.GildeImageView.GlideImageView;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class TravelingAdapter extends BaseListAdapter<Article> {
    private boolean isNoData;
    private int mHeight;
    // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_SCREEN_COUNT = 8;
    // 一次请求的个数
    public static final int ONE_REQUEST_COUNT = 10;

    public TravelingAdapter(Context context) {
        super(context);
    }

    public TravelingAdapter(Context context, List<Article> list) {
        super(context, list);
    }

    // 设置数据
    public void setData(List<Article> list) {
        clearAll();
        addALL(list);

        isNoData = false;
        if (list.size() == 1 && list.get(0).isNoData()) {
            // 暂无数据布局
            isNoData = list.get(0).isNoData();
            mHeight = list.get(0).getHeight();
        } else {
            // 添加空数据
            if (list.size() < ONE_SCREEN_COUNT) {
                addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
            }
        }
        notifyDataSetChanged();
    }

    // 创建不满一屏的空数据
    public List<Article> createEmptyList(int size) {
        List<Article> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i=0; i<size; i++) {
            emptyList.add(new Article());
        }
        return emptyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 暂无数据
        if (isNoData) {
            convertView = mInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            RelativeLayout rootView = (RelativeLayout) convertView .findViewById(R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }

        // 正常数据
        final ViewHolder holder;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_travel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        final Article entity = getItem(position);

        holder.llRootView.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(entity.getId())) {
            holder.llRootView.setVisibility(View.INVISIBLE);
            return convertView;
        }

        final String title = entity.getA_t() ;
        holder.tvTitle.setText(title);
        holder.tvRank.setText(  entity.getA_js());

        String images = entity.getA_img();
        if (!TextUtils.isEmpty(images)){
            holder.givImage.loadNetImage(images, R.color.font_black_6);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(entity);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        LinearLayout llRootView;
        GlideImageView givImage;
        TextView tvTitle;
        TextView tvRank;

        ViewHolder(View view) {
            llRootView = (LinearLayout) view.findViewById(R.id.ll_root_view);
            givImage = (GlideImageView) view.findViewById(R.id.giv_image);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvRank = (TextView) view.findViewById(R.id.tv_rank);
        }
    }

    ItemClickListener listener;

    public void setOnItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    public interface ItemClickListener{
        void click(Article entity);
    }
}
