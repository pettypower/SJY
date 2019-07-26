package com.fxkj.publicframework.tool;

import com.fxkj.publicframework.beans.Weather;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class WeatherManager {

    public static void getWether(WeatherCallBack weatherCallBack) {
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?key=086018b022fbbf14e53f265105501f56&city=360702&output=JSON";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        String json = e.toString();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String json = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.getString("infocode").equals("10000")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("lives");
                                if (jsonArray.length() > 0) {
                                    JSONObject object = jsonArray.getJSONObject(0);
                                    Weather weather1 = new Weather();
                                    weather1.setProvince(object.getString("province"));
                                    weather1.setCity(object.getString("city"));
                                    weather1.setAdcode(object.getString("adcode"));
                                    weather1.setWeather(object.getString("weather"));
                                    weather1.setTemperature(object.getString("temperature"));
                                    weather1.setWinddirection(object.getString("winddirection"));
                                    weather1.setWindpower(object.getString("windpower"));
                                    weather1.setHumidity(object.getString("humidity"));
                                    weather1.setReporttime(object.getString("reporttime"));
                                    weatherCallBack.callBack(weather1);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public static void getlocationInfo( android.location.Location _location,WeatherCallBack weatherCallBack) {
        String url = "https://restapi.amap.com/v3/geocode/regeo?"
                +"key=086018b022fbbf14e53f265105501f56"
                +"&location=" + _location.getLongitude() + ","+_location.getLatitude()
                + "&output=JSON";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        String json = e.toString();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String json = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.getString("infocode").equals("10000")) {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public interface WeatherCallBack {
        void callBack(Weather _weather);
    }
}
