package com.fxkj.publicframework.tool;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageFunction {

    private static final String TAG = "ImageLog";

    /**
     * saveBitmapByPath
     *
     * @param strPath
     * @param bitmapParam
     * @return
     * @Description: 保存图片到本地SD卡
     * @date 2014-5-12
     */
    public static boolean saveBitmapToPath(String strPath, Bitmap bitmapParam, CompressFormat format, int sacle) {
        boolean bRet = false;
        if (null == strPath || null == bitmapParam || "".equals(strPath)) {
            return bRet;
        }
        File bitmapFile = new File(strPath);
        if (bitmapFile.exists()) {
            if (bitmapFile.isDirectory()) {
                return bRet;
            }
            bitmapFile.delete();
        }
        try {
            if (!bitmapFile.createNewFile()) {
                return bRet;
            }

            FileOutputStream fout = new FileOutputStream(bitmapFile);

            bRet = bitmapParam.compress(format, sacle, fout);

            fout.flush();
            fout.close();
            bitmapFile = null;
            Log.d("TAG", "saveBitmapByPath() is End. It is ok");
        } catch (IOException e) {
            Log.d("TAG", "saveBitmapByPath() IOException: " + e);
            e.printStackTrace();
        }
        // if (bitmapParam != null) {
        // bitmapParam.recycle();
        // bitmapParam = null;
        // }
        return bRet;
    }

    /**
     * getBitmapLocation
     *
     * @param strUrl 图片地址
     * @return
     * @Description: 获取本地图片Bitmap
     */
    public static Bitmap getBitmapFromLocation(String strUrl) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(strUrl);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    // Bitmap bitmap =
    // BitmapFactory.decodeByteArray(Util.decodeBitmap(imagePath), 0,
    // Util.decodeBitmap(imagePath).length);
    // imageCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
    public static Bitmap getBitmap(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeFile(path, opts);
        opts.inSampleSize = computeSampleSize(opts, -1, 1024 * 800);
        opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDither = false;
        opts.inPurgeable = true;
        opts.inTempStorage = new byte[16 * 1024];
        FileInputStream is = null;
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(path);
            bmp = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
            // double scale = getScaling(opts.outWidth * opts.outHeight, 1920 *
            // 1080);
            // Bitmap bmp2 = Bitmap.createScaledBitmap(bmp, (int) (opts.outWidth
            // * scale), (int) (opts.outHeight * scale), true);
            bmp.recycle();
            // baos = new ByteArrayOutputStream();
            // bmp2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            // bmp2.recycle();
            return bmp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }
        return null;
    }

    public static byte[] decodeBitmap(String path) {
        // 读系统目录时，给该目录付读写权限
        if (path.startsWith("/data/")) {
            String args[] = {"chmod", "777", path};
            WoSystem.exec(args);
        }
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeFile(path, opts);
        opts.inSampleSize = computeSampleSize(opts, -1, 2272 * 1280);
        opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDither = false;
        opts.inPurgeable = true;
        opts.inTempStorage = new byte[16 * 1024];
        FileInputStream is = null;
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(path);
            bmp = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
            if (bmp == null) {
                return null;
            }
            double scale = getScaling(opts.outWidth * opts.outHeight, 2272 * 1280);
            Bitmap bmp2 = Bitmap.createScaledBitmap(bmp, (int) (opts.outWidth * scale), (int) (opts.outHeight * scale), true);
            baos = new ByteArrayOutputStream();
            bmp2.compress(CompressFormat.JPEG, 100, baos);
            bmp2.recycle();
            bmp.recycle();
            return baos.toByteArray();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }
        return (baos != null) ? baos.toByteArray() : null;
    }

    private static double getScaling(int src, int des) {
        /**
         * 48 目标尺寸÷原尺寸 sqrt开方，得出宽高百分比 49
         */
        double scale = Math.sqrt((double) des / (double) src);
        return scale;
    }

    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static String getPath(Uri uri, Context context) {
        String[] proj = {MediaStore.Images.Media.DATA};
        ContentResolver cr = context.getContentResolver();

        Cursor cursor = cr.query(uri, proj, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            int actual_image_column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            return cursor.getString(actual_image_column_index);
        } else {
            return "";
        }
    }

    // path为下载路径,saveName是保存名称可以是任何文件
    public static void getGifByUrl(String path, String saveName) throws Exception {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(1000 * 6);
        if (con.getResponseCode() == 200) {
            InputStream inputStream = con.getInputStream();
            byte[] b = getByte(inputStream);
            File file = new File(saveName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(b);
            fileOutputStream.close();

        }
    }

    private static byte[] getByte(InputStream inputStream) throws Exception {
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * getBitmapByUrl
     *
     * @param urlString
     * @return
     * @Description: 根据网络地址获取网络图片Bitmap
     * @date 2012-11-28
     */
    public static Bitmap getBitmapByUrl(String urlString) {
        if ((null == urlString) || ("".equals(urlString))) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(90000);
            InputStream input = connection.getInputStream();
            byte[] imagebyte = readInputStream(input);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;
            bitmap = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length, opts);
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * getBitmapByUrl
     *
     * @param urlString
     * @return
     * @Description: 根据网络地址获取网络图片Bitmap
     * @date 2012-11-28
     */
    public static InputStream getMovieByUrl(String urlString) {
        if ((null == urlString) || ("".equals(urlString))) {
            return null;
        }
        InputStream bitmap = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(90000);
            bitmap = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static boolean checkUrlFile(String urlString) {
        if ((null == urlString) || ("".equals(urlString))) {
            return false;
        }
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(2000);
            connection.getInputStream();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * destroyImageView
     *
     * @param imageView
     * @Description: 释放ImageView中的图片
     */
    public static void destroyImageView(ImageView imageView) {
        if (imageView == null)
            return;
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        if (drawable == null)
            return;
        Bitmap bitmap = drawable.getBitmap();
        imageView.setImageDrawable(null);
        imageView.setImageBitmap(null);
        imageView = null;
        if ((bitmap != null) && (!bitmap.isRecycled())) {
            bitmap.recycle();
            bitmap = null;
        }
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    /**
     * zoomBitmap
     *
     * @param bitmap    图片Bitmap
     * @param maxWidth  缩放后的Bitmap的最大宽度
     * @param maxHeight 缩放后的Bitmap的最大高度
     * @return
     * @Description: 缩放图片Bitmap
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, float maxWidth, float maxHeight) {
        if (null == bitmap) {
            Log.e(TAG, "input param bitmap is null");
            return null;
        }
        if ((0 >= maxWidth) || (0 >= maxHeight)) {
            throw new IllegalArgumentException("input param maxWidth or maxHeight cann't less-than zero");
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // 原图不超大
        if ((maxWidth >= width) && (maxHeight >= height)) {
            return bitmap;
        }

        float scaleWidth = 1f;
        float scaleHeight = 1f;
        if (maxWidth < width) {// 超宽
            scaleWidth = maxWidth / (float) width;
        }

        if (maxHeight < height) {// 超高
            scaleHeight = maxHeight / (float) height;
        }
        // 为了保持图片不扭曲失真, 让高和宽的缩放比例一致
        Matrix matrix = new Matrix();
        float scale = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
        matrix.postScale(scale, scale);

        Bitmap bitmapZoom = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return bitmapZoom;
    }

    public static Bitmap CompressionImage(Bitmap image) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 80, out);
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // int be = 2;
        // newOpts.inSampleSize = be;
        ByteArrayInputStream isBm = new ByteArrayInputStream(out.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    /**
     * 读取InputStream数据，转为byte[]数据类型
     *
     * @param is InputStream数据
     * @return 返回byte[]数据
     */
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static InputStream Bitmap2IS(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.JPEG, 100, baos);
        InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
        return sbs;
    }

    private void recycleBitmap(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof ImageView) {
                    Drawable drawable = ((ImageView) view).getDrawable();
                    if (drawable != null) {
                        if (drawable instanceof BitmapDrawable) {
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                            Bitmap bitmap = bitmapDrawable.getBitmap();
                            if (bitmap != null)
                                bitmap.recycle();
                        }
                    }
                }
            }
        }
    }

    /**
     * bitmap 保存为jpg 图片
     *
     * @param mBitmap 图片源
     * @param bitName 图片名
     */
    public static  void saveMyBitmap(Context _context, Bitmap mBitmap, String bitName) {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + bitName + ".jpg");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveImageToGallery(_context, file);
    }

    /**
     * 先保存到本地再广播到图库
     */
    public static void saveImageToGallery(Context context, File _file) {

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), _file.getAbsolutePath(), "code", null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + _file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
