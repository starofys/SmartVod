package com.hhzt.vod.smartvod.mvp.link;

import com.hhzt.vod.smartvod.mvp.model.DataModel;
import com.hhzt.vod.smartvod.mvp.model.HomeMovieRecommondModel;
import com.hhzt.vod.smartvod.mvp.model.HomeTypeListModelModel;
import com.hhzt.vod.smartvod.mvp.model.HomeTypeModel;
import com.hhzt.vod.smartvod.mvp.model.IDataTime;
import com.hhzt.vod.smartvod.mvp.model.IHomeType;
import com.hhzt.vod.smartvod.mvp.model.IHomeTypeList;
import com.hhzt.vod.smartvod.mvp.model.IMovieDetail;
import com.hhzt.vod.smartvod.mvp.model.ISearchMovie;
import com.hhzt.vod.smartvod.mvp.model.IWeather;
import com.hhzt.vod.smartvod.mvp.model.MovieDetailModel;
import com.hhzt.vod.smartvod.mvp.model.SearchMovieModel;
import com.hhzt.vod.smartvod.mvp.model.WeatherModel;

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

	public static HomeMovieRecommondModel initMovieRecommond() {
		return new HomeMovieRecommondModel();
	}

	public static IMovieDetail initMovieDetail() {
		return new MovieDetailModel();
	}

	public static ISearchMovie initSearchMovie() {
		return new SearchMovieModel();
	}

	public static IDataTime initDataTime() {
		return new DataModel();
	}

	public static IWeather initWeather() {
		return new WeatherModel();
	}
}
