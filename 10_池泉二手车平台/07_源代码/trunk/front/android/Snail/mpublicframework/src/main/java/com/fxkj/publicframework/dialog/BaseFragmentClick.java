package com.fxkj.publicframework.dialog;

import android.view.View;
import android.widget.AdapterView;

public interface BaseFragmentClick {

	// 点击试图的 id 最后传递给 实现者 baseActivity
	 void fClick(int id);

	 void onItemClick(AdapterView<?> parent, View view, int position,
                      long id);
}
