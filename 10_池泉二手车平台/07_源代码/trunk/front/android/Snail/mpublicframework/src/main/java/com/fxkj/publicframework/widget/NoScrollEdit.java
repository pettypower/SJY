package com.fxkj.publicframework.widget;

import android.content.Context;
import android.util.AttributeSet;

public class NoScrollEdit extends android.support.v7.widget.AppCompatEditText {
    public NoScrollEdit(Context context) {
		super(context);
	}
	public NoScrollEdit(Context context, AttributeSet attrs) {
		super(context, attrs); 
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}