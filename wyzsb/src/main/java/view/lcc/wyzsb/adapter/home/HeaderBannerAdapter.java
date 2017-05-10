package view.lcc.wyzsb.adapter.home;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import view.lcc.wyzsb.R;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderBannerAdapter extends PagerAdapter {

    private List<ImageView> ivList; // ImageView的集合
    private List<String> list;

    public HeaderBannerAdapter(List<ImageView> ivList,List<String> list) {
        super();
        this.list = list;
        this.ivList = ivList;
    }

    @Override
    public int getCount() {
        return ivList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(ivList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ((ViewPager) container).addView(ivList.get(position), 0);
        View view = ivList.get(position);
        try {
            String head_img =list.get(position);
            ImageView ivAdvertise = (ImageView) view.findViewById(R.id.ivAdvertise);
            Glide.with(ivAdvertise.getContext())
                    .load(head_img)
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .crossFade()
                    .into(ivAdvertise);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
