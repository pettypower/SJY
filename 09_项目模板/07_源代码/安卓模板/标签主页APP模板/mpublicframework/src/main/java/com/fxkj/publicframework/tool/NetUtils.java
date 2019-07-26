package com.fxkj.publicframework.tool;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by dht on 2016/12/14.
 */

public class NetUtils {

    public static boolean hasNetWork(Context _context) {
        boolean flag = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Service.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            flag = activeNetworkInfo.isAvailable();
        }
        return flag;
    }
}
