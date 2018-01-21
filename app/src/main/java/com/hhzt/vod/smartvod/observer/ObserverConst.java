package com.hhzt.vod.smartvod.observer;

/**
 * Created by zengxiaoping on 2018/1/21.
 *
 * @Author zengxiaoping
 */

public class ObserverConst {
	public static final int BASE_MOVIE_PAGE = 1000;
	public static final int CODE_MOVIE_CURRENT_PAGE = BASE_MOVIE_PAGE + 1;      //当前页数
	public static final int CODE_MOVIE_TOTAL_PAGE = BASE_MOVIE_PAGE + 2;        //总页数
	public static final int CODE_MOVIE_SHOW_OR_HINT_PAGE = BASE_MOVIE_PAGE + 3; //显示隐藏当前处在页面

	public static final int BASE_MOVIE_TYPE = 2000;
	public static final int CODE_MOVIE_TYPE_SHOW_OR_HINT = BASE_MOVIE_TYPE + 1; //类型显示隐藏
	public static final int CODE_MOVIE_TYPE_TRANSLATE = BASE_MOVIE_TYPE + 2;    //是否开启动画
}
