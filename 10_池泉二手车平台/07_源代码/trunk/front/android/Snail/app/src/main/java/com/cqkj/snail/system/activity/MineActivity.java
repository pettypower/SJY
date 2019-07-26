package com.cqkj.snail.system.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.cqkj.snail.R;
import com.fxkj.publicframework.activity.BaseTitleActivity;
import com.fxkj.publicframework.beans.CallBackObject;

import java.text.ParseException;

/**
 * 个人页
 */
public class MineActivity extends BaseTitleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initView() {
        super.initView();
        title_text.setText(getString(R.string.mine));
        tv_back.setVisibility(View.GONE);
        title_do.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
