package com.fxkj.publicframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.RequestManager;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by wxmij on 2016/11/29.
 */

public class CommonAdapter extends BaseAdapter {
    protected Context context;
    protected List<?> list;
    protected int layoutid;
    protected RequestManager glide;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(layoutid, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    public static class ViewHolder {
        protected ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
