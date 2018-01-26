package com.hhzt.vod.api;

/**
 * Created by wujichang on 2017/12/29.
 */

public class HttpConst {

	public static final String DEFAULT_NAME = "10001";

	private static final String DEFAULT_BASE_HOST = "http://szhhzt.cn:8001";

	public static final String EPG_PATH = "/epg/router?";
	public static final String PAY_PATH = "/epg/auth/order/";

	public static final String DEFAULT_HOST = DEFAULT_BASE_HOST + EPG_PATH;
	public static final String DEFAULT_PAY_HOST = DEFAULT_BASE_HOST + PAY_PATH;

	public static final int DEFAULT_GROUP_ID = 1;

	public static final String APP_KEY = "hhzt";
	public static final String FORMAT = "json";
	public static final String VERSION = "1.0";

	public static final String VERSION_1_1 = "1.1";


	public static final String METHOD_01 = "api.program.programHome";
	public static final String METHOD_02 = "api.program.categoryList";
	public static final String METHOD_03 = "api.program.programList";
	public static final String METHOD_04 = "api.program.programDetail";
	public static final String METHOD_05 = "api.program.playRecord";
	public static final String METHOD_06 = "api.program.starRating";
	public static final String METHOD_07 = "api.program.searchFever";
	public static final String METHOD_08 = "api.program.searchHome";
	public static final String METHOD_09 = "api.program.search";
	public static final String METHOD_10 = "api.program.historicalRecord";
	public static final String METHOD_11 = "api.weather.today";
	public static final String METHOD_12 = "api.program.lookTime";
	public static final String METHOD_13 = "api.advertising.programPauseAd";
}
