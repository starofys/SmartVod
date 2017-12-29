package com.hhzt.vod.smartvod;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhzt.vod.logiclayer.FragmentUtil;

import org.xutils.view.annotation.ContentView;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.activity_movie_detail)
public class MovieDetailActivity extends BaseActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
		FragmentUtil.replace(this, false, R.id.movie_detail_fragment_container, movieDetailFragment);
	}
}
