package com.fxkj.publicframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dht on 2016/12/22.
 */

public class CommonPageAdapter extends FragmentPagerAdapter {


    private final ArrayList<Fragment> list_fragment;
    private final List<String> list_title;

    public CommonPageAdapter(FragmentManager fm, ArrayList<Fragment> list_fragment, List<String> list_title) {
        super(fm);
        this.list_fragment=list_fragment;
        this.list_title=list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }
    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
