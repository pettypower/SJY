package com.fxkj.publicframework.tool;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by hlj on 2018/8/27.
 */

public class CountDownTimerUtil extends android.os.CountDownTimer {
    private TextView textView;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtil(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    /**
     * 倒计时过程中调用
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        Log.e("Tag", "millisUntilFinished=" + millisUntilFinished);
//            Log.e("Tag", "倒计时=" + (millisUntilFinished/1000));
        //处理后的倒计时数值
        int time = (int) (Math.round((double) millisUntilFinished / 1000) - 1);
        textView.setText(String.valueOf(time) + "''");
        //设置倒计时中的按钮外观
        textView.setClickable(false);//倒计时过程中将按钮设置为不可点击
    }

    /**
     * 倒计时完成后调
     */
    @Override
    public void onFinish() {
        Log.e("Tag", "倒计时完成");
        textView.setText("获取验证码");
        textView.setClickable(true);
    }

}
