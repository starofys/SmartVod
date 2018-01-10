package com.hhzt.vod.smartvod.mvp.link;

import com.hhzt.vod.smartvod.mvp.model.HomeTypeListModelModel;
import com.hhzt.vod.smartvod.mvp.model.HomeTypeModel;
import com.hhzt.vod.smartvod.mvp.model.IHomeType;
import com.hhzt.vod.smartvod.mvp.model.IHomeTypeList;
import com.hhzt.vod.smartvod.mvp.model.IMovieDetail;
import com.hhzt.vod.smartvod.mvp.model.ISearchMovie;
import com.hhzt.vod.smartvod.mvp.model.MovieDetailModel;
import com.hhzt.vod.smartvod.mvp.model.SearchMovieModel;

/**
 * Created by Johnson on 2017/12/29.
 */

public class InJection {
	public static IHomeType initHomeType() {
		return new HomeTypeModel();
	}

	public static IHomeTypeList initHomeTypeList() {
		return new HomeTypeListModelModel();
	}

	public static IMovieDetail initMovieDetail() {
		return new MovieDetailModel();
	}

	public static ISearchMovie initSearchMovie() {
		return new SearchMovieModel();
	}
}
