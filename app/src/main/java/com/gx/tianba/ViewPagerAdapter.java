package com.gx.tianba;

import android.app.Fragment;
import android.app.FragmentManager;
import android.view.ViewGroup;


import java.util.List;

public  class ViewPagerAdapter extends android.support.v13.app.FragmentPagerAdapter {

    private List<android.app.Fragment> fragmentlists;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentlists) {
        super(fm);
        this.fragmentlists = fragmentlists;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlists.get(i);
    }

    @Override
    public int getCount() {
        return fragmentlists.size();
    }

}
