package com.gx.tianba.load.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gx.tianba.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动页的Adapter
 */
public class LoadingPagerAdapter extends PagerAdapter {

    private List<Integer> listImages=new ArrayList<>();

    public LoadingPagerAdapter(List<Integer> listImages) {
        this.listImages = listImages;
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.item_load_image,null);
        ImageView imageView= inflate.findViewById(R.id.itemloadiamge);
        imageView.setImageResource(listImages.get(position));
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
