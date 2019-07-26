package com.cqkj.snail.system.activity;

import android.view.View;

import com.cqkj.snail.R;
import com.fxkj.publicframework.activity.BaseTitleActivity;
import com.fxkj.publicframework.beans.CallBackObject;

import java.text.ParseException;


/**
 * 首页
 */
public class FirstPagerActivity extends BaseTitleActivity {

    @Override
    protected int getLayoutId() {
        setBack(false);
        return R.layout.activity_first;
    }

    @Override
    protected void initView() {
        super.initView();
        title_text.setText(getString(R.string.first_pager));
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
    public void onSuccess(int flag, CallBackObject obj) throws ParseException {
    }


    @Override
    public void onFailure(int flag, String message) {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
