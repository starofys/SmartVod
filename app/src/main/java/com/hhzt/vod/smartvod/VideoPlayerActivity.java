package com.hhzt.vod.smartvod;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hhzt.vod.logiclayer.App;
import com.hhzt.vod.viewlayer.media.VideoControllerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.activity_video_player)
public class VideoPlayerActivity extends BaseActivity implements SurfaceHolder.Callback, VideoControllerView.MediaPlayerControl,
        View.OnClickListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnErrorListener {

    private static final String TAG_MEDIA = "smartVod_Mdia";

    @ViewInject(R.id.videoSurface)
    private SurfaceView videoSurface;
    @ViewInject(R.id.videoSurfaceContainer)
    private FrameLayout videoSurfaceContainer;

    private MediaPlayer player;
    private VideoControllerView controller;

    private boolean isFullScrenen;
    private Display currDisplay;
    private int size = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currDisplay = this.getWindowManager().getDefaultDisplay();
        String videoPath = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(videoPath)) {
            Toast.makeText(App.mContext, getText(R.string.media_url_error_tips), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        videoSurfaceContainer.setOnClickListener(this);
        SurfaceHolder videoHolder = videoSurface.getHolder();

        videoHolder.addCallback(this);

        player = new MediaPlayer();
        controller = new VideoControllerView(this);

        try {
            player.reset();
            player.setOnBufferingUpdateListener(this);
            player.setOnCompletionListener(this);
            player.setOnErrorListener(this);
            player.setOnVideoSizeChangedListener(this);
            player.setOnSeekCompleteListener(this);
            player.setOnPreparedListener(this);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(this, Uri.parse(videoPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player.setDisplay(holder);//为MediaPlayer设置播放的区域
        player.prepareAsync();//准备播放
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initStartPlayer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int position = player.getCurrentPosition();
        outState.putInt("position", position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int position = savedInstanceState.getInt("position");
        if (position != 0) {
            player.seekTo(position);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        initStartPlayer();
    }

    private void initStartPlayer() {
        int vWidth = player.getVideoWidth();
        int vHeight = player.getVideoHeight();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int windowWidth = dm.widthPixels;
        int windowHeight = dm.heightPixels; //如果video的宽或者高超出了当前屏幕的大小，则要进行缩
        float wRatio = (float) vWidth / (float) currDisplay.getWidth();
        float hRatio = (float) vHeight / (float) currDisplay.getHeight(); //选择大的一个进行缩放
        float ratio = Math.max(wRatio, hRatio);
        vWidth = (int) Math.ceil((float) vWidth / ratio);
        vHeight = (int) Math.ceil((float) vHeight / ratio); //设置surfaceView的布局参数
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(vWidth, vHeight);
        lp.setMargins((windowWidth - vWidth) / 2, 0, 0, 0);
        videoSurface.setLayoutParams(lp);
        controller.setMediaPlayer(this);//为VideoController设置MediaPlayer
        controller.setAnchorView(videoSurfaceContainer);
        controller.show();

        player.start();
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return player.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return player.getDuration();
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public void pause() {
        player.pause();
    }

    @Override
    public void seekTo(int i) {
        player.seekTo(i);
    }

    @Override
    public void start() {
        player.start();
    }

    @Override
    public boolean isFullScreen() {
        int orientation = this.getRequestedOrientation();
        isFullScrenen = (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        return isFullScrenen;
    }

    @Override
    public void toggleFullScreen() {
        if (isFullScrenen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.videoSurfaceContainer:
                //控制底部控制条的显示和隐藏
                if (size % 2 == 1) {
                    controller.show();
                } else {
                    controller.hide();
                }
                size++;
                break;
            default:
                break;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.i(TAG_MEDIA, "onBufferingUpdate");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG_MEDIA, "onCompletion");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i(TAG_MEDIA, "onError");
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        Log.i(TAG_MEDIA, "onSeekComplete");
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.i(TAG_MEDIA, "onVideoSizeChanged");
    }
}
