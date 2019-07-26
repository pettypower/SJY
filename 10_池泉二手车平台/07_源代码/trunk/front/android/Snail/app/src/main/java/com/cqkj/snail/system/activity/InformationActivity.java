package com.cqkj.snail.system.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;


import com.cqkj.snail.R;
import com.fxkj.publicframework.activity.BaseActivityManager;
import com.fxkj.publicframework.activity.BaseTitleActivity;
import com.fxkj.publicframework.beans.CallBackObject;
import com.fxkj.publicframework.tool.CommonUtil;
import com.fxkj.publicframework.widget.WListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


/**
 * 信息页
 */
public class InformationActivity extends BaseTitleActivity {


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        setBack(false);
        return R.layout.activity_information;
    }

    @Override
    protected void initView() {
        super.initView();
        title_text.setText(getString(R.string.message));
        tv_back.setVisibility(View.GONE);
        title_do.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSuccess(int flag, CallBackObject obj) throws ParseException {
        super.onSuccess(flag, obj);
    }

    @Override
    public void onFailure(int flag, String message) {
        super.onFailure(flag, message);
    }

    @Override
    public void onBackPressed() {

    }

}
