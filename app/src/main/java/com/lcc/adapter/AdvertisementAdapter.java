package com.lcc.adapter;

import java.util.List;
import org.json.JSONArray;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.lcc.activity.R;

public class AdvertisementAdapter extends PagerAdapter {

	private Context context;
	private List<View> views;
	JSONArray advertiseArray;
	
	public AdvertisementAdapter() {
		super();
	}

	public AdvertisementAdapter(Context context, List<View> views, JSONArray advertiseArray) {
		this.context = context;
		this.views = views;
		this.advertiseArray = advertiseArray;
	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position), 0);
		final int POSITION = position;
		View view = views.get(position);
		try {
			//图片的下载地址在json里面
			String head_img = advertiseArray.optJSONObject(position).optString("head_img");
			ImageView ivAdvertise = (ImageView) view.findViewById(R.id.ivAdvertise);
			Glide.with(ivAdvertise.getContext())
					.load(head_img)
					.centerCrop()
					.placeholder(R.drawable.loading1)
					.crossFade()
					.into(ivAdvertise);
			ivAdvertise.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "第"+POSITION+"个广告图片被点击", Toast.LENGTH_SHORT).show();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

}
