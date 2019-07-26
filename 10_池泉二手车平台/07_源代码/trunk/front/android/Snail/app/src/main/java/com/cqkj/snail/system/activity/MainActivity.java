package com.cqkj.snail.system.activity;

import android.Manifest;
import android.app.ActivityGroup;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.cqkj.snail.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 主页
 *
 * @author wwb 2019/01/03
 */
public class MainActivity extends ActivityGroup {
    @BindView(R.id.view_tab_host)
    TabHost tabHost;
    @BindView(R.id.ll_bottom_tabs)
    LinearLayout ll_bottom_tabs;

    //底部标检图标集合
    private ArrayList<Map<Integer, Integer>> botResList;
    private RelativeLayout tab1, tab2, tab3, tab4;
    private List<RelativeLayout> tabRelativeLayouts = new ArrayList<RelativeLayout>();
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    //消息提示小红点
    private ImageView img_remind_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestPermissions(permissions);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
    }


    /**
     * 初始化界面元素
     */
    private void initUI() {
        initRes();
        initBottomUI();
    }

    private void initRes() {
        // 初始化底部图片资源文件
        botResList = new ArrayList<Map<Integer, Integer>>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, R.mipmap.guide_home_nm);
        map.put(1, R.mipmap.guide_home_on);
        botResList.add(map);
        map = new HashMap<Integer, Integer>();
        map.put(0, R.mipmap.guide_tfaccount_nm);
        map.put(1, R.mipmap.guide_tfaccount_on);
        botResList.add(map);
        map = new HashMap<Integer, Integer>();
        map.put(0, R.mipmap.guide_discover_nm);
        map.put(1, R.mipmap.guide_discover_on);
        botResList.add(map);
        map = new HashMap<Integer, Integer>();
        map.put(0, R.mipmap.guide_account_nm);
        map.put(1, R.mipmap.guide_account_on);
        botResList.add(map);

        // 加载底部Tab布局
        LayoutInflater inflater = LayoutInflater.from(this);
        // “首页”标签
        tab1 = (RelativeLayout) inflater.inflate(R.layout.action_item, null);
        ImageView icon1 = (ImageView) tab1.findViewById(R.id.icon);
        icon1.setImageResource(R.mipmap.guide_home_on);
        icon1.setTag(getString(R.string.first_pager));
        ImageView img_remind = (ImageView) tab1.findViewById(R.id.img_remind);
        img_remind.setVisibility(View.GONE);
        tabRelativeLayouts.add(tab1);

        // “信息”标签
        tab2 = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.action_item, null);
        ImageView icon2 = (ImageView) tab2.findViewById(R.id.icon);
        icon2.setImageResource(R.mipmap.guide_tfaccount_nm);
        icon2.setTag(getString(R.string.message));
        img_remind_msg = (ImageView) tab2.findViewById(R.id.img_remind);
        img_remind_msg.setVisibility(View.VISIBLE);
        tabRelativeLayouts.add(tab2);
        // "通知"标签
        tab3 = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.action_item, null);
        ImageView icon3 = (ImageView) tab3.findViewById(R.id.icon);
        icon3.setImageResource(R.mipmap.guide_discover_nm);
        icon3.setTag(getString(R.string.notice));
        img_remind = (ImageView) tab3.findViewById(R.id.img_remind);
        img_remind.setVisibility(View.GONE);
        tabRelativeLayouts.add(tab3);

        // “个人”标签
        tab4 = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.action_item, null);
        ImageView icon4 = (ImageView) tab4.findViewById(R.id.icon);
        icon4.setImageResource(R.mipmap.guide_account_nm);
        icon4.setTag(getString(R.string.mine));
        img_remind = (ImageView) tab4.findViewById(R.id.img_remind);
        img_remind.setVisibility(View.GONE);
        tabRelativeLayouts.add(tab4);
        // 加载TabSpec
        tabHost.setup(getLocalActivityManager());

    }

    /**
     * 第一页
     */
    private TabHost.TabSpec ts1;

    /**
     * 初始化底部按钮
     */
    private void initBottomUI() {
        tabHost.clearAllTabs();
        ts1 = tabHost
                .newTabSpec(getString(R.string.first_pager));
        ts1.setIndicator(tab1);// 这句话就是设置每个小tab显示的内容
        ts1.setContent(new Intent(this, FirstPagerActivity.class));
        tabHost.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost.newTabSpec(getString(R.string.message));
        ts2.setIndicator(tab2);
        Intent intent = new Intent(this, InformationActivity.class);
        ts2.setContent(intent);
        tabHost.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost.newTabSpec(getString(R.string.notice));
        ts3.setIndicator(tab3);
        Intent intent3 = new Intent(this, NoticeActivity.class);
        intent3.putExtra("showReturn", 1);
        ts3.setContent(intent3);
        tabHost.addTab(ts3);

        TabHost.TabSpec ts4 = tabHost.newTabSpec(getString(R.string.mine));
        ts4.setIndicator(tab4);
        Intent intent4 = new Intent(this, MineActivity.class);
        ts4.setContent(intent4);
        tabHost.addTab(ts4);
        tabHost.setOnTabChangedListener(new TabChange());
        tab1.setOnClickListener(new TabOnClick(0));
        tab2.setOnClickListener(new TabOnClick(1));
        tab3.setOnClickListener(new TabOnClick(2));
        tab4.setOnClickListener(new TabOnClick(3));
        //调整发布按钮图标的大小
        View childTabViewAt = tabHost.getTabWidget().getChildTabViewAt(2);
        ImageView icon_add = (ImageView) childTabViewAt.findViewById(R.id.icon);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) icon_add.getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.icon_size2);
        icon_add.setLayoutParams(params);
    }

    class TabOnClick implements View.OnClickListener {
        private int position;

        public TabOnClick(int position) {
            super();
            this.position = position;
        }

        public void onClick(View v) {
            tabHost.setCurrentTab(position);
        }
    }

    /**
     * 页卡切换监听
     */
    class TabChange implements TabHost.OnTabChangeListener {

        @Override
        public void onTabChanged(String tabId) {
            for (int i = 0; i < tabRelativeLayouts.size(); i++) {
                RelativeLayout relativeLayout = tabRelativeLayouts.get(i);
                RelativeLayout linearLayout = (RelativeLayout) relativeLayout
                        .getChildAt(0);
                ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                if (imageView.getTag().toString().equals(tabId)) {
                    imageView.setImageResource(botResList.get(i).get(1));
                } else {
                    imageView.setImageResource(botResList.get(i).get(0));
                }
            }
        }
    }

    private void requestPermissions(String[] _permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> mPermissionList = new ArrayList<>();
            mPermissionList.clear();
            for (String _permission : _permissions) {
                if (ActivityCompat.checkSelfPermission(this, _permission) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(_permission);
                }
            }
            if (!mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
                String[] permissions2 = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
                ActivityCompat.requestPermissions(this, permissions2, 0000);
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
