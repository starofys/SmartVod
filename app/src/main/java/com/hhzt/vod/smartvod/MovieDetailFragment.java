package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhzt.vod.smartvod.iview.IMovieDetail;

import org.xutils.view.annotation.ContentView;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_movie_detail)
public class MovieDetailFragment extends BaseFragment implements IMovieDetail{

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void showSmallVideo() {

	}

	@Override
	public void showMovieDetail() {

	}

	@Override
	public void showMovieItemTips() {

	}

	@Override
	public void showMovieRecommend() {

	}
}
