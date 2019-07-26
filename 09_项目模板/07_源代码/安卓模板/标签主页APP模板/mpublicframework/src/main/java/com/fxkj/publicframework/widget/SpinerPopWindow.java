package com.fxkj.publicframework.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fxkj.publicframework.R;
import com.fxkj.publicframework.adapter.SpinerAdapter;

public class SpinerPopWindow extends PopupWindow implements OnItemClickListener {

	private Activity mActivity;
	private ListView mListView;
	private SpinerAdapter mAdapter;
	private TextView mTextView;
	private String[] mArray;

	private SpinerAdapter.IOnItemSelectListener mItemSelectListener;

	public SpinerPopWindow(Context context, String[] array, TextView tv) {
		super(context);
		mActivity = (Activity) context;
		mTextView = tv;
		mArray = array;
		init();
	}

	public void setItemListener(SpinerAdapter.IOnItemSelectListener listener) {
		mItemSelectListener = listener;
	}

	public void setAdatper(SpinerAdapter adapter) {
		mAdapter = adapter;
		mListView.setAdapter(mAdapter);
	}

	private void init() {

		View view = LayoutInflater.from(mActivity).inflate(R.layout.spiner_window_layout, null);
		setContentView(view);
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);

		ColorDrawable dw = new ColorDrawable(0x000000);
		setBackgroundDrawable(dw);
		setOutsideTouchable(true);
		setFocusable(true);
		setTouchable(true);

		setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				windowsAlpha(1.0f);
			}
		});

		mListView = (ListView) view.findViewById(R.id.listview);
		mListView.setOnItemClickListener(this);
	}

	public void refreshData(String[] list, int selIndex) {
		if (list != null && selIndex != -1) {
			if (mAdapter != null) {
				mAdapter.refreshData(list, selIndex);
			}
		}
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		windowsAlpha(0.7f);
		super.showAtLocation(parent, gravity, x, y);
	}

	/** 窗体透明度 */
	private void windowsAlpha(float alpha) {
		WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
		lp.alpha = alpha;
		mActivity.getWindow().setAttributes(lp);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		dismiss();
		if (mItemSelectListener != null) {
			mItemSelectListener.onItemClick(pos);
		}
		Log.i("onItemClick", mArray[pos]);
		mTextView.setText(mArray[pos]);
	}

}
