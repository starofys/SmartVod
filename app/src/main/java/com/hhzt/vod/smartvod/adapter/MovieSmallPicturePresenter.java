package com.hhzt.vod.smartvod.adapter;

import android.content.Context;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.R;

import java.util.List;

/**
 * 小图
 * Created by zengxiaoping on 2018/1/11.
 */
public class MovieSmallPicturePresenter extends BasePicturePresenter {

	public MovieSmallPicturePresenter(Context context, List<MovieInfoData> mMovieInfoList) {
		super(context, mMovieInfoList);
	}

	@Override
	int getLayoutId() {
		return R.layout.item_movie_small_picture_fragment;
	}

}
