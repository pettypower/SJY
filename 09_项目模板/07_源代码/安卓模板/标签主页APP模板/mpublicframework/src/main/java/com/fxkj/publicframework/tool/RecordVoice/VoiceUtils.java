package com.fxkj.publicframework.tool.RecordVoice;

import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by wxmij on 2017/3/29.
 */

public class VoiceUtils {
    private final String TAG = VoiceUtils.class.getName();
    private String path;

    private MediaPlayer mPlayer;

    public MediaPlayer getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(MediaPlayer mPlayer) {
        this.mPlayer = mPlayer;
    }
    private VoiceUtils() {
//        this.path = path;
        setMPlay();
    }
    public void setMPlay() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }
//    public VoiceUtils(String path) {
//        this.path = path;
//        mPlayer = new MediaPlayer();
//        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.start();
//            }
//        });
//    }
    private NotifyDataInterface notifyDataInterface;
    private int index;
    public interface NotifyDataInterface{
        void notify(int index);
    }
    private static VoiceUtils voiceUtils;
    public static VoiceUtils getInstance(NotifyDataInterface notifyDataInterface,int index){
        if (voiceUtils==null){
            synchronized (VoiceUtils.class){
                if (voiceUtils==null){
                    voiceUtils = new VoiceUtils();
                }
            }
        }
        if (voiceUtils.notifyDataInterface!=null){
            voiceUtils.notifyDataInterface.notify(voiceUtils.index);
        }

        voiceUtils.setIndex(index);
        voiceUtils.setNotifyDataInterface(notifyDataInterface);
        return voiceUtils;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public void setNotifyDataInterface(NotifyDataInterface notifyDataInterface) {
        this.notifyDataInterface = notifyDataInterface;
    }
    public boolean start() {
        try {
            //设置要播放的文件
            mPlayer.setDataSource(path);
            mPlayer.prepareAsync();
            //播放
           // mPlayer.start();
        } catch (Exception e) {
            Log.e(TAG, "prepare() failed");
        }

        return false;
    }

    public boolean stop() {
        if (mPlayer!=null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        return false;
    }
    public boolean isPlaying(){
        if (mPlayer!=null){
            return mPlayer.isPlaying();
        }
        return false;
    }
    public void setPath(String path){
        this.path = path;
    }
}
