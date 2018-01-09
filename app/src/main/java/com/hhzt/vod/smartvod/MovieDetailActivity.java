package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhzt.vod.media.NiceVideoPlayerManager;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
import com.hhzt.vod.smartvod.utils.FragmentUtil;

import org.xutils.view.annotation.ContentView;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.activity_movie_detail)
public class MovieDetailActivity extends BaseActivity implements MovieDetailCallBack {

	public static final String MOVIE_TYPE_ID = "movie_type_id";
	public static final String MOVIE_DETAIL_ID = "movie_Detail_id";

	//电影类型id、具体电影id
	//mMovieTypeId<==>programId
	//mMovieDetailId<==>categoryId
	private int mMovieTypeId;
	private int mMovieDetailId;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		mMovieTypeId = intent.getIntExtra(MOVIE_TYPE_ID, 0);
		mMovieDetailId = intent.getIntExtra(MOVIE_DETAIL_ID, 0);

		showFragment();
	}

	private void showFragment() {
		MovieDetailFragment movieDetailFragment = MovieDetailFragment.getInstance(mMovieTypeId, mMovieDetailId);
		FragmentUtil.replace(this, false, R.id.movie_detail_fragment_container, movieDetailFragment);
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
	public void showMovieDetailCallBack(int code, int programId, int categoryId) {
		mMovieTypeId = programId;
		mMovieDetailId = categoryId;
		showFragment();
	}
}
