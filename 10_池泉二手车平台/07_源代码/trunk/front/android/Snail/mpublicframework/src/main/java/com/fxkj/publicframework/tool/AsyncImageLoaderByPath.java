package com.fxkj.publicframework.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.Message;

import com.fxkj.publicframework.globalvariable.Constant;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class AsyncImageLoaderByPath {
    // SoftReference是软引用，是为了更好的为了系统回收变量
    private HashMap<String, SoftReference<Bitmap>> imageCache;
    //private int Degree;

    public AsyncImageLoaderByPath() {
        this.imageCache = new HashMap<String, SoftReference<Bitmap>>();
    }

    public HashMap<String, SoftReference<Bitmap>> getImageCache() {
        return imageCache;
    }

    public Bitmap getBitmapFromCache(String imagePath, String key) {
        if (key != null) {
            if (imageCache.containsKey(key)) {
                // 从缓存中获取
                SoftReference<Bitmap> softReference = imageCache.get(key);
                Bitmap bitmap = softReference.get();
                if ((bitmap != null) && (!bitmap.isRecycled())) {
                    return bitmap;
                }
            }
        }
        if (imagePath != null) {
            try {
                imageCache.put(key, new SoftReference<Bitmap>(BitmapFactory.decodeFile(imagePath)));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
        if (imageCache.containsKey(key)) {
            return getBitmapFromCache(null, key);
        } else {
            return null;
        }
    }

    public void putBitmapToCache(String imagePath, String key, Bitmap bitmap) {
        try {
            if (bitmap != null) {
                //imageCache.put(key, new SoftReference<Bitmap>(bitmap));
                ImageFunction.saveBitmapToPath(imagePath, bitmap, Bitmap.CompressFormat.JPEG, 80);
            }
        } catch (Exception e) {
        }

    }

    private boolean isFile(String imagePath) {
        if (imagePath == null) {
            return false;
        }
        File file = new File(imagePath);
        if (file.isDirectory()) {
            return false;
        }
        if (!file.isFile()) {
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    public void loadBitmapByPath(final String imagePath, final String key, final ImageCallback imageCallback, final int degree) {
        if (imagePath == null) {
            return;
        }
        if (!isFile(imagePath)) {
            return;
        }
        //Degree = readPicDegree(imagePath);
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                if (imageCallback != null) {
                    imageCallback.imageLoaded((Bitmap) message.obj, Constant.project_root_folder + key);
                }
            }
        };
        // 建立新一个获取SD卡的图片
        new Thread() {
            @Override
            public void run() {
                byte[] data = ImageFunction.decodeBitmap(imagePath);
                if (data != null) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        if (degree != 0) {
                            bitmap = rotateBitmap(degree, bitmap);
                        }
                        // imageCache.put(key, new
                        // SoftReference<Bitmap>(bitmap));
                        putBitmapToCache(Constant.project_root_folder + key, key, bitmap);
                        Message message = handler.obtainMessage(0, bitmap);
                        handler.sendMessage(message);
                    } catch (OutOfMemoryError e) {
                    } catch (Exception e) {
                    }
                } else {
                    Message message = handler.obtainMessage(0, null);
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    // 回调接口
    public interface ImageCallback {
        public void imageLoaded(Bitmap imageBitmap, String imagePath);
    }

    /**
     * 通过ExifInterface类读取图片文件的被旋转角度
     *
     * @param path ： 图片文件的路径
     * @return 图片文件的被旋转角度
     */
    public static int readPicDegree(String path) {
        int degree = 0;

        // 读取图片文件信息的类ExifInterface
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        }

        return degree;
    }

    /**
     * 将图片纠正到正确方向
     *
     * @param degree ： 图片被系统旋转的角度
     * @param bitmap ： 需纠正方向的图片
     * @return 纠向后的图片
     */
    public static Bitmap rotateBitmap(int degree, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        bitmap = null;
        return bm;
    }
}
