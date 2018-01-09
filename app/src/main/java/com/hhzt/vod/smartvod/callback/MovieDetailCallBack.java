package com.hhzt.vod.smartvod.callback;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public interface MovieDetailCallBack {
	/**
	 * @param code
	 * @param programId  电影类型id==>mMovieTypeId
	 * @param categoryId 具体电影id==>mMovieDetailId
	 */
	void showMovieDetailCallBack(int code, int programId, int categoryId);
}
