package com.fxkj.publicframework.requestdata;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fxkj.publicframework.beans.CallBackObject;
import com.fxkj.publicframework.globalvariable.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 管理所有请求类
 * Created by dongjunkun on 2016/2/1.
 */
public class RequestManager {
    private Handler handler = new Handler(Looper.getMainLooper());


    protected void post(final int tag, String url, final HashMap params, final boolean isCache, final CallBack callBack, final Context _context) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        Iterator map1it = params.entrySet().iterator();
        while (map1it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) map1it.next();
            //String newStr = new String(entry.getValue().getBytes("UTF-8"), "ISO-8859-1").trim();
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String errorStr = e.getLocalizedMessage();
                        if (errorStr != null) {
                            if (errorStr.contains("502") || errorStr.contains("No address associated with hostname")) {
                                errorStr = "连接服务器失败";
                            }
                        }
                        OnFail(callBack, "连接服务器失败", tag);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    if (response == null) {
                        if (callBack != null) {
                            OnFail(callBack, "没有返回数据", tag);
                        }
                        return;
                    }
                    if (json.isEmpty()) {
                        if (callBack != null) {
                        }
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(json);
                    String resultsCode = jsonObject.getString("code");
                    if (resultsCode.equals("10000")) {
                        if (callBack != null) {
                            onSuccess(callBack, tag, AnalyzeJson(jsonObject, tag, params));
                        }
                    } else {
                        if (callBack != null) {
                            String errorMsg = jsonObject.getString("msg");
                            OnFail(callBack, errorMsg, tag);
                        }
                    }
                } catch (Exception e) {
                    Log.i("wxmijl", e.getMessage().toString());
                    if (callBack != null) {
                        OnFail(callBack, "格式化数据出错", tag);
                    }
                }
            }
        });
    }

    protected void get(final int tag, final String url, final HashMap params, final boolean isCache, final CallBack callBack, final Context _context) {
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, final Exception e, int id) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String errorStr = e.getLocalizedMessage();
                                if (errorStr != null) {
                                    if (errorStr.contains("502") || errorStr.contains("No address associated with hostname") || e instanceof TimeoutException) {
                                        errorStr = "连接服务器失败";
                                    }
                                }
                                OnFail(callBack, "连接服务器失败", tag);
                            }
                        });
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            String json = response.toString();
                            if (response == null) {
                                if (callBack != null) {
                                    OnFail(callBack, "没有返回数据", tag);
                                }
                                return;
                            }
                            if (json.isEmpty()) {
                                if (callBack != null) {
                                }
                                return;
                            }
                            JSONObject jsonObject = new JSONObject(json);
                            if (!url.contains("feizaotai")) {
                                onSuccess(callBack, tag, AnalyzeJson(jsonObject, tag, params));
                                return;
                            }
                            String resultsCode = jsonObject.getString("code");
                            if (resultsCode.equals("10000")) {
                                if (callBack != null) {
                                    onSuccess(callBack, tag, AnalyzeJson(jsonObject, tag, params));
                                }
                            } else {
                                if (callBack != null) {
                                    //10000: 成功， 10001：参数错误 10002：md5验证错误 10003：超时错误 10004：验证码次数已达上限 10005：发送失败
                                    String errorMsg = jsonObject.getString("msg");
                                    OnFail(callBack, errorMsg, tag);
                                }
                            }
                        } catch (Exception e) {
                            Log.i("wxmijl", e.getMessage().toString());
                            if (callBack != null) {
                                OnFail(callBack, "格式化数据出错", tag);
                            }
                        }
                    }
                });
    }

    protected void post_file(final int tag, final String url, final HashMap<String, Object> params, File file, final CallBack callBack) {
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", filename, body);
        }
        if (params != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : params.entrySet()) {
                requestBody.addFormDataPart(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String errorStr = e.getMessage();
                        OnFail(callBack, errorStr, tag);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String resultsCode = jsonObject.getString("code");
                        if (resultsCode.equals("10000")) {
                            if (callBack != null) {
                                onSuccess(callBack, tag, AnalyzeJson(jsonObject, tag, params));
                            }
                        } else {
                            if (callBack != null) {
                                //10000: 成功， 10001：参数错误 10002：md5验证错误 10003：超时错误 10004：验证码次数已达上限 10005：发送失败
                                String errorMsg = jsonObject.getString("msg");
                                OnFail(callBack, errorMsg, tag);
                            }
                        }
                    } catch (JSONException e) {
                        OnFail(callBack, json, tag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    protected void postMoreFile(final int tag, final String url, final HashMap<String, Object> params, final CallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            if (key.equals("file")) {//如果请求的值是文件
                List<File> fileList = (List<File>) value;
                for (int i = 0; i < fileList.size(); i++) {
                    builder.addFormDataPart("file[" + i + "]", fileList.get(i).getName(), RequestBody.create(guessMimeType(fileList.get(i).getName()), fileList.get(i)));
                }
            } else {//如果请求的值是string类型
                builder.addFormDataPart(key, value.toString());
            }
        }

        Request request = new Request.Builder().post(builder.build()).url(url).build();
        // readTimeout("请求超时时间" , 时间单位);
        okHttpClient.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String errorStr = e.getMessage();
                        OnFail(callBack, errorStr, tag);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String resultsCode = jsonObject.getString("code");
                        if (resultsCode.equals("10000")) {
                            if (callBack != null) {
                                onSuccess(callBack, tag, AnalyzeJson(jsonObject, tag, params));
                            }
                        } else {
                            if (callBack != null) {
                                //10000: 成功， 10001：参数错误 10002：md5验证错误 10003：超时错误 10004：验证码次数已达上限 10005：发送失败
                                String errorMsg = jsonObject.getString("msg");
                                OnFail(callBack, errorMsg, tag);
                            }
                        }
                    } catch (JSONException e) {
                        OnFail(callBack, json, tag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected void postFile2(String url, File _file, final CallBack callBack, final HashMap params) {
        url += "?fileName=" + _file.getName();

        RequestBody fileBody = RequestBody.create(MediaType.parse("video/mp4"), _file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fileName", _file.getName(), fileBody).build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newBuilder().writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                String errorStr = e.getMessage();
                OnFail(callBack, errorStr, -2);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if (json != null) {
                    try {
                        onSuccess(callBack, -2, AnalyzeJson(new JSONObject(json), -2, params));
                    } catch (JSONException e) {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 根据文件名获取MIME类型
     */
    public static MediaType guessMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        fileName = fileName.replace("#", "");   //解决文件名中含有#号异常的问题
        String contentType = fileNameMap.getContentTypeFor(fileName);
        if (contentType == null) {
            return MediaType.parse("application/octet-stream");
        }
        return MediaType.parse(contentType);
    }

    private void OnFail(final CallBack callBack, final String error_text, final int flag) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailure(flag, error_text);
                }
            }
        });

    }

    private void onSuccess(final CallBack callBack, final int flag, final CallBackObject _callBackObject) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    callBack.onSuccess(flag, _callBackObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    protected CallBackObject AnalyzeJson(JSONObject _json, final int flag, HashMap params) throws Exception {
        return null;
    }


}
