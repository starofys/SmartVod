package com.hhzt.vod.smartvod.adapter;

import android.content.Context;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.R;

import java.util.List;

/**
 * 大图
 */
public class HomeBigPicturePresenter extends BasePicturePresenter {

	public HomeBigPicturePresenter(Context context, List<MovieInfoData> mMovieInfoList) {
		super(context, mMovieInfoList);
	}

	@Override
	int getLayoutId() {
		return R.layout.item_movie_type_big_picture_fragment;
	}
}
