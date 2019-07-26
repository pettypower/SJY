package com.fxkj.publicframework.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fxkj.publicframework.R;
import com.fxkj.publicframework.beans.CallBackObject;
import com.fxkj.publicframework.dialog.BaseFragmentClick;
import com.fxkj.publicframework.dialog.LoadingDialog;
import com.fxkj.publicframework.globalvariable.Constant;
import com.fxkj.publicframework.requestdata.CallBack;
import com.fxkj.publicframework.tool.AsyncImageLoaderByPath;
import com.fxkj.publicframework.tool.FileUtils;
import com.fxkj.publicframework.tool.ImageFunction;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class BaseTitleActivity extends AppCompatActivity implements View.OnClickListener, CallBack, BaseFragmentClick {
    protected ImageView title_do2;
    protected TextView title_do;
    protected TextView tv_back;
    protected TextView title_text;
    //    protected RelativeLayout title_layout;
    protected RelativeLayout title_main_layout;
    protected LinearLayout main_layout;
    protected Context context;
    protected LayoutInflater inflater;
    protected LoadingDialog mProgressDialog;
    protected static final int CAMERA_REQUEST_CODE = 101;
    protected static final int GALLERY_REQUEST_CODE = 102;
    protected ArrayList<String> imagelist = new ArrayList<>();
    public File mTmpFile;
    private long time = 0;
    protected ImageView base_title_do3;
    protected boolean isBack = true;
    public boolean isActive;
    protected Context mContext;

    public void setBack(boolean back) {
        isBack = back;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityManagers.getInstance().pushStack(this);
        super.onCreate(savedInstanceState);
        mContext = this;
        overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        setContentView(R.layout.activity_base);
        context = this;
        inflater = LayoutInflater.from(this);
        time = System.currentTimeMillis();
        initView();
        initData();
        initListener();
        loadData();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
        ActivityManagers.getInstance().popStack(this);
    }

    protected void loadData() {
    }

    protected int getLayoutId() {
        return 0;
    }

    protected void initView() {
        main_layout = (LinearLayout) findViewById(R.id.base_main_layout);

        title_main_layout = (RelativeLayout) findViewById(R.id.base_title_main_layout);
//        title_layout = (RelativeLayout) findViewById(R.id.base_title_layout);
        title_text = (TextView) findViewById(R.id.base_title_text);
        title_do = (TextView) findViewById(R.id.base_title_do);
        title_do2 = (ImageView) findViewById(R.id.base_title_do2);
        base_title_do3 = (ImageView) findViewById(R.id.base_title_do3);
        tv_back = (TextView) findViewById(R.id.tv_back);

        View viewRoot = inflater.inflate(getLayoutId(), main_layout, false);
        ButterKnife.bind(this, viewRoot);
        main_layout.addView(viewRoot);


    }


    protected void initData() {
        mProgressDialog = new LoadingDialog(this);
        mProgressDialog.setCancelable(true);
    }

    protected void initListener() {
        tv_back.setOnClickListener(this);
        title_do.setOnClickListener(this);
        base_title_do3.setOnClickListener(this);
    }

    /**
     * 显示替换fragment
     */
    protected void showFragment(Fragment fg, int contaner) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!fg.isAdded()) {
            transaction.replace(contaner, fg);
        } else {
            transaction.show(fg);
        }
        transaction.commitAllowingStateLoss();
        // 重置导航选中状态
    }

    protected void showDialog(String msg) {
        mProgressDialog.setMessage(msg);
        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    protected void showDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }


    @Override
    public void onClick(View view) {
//        if ((System.currentTimeMillis() - time < 1200)) {
//            // Toast.makeText(this, "请不要频繁点击", Toast.LENGTH_SHORT).show();
//            time = System.currentTimeMillis();
//            return;
//        }
        int i = view.getId();
        if (i == R.id.tv_back) {
            if (isBack) {
                finish();
                hideSoftInputFromWindow();
            }

        }
    }

    protected View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText textView = (EditText) v;
            String hint;
            if (hasFocus) {
                hint = textView.getHint().toString();
                textView.setTag(hint);
                textView.setHint("");
            } else {
                hint = textView.getTag().toString();
                textView.setHint(hint);
            }
        }
    };

    protected void hideSoftInputFromWindow() {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View curentView = getCurrentFocus();
        if (im != null && curentView != null) {
            IBinder iBinder = getCurrentFocus().getApplicationWindowToken();
            im.hideSoftInputFromWindow(iBinder,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    @Override
    public void onSuccess(int flag, CallBackObject obj) throws ParseException {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void onFailure(int flag, String message) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void onNoData(int flag) {

    }

    protected void startActivity(Class<? extends BaseTitleActivity> targetClass) {
        mContext.startActivity(new Intent(this, targetClass));
    }

    public void startActivity(Bundle b, Class c) {
        Intent it = new Intent(this, c);
        it.putExtra("bundle", b);
        startActivity(it);
    }

    public void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            try {
                mTmpFile = FileUtils.createTmpFile(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (mTmpFile != null && mTmpFile.exists()) {
                /*获取当前系统的android版本号*/
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion < 24) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                } else {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, mTmpFile.getAbsolutePath());
                    Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            } else {
                Toast.makeText(this, "图片错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "没有系统相机", Toast.LENGTH_SHORT).show();
        }
    }

    protected void callBackImage(String imagePath) {

    }

    ///storage/emulated/0/lmyh/IMG_-826174252.jpg
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case CAMERA_REQUEST_CODE:

//                    new AsyncImageLoaderByPath().loadBitmapByPath(mTmpFile.getAbsolutePath(), mTmpFile.getName(), new AsyncImageLoaderByPath.ImageCallback() {
//                        @Override
//                        public void imageLoaded(Bitmap imageBitmap, String imagePath) {
//                            callBackImage(imagePath);
//                        }
//                    }, 0);
                    showDialog("正在提交...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            byte[] dataArr = null;
                            do {
                                dataArr = ImageFunction.decodeBitmap(mTmpFile.getAbsolutePath());
                            } while (dataArr == null);
                            try {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(dataArr, 0, dataArr.length);
                                putBitmapToCache(Constant.project_root_folder + mTmpFile.getName(), mTmpFile.getName(), bitmap);
                                handler.obtainMessage().sendToTarget();
                            } catch (OutOfMemoryError e) {
                            } catch (Exception e) {
                            }
                        }
                    }).start();


                    break;
                case GALLERY_REQUEST_CODE:
                    try {
                        ContentResolver resolver = getContentResolver();
                        Uri originalUri = data.getData();  //获得图片的uri
                        Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        try {
                            mTmpFile = FileUtils.createTmpFile(this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageBitmap = ImageFunction.zoomBitmap(imageBitmap, 1920, 1920);
                        ImageFunction.saveBitmapToPath(mTmpFile.getAbsolutePath(), imageBitmap, Bitmap.CompressFormat.JPEG, 80);
                        callBackImage(mTmpFile.getAbsolutePath());
                        //显得到bitmap图片
                    } catch (IOException e) {
                        Log.e("wxmijl", e.toString());
                    } catch (OutOfMemoryError e) {
                        Log.e("wxmijl", e.toString());
                    }
                    break;
            }
        } else {
            callBackImage(null);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            hideDialog();
            callBackImage(Constant.project_root_folder + mTmpFile.getName());
        }
    };

    public void putBitmapToCache(String imagePath, String key, Bitmap bitmap) {
        try {
            if (bitmap != null) {
                //imageCache.put(key, new SoftReference<Bitmap>(bitmap));
                ImageFunction.saveBitmapToPath(imagePath, bitmap, Bitmap.CompressFormat.JPEG, 80);
            }
        } catch (Exception e) {
        }

    }

    /**
     * 弹出 toast
     *
     * @param content
     */
    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fClick(int id) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagers.getInstance().popStack(this);
    }


}
