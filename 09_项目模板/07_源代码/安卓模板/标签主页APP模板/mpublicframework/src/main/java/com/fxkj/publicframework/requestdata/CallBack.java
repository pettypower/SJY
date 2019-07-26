package com.fxkj.publicframework.requestdata;

import com.fxkj.publicframework.beans.CallBackObject;

import java.text.ParseException;

/**
 * by:wxmijl
 */
public interface CallBack {

    void onSuccess(int flag, CallBackObject obj) throws Exception;

    void onFailure(int flag, String message);

    void onNoData(int flag);
}
