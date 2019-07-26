package com.fxkj.publicframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fxkj.publicframework.R;


public class SpinerAdapter extends BaseAdapter {

	public static interface IOnItemSelectListener {
		public void onItemClick(int pos);
	};

	private String[] mList;

	private LayoutInflater mInflater;

	public SpinerAdapter(Activity activity, String[] list) {
		mList = list;
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void refreshData(String[] list, int selIndex) {
		mList = list;
		if (selIndex < 0) {
			selIndex = 0;
		}
		if (selIndex >= mList.length) {
			selIndex = mList.length - 1;
		}
	}

	@Override
	public int getCount() {

		return mList.length;
	}

	@Override
	public String getItem(int pos) {
		return mList[pos];
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.spiner_item_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.textView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mTextView.setText(getItem(pos));
		return convertView;
	}

	class ViewHolder {
		TextView mTextView;
	}

}
