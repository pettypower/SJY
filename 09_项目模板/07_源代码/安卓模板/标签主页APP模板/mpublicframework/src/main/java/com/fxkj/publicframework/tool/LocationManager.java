package com.fxkj.publicframework.tool;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.nio.Buffer;

public class LocationManager implements LocationListener {
    private Context context;
    private android.location.LocationManager locationManagerManager;
    private GetLocationListener getLocationListener;
    private static LocationManager locationManager;

    public static LocationManager getLocation(Context _context, GetLocationListener _getLocationListener) {
        if (locationManager == null) {
            locationManager = new LocationManager(_context, _getLocationListener);
        }
        return locationManager;
    }

    public LocationManager(Context _context, GetLocationListener _getLocationListener) {
        context = _context;
        getLocationListener = _getLocationListener;
        locationManagerManager = (android.location.LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    //一次性定位
    public android.location.Location onceLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "开启定位权限后再试", Toast.LENGTH_SHORT).show();
            return null;
        }

        android.location.Location location = locationManagerManager.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManagerManager.getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER);
        }
        return location;
    }

    //持续定位
    public void startLocation(int _distance) {
        //获取位置服务
        //获取最佳位置定位方式
        String locationProvider = locationManagerManager.getBestProvider(createFineCriteria(), true);
        //创建位置监听对象
        //开启位置监听
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            //设置位置更新限制，1秒或5m更新一次
            locationManagerManager.requestLocationUpdates(locationProvider, 1,_distance, this);
        }
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        getLocationListener.getLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void onDestroy() {
        if (getLocationListener != null) {
            locationManagerManager.removeUpdates(this);
            Log.e("wxmijl", "注销位置监听");
        }
    }

    public static Criteria createFineCriteria() {
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);//高精度
        c.setAltitudeRequired(true);//包含高度信息
        c.setBearingRequired(true);//包含方位信息
        c.setSpeedRequired(true);//包含速度信息
        c.setCostAllowed(true);//允许付费
        c.setPowerRequirement(Criteria.POWER_HIGH);//高耗电
        return c;
    }

    public interface GetLocationListener {
        void getLocation(android.location.Location location);
    }
}
