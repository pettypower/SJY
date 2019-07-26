package com.fxkj.publicframework.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fxkj.publicframework.R;
import com.fxkj.publicframework.widget.photoview.EasePhotoView;
import com.fxkj.publicframework.widget.photoview.PhotoViewAttacher;

public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private EasePhotoView mImageView;
    private ProgressBar progressBar;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);
        return f;
    }

    /*    com.bumptech.glide.load.engine.GlideException: Failed to load resource
        There were 9 causes:
                java.io.IOException(Exception decoding bitmap, outWidth: 1080, outHeight: 1920, outMimeType: image/jpeg, inBitmap: [1080x1920] ARGB_8888 (8294400))
                com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream$InvalidMarkException(Mark has been invalidated, pos: 65536 markLimit: 65536)
                java.io.IOException(java.lang.RuntimeException: setDataSource failed: status = 0x80000000)
                java.io.IOException(java.lang.RuntimeException: setDataSource failed: status = 0x80000000)
                java.io.IOException(Exception decoding bitmap, outWidth: 1080, outHeight: 1920, outMimeType: image/jpeg, inBitmap: [1080x1920] ARGB_8888 (8294400))
                com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream$InvalidMarkException(Mark has been invalidated, pos: 65536 markLimit: 65536)
                java.io.IOException(java.lang.RuntimeException: setDataSource failed: status = 0x80000000)
                java.io.IOException(java.lang.RuntimeException: setDataSource failed: status = 0x80000000)
                java.io.FileNotFoundException(No content provider: http://220.175.137.216:8080/lmyh-main/a.up?type=viewFile&id=2c9191ed6519ba9701651cbf9bd70033)
        call GlideException#logRootCauses(String) for more detail*/
    @Override
    public void onResume() {
        super.onResume();

//        RequestOptions options = new RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//
//        //此处修改是因为Glide版本修改为4.5.0
//        Glide.with(getContext()).load(mImageUrl).apply(options).listener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                progressBar.setVisibility(View.GONE);
//                return false;
//            }
//        }).into(mImageView);

        Glide.with(getContext()).load(mImageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(mImageView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        mImageView = (EasePhotoView) v.findViewById(R.id.image);
        mImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                getActivity().finish();
            }
        });
        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

}
