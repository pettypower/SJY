package com.fxkj.publicframework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.fxkj.publicframework.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * Created by dht on 2017/3/8.
 */

public class LoadingDialog extends Dialog {

    private TextView tv_text;
    private AVLoadingIndicatorView avl;

    public LoadingDialog(Context context) {
        super(context);
        /*设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_common_loading_layout);
        avl = findViewById(R.id.avl);
//        tv_text = (TextView) findViewById(R.id.tv_text);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     *
     * @param message 给用户展示的提示信息
     * @return build模式设计，可以链式调用
     */
    public LoadingDialog setMessage(String message) {
//        tv_text.setText(message);
        return this;
    }
}
