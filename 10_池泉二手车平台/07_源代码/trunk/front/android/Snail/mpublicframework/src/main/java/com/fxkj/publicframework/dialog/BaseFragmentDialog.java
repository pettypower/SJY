package com.fxkj.publicframework.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fxkj.publicframework.beans.CallBackObject;
import com.fxkj.publicframework.requestdata.CallBack;

/**
 * Created by gml on 16/9/19.
 */
public class BaseFragmentDialog extends DialogFragment implements View.OnClickListener ,CallBack {

    protected Activity mContext;
    private BaseFragmentClick fragmentClick;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        try {
            fragmentClick = (BaseFragmentClick) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black);
    }

    public void onClick(View v) {
        dismiss();
        if (fragmentClick != null) {
            fragmentClick.fClick(v.getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            Log.e("dialog", "Exception", e);
        }
    }

    @Override
    public void onSuccess(int flag, CallBackObject obj) throws Exception {

    }

    @Override
    public void onFailure(int flag, String message) {

    }

    @Override
    public void onNoData(int flag) {

    }
}
