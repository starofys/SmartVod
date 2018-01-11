package com.hhzt.vod.smartvod.adapter;

import android.content.Context;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.R;

import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @Author zengxiaoping
 */
public class SearchMoviePicturePresenter extends BasePicturePresenter {

	public SearchMoviePicturePresenter(Context context, List<MovieInfoData> mMovieInfoList) {
		super(context, mMovieInfoList);
	}

	@Override
	int getLayoutId() {
		return R.layout.item_search_movie_picture_fragment;
	}
}
