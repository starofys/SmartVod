package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.hhzt.vod.media.NiceVideoPlayerManager;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.utils.FragmentUtil;

import org.xutils.view.annotation.ContentView;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.activity_movie_detail)
public class MovieDetailActivity extends BaseActivity implements MovieDetailCallBack {

    public static final String MOVIE_NEED_PAY_TAG = "movie_need_pay_tag";
    public static final String MOVIE_CATEGORY_ID = "movie_category_id";
    public static final String MOVIE_PROGRAM_ID = "movie_program_id";

    //电影类型id、具体电影id
    private boolean mMovieVipFlag;
    private int mMovieCategoryId;
    private int mMovieProgramId;

    private MovieDetailFragment mMovieDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMovieVipFlag = intent.getIntExtra(MOVIE_NEED_PAY_TAG, 0) == ConfigX.NEED_VIP;
        mMovieCategoryId = intent.getIntExtra(MOVIE_CATEGORY_ID, 0);
        mMovieProgramId = intent.getIntExtra(MOVIE_PROGRAM_ID, 0);

        showFragment();
    }

    private void showFragment() {
        mMovieDetailFragment = MovieDetailFragment.getInstance(mMovieCategoryId, mMovieProgramId, mMovieVipFlag);
        FragmentUtil.replace(this, false, R.id.movie_detail_fragment_container, mMovieDetailFragment);
    }

    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void showMovieDetailCallBack(int code, int categoryId, int programId) {
        mMovieCategoryId = categoryId;
        mMovieProgramId = programId;
        showFragment();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer().isFullScreen()) {
            return mMovieDetailFragment.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
