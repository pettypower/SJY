package com.fxkj.publicframework.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Window;
import android.view.WindowManager;

import com.fxkj.publicframework.R;
import com.fxkj.publicframework.fragment.ImageDetailFragment;
import com.fxkj.publicframework.widget.HackyViewPager;

import java.util.ArrayList;


public class ImagePagerActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";

    private HackyViewPager mPager;
    private ArrayList<String> list = new ArrayList<>();
    private String currImageUrl;//点击图片的网址

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.image_detail_pager);
        list = getIntent().getStringArrayListExtra("imagepath");
        currImageUrl = getIntent().getStringExtra("url");//
        mPager = (HackyViewPager) findViewById(R.id.pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), list);
        mPager.setAdapter(mAdapter);
        if (savedInstanceState != null) {
            int pagerPosition = savedInstanceState.getInt(STATE_POSITION);
            mPager.setCurrentItem(pagerPosition);
        } else {
            if (currImageUrl != null && list != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(currImageUrl)) {
                        mPager.setCurrentItem(i);
                    }
                }
            }
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);
        }

    }


}