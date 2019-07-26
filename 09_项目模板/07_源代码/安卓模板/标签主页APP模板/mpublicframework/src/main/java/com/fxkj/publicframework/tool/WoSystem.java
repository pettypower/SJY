package com.fxkj.publicframework.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_PHONE_STATE;

public class WoSystem {

    public static StringBuffer buffer = new StringBuffer();
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getIMEI(Context _context) {
        TelephonyManager tm = (TelephonyManager) _context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return tm != null ? tm.getImei() : "";
        }
        return tm != null ? tm.getDeviceId() : "";
    }
    /**
     * 隐藏输入法
     */
    public static  void hideSoftInputFromWindow(Activity _context) {
        InputMethodManager mImm = (InputMethodManager) _context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = mImm.isActive();
        if (isOpen) {
            if (_context.getCurrentFocus() != null) {
                mImm.hideSoftInputFromWindow(_context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                mImm.hideSoftInputFromWindow(null, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    //沉浸式状态栏
    public  static void setImmersiveStateBar(Activity _context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = _context.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * getSystemDownloadPath
     *
     * @param context
     * @return
     * @Description: 获取系统下载目录下的APP
     * @date 2013-5-29
     */
    public static String getSystemDownloadPath(Context context) {
        String path = context.getApplicationContext().getFilesDir().getParentFile().getPath() + ("/apps/");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            // 让系统有读取/data/data/的权限 777指所有用户都有RWR（读写运行）权限
            String args[] = {"chmod", "777", path};
            exec(args);
        }

        return path;
    }

    public static String exec(String[] args) {

        String result = "";

        ProcessBuilder processBuilder = new ProcessBuilder(args);

        Process process = null;

        InputStream errIs = null;

        InputStream inIs = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;

            process = processBuilder.start();

            errIs = process.getErrorStream();

            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }

            baos.write('\n');

            inIs = process.getInputStream();

            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            baos.flush();
            byte[] data = baos.toByteArray();

            result = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (errIs != null) {
                    errIs.close();
                }
                if (inIs != null) {
                    inIs.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }

        return result;

    }

    /**
     * 删除 文件
     *
     * @param file 要删除的文件
     */
    public static void deleteFile(File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
        }
    }

    // 代码重构：复用方法
    public static String setUserAgent(Context mContext) {
        try {
            // 取得浏览器的UA
            WebView web = new WebView(mContext);
            String userAgent = web.getSettings().getUserAgentString();
            return userAgent;
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 手机号码验证 适合目前所有的手机
    public static boolean isPhoneNumber(String str) {
        String regex = "^(1)\\d{10}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public static boolean copy(String file1, String file2) {
        File in = new File(file1);
        File out = new File(file2);
        if (!in.exists()) {
            return false;
        }

        if (!out.exists()) {
            out.mkdirs();
        } else {

        }
        System.out.println("cpoy start");
        File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                try {
                    fis = new FileInputStream(file[i]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    File f = new File(file2 + "/" + file[i].getName());
                    fos = new FileOutputStream(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                int c;
                byte[] b = new byte[1024 * 5];
                try {
                    while ((c = fis.read(b)) != -1) {
                        fos.write(b, 0, c);
                    }
                    fis.close();
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                copy(file1 + "/" + file[i].getName(), file2 + "/" + file[i].getName());
        }
        return true;
    }
}