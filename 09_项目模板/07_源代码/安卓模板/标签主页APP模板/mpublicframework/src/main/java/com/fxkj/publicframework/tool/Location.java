package com.fxkj.publicframework.tool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

public class Location implements LocationListener {
    private Context context;
    private GetLocationListener getLocationListener;
    private static Location location;

    public static Location getLocation(Context _context, GetLocationListener _getLocationListener) {
        if (location == null) {
            location = new Location(_context, _getLocationListener);
        }
        return location;
    }

    public Location(Context _context, GetLocationListener _getLocationListener) {
        context = _context;
        getLocationListener = _getLocationListener;
    }

    public boolean isCanGetLocation() {
        LocationManager alm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "请开启GPS！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent); // 此为设置完成后返回到获取界面
            return false;
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "开启定位权限后再试", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //一次性定位
    @SuppressLint("MissingPermission")
    public android.location.Location onceLocation() {
        if (!isCanGetLocation()) {
            return null;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        android.location.Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        return location;
    }

    //持续定位
    public void startLocation() {
        //获取位置服务
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取最佳位置定位方式
        String locationProvider = locationManager.getBestProvider(createFineCriteria(), true);
        //创建位置监听对象
        //开启位置监听
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else {
            //设置位置更新限制，1秒或5m更新一次
            locationManager.requestLocationUpdates(locationProvider, 0, 0, this);
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
