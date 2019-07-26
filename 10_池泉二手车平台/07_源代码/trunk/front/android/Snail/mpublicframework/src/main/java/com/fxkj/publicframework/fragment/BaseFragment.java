package com.fxkj.publicframework.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.fxkj.publicframework.activity.BaseTitleActivity;
import com.fxkj.publicframework.beans.CallBackObject;
import com.fxkj.publicframework.dialog.BaseFragmentClick;
import com.fxkj.publicframework.requestdata.CallBack;

//import com.squareup.leakcanary.RefWatcher;

public class BaseFragment extends Fragment implements OnClickListener,
        OnCheckedChangeListener, OnItemClickListener, CallBack {

    protected Activity mContext;
    private BaseFragmentClick fragmentClick;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getActivity();
        try {
            fragmentClick = (BaseFragmentClick) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnArticleSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void startActivity(Class<? extends BaseTitleActivity> targetClass) {
        mContext.startActivity(new Intent(getActivity(), targetClass));
    }

    /**
     * 集中处理 所有子类试图的 事件，分发给 baseActivity 去处理，不需要 每个子类自己分发。 所有的按键事件统一处理
     */
    @Override
    public void onClick(View arg0) {

        if (fragmentClick != null) {
            fragmentClick.fClick(arg0.getId());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        if (fragmentClick != null) {
            fragmentClick.fClick(arg1);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if (fragmentClick != null) {
            fragmentClick.onItemClick(parent, view, position, id);
        }
    }

    protected void hideSoftInputFromWindow() {
        InputMethodManager im = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View curentView = getActivity().getCurrentFocus();
        if (im != null && curentView != null) {
            IBinder iBinder = getActivity().getCurrentFocus()
                    .getApplicationWindowToken();
            im.hideSoftInputFromWindow(iBinder,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = MyApplication.getRefWatcher(mContext);
//        refWatcher.watch(this);
    }

    @Override
    public void onSuccess(int flag, CallBackObject obj) throws Exception {
        Log.e("ssssssssss","super.onSuccess(flag, obj);");
    }

    @Override
    public void onFailure(int flag, String message) {

    }

    @Override
    public void onNoData(int flag) {

    }
}
