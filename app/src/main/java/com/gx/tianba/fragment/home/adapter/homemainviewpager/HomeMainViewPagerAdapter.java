package com.gx.tianba.fragment.home.adapter.homemainviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gx.tianba.R;
import com.gx.tianba.util.fresco.FrescoUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeMainViewPagerAdapter extends PagerAdapter {

    private ArrayList<String> banners;
    private Context context;

    public HomeMainViewPagerAdapter(ArrayList<String> banners, Context context) {
        this.banners = banners;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context,R.layout.home_main_viewpager_item, null);
        ImageView img = view.findViewById(R.id.home_main_viewpager_item_iamge);
        Picasso.with(context).load(banners.get(position%banners.size())).into(img);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
