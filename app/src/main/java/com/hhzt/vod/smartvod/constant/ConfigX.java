package com.hhzt.vod.smartvod.constant;

/**
 * Created by wujichang on 2018/1/7.
 */

public class ConfigX {

	public static final String HHZT_SMART_LOG = "HHZT_SMART_VOD";

	public static final String HOST = "host";

	public static final String USERNAME = "userName";

	public static final String GROUPID = "programGroupID";

	//图片选中缩放大小
	public static final float SCALE = 1.1f;

	//电视剧默认可以看集数
	public static final int FREE_SERIES_NUMBER = 3;

	// 免费
	public static final int FREE = 0;
	// 付费
	public static final int NEED_VIP = 1;

	// 媒体类型--供支付type使用
	public static final int MEDIA_TYPE_LIVE = 1;//直播
	public static final int MEDIA_TYPE_VOD = 2;//点播
	public static final int MEDIA_TYPE_PACAGE = 3;// 套餐
	public static final int MEDIA_TYPE_MUSIC = 4;//音乐

	// 计费方式---直接计费
	public static final int MEDIA_PAY_PATH_DIRECT = 100;
	// 计费方式---试看时间结束计费
	public static final int MEDIA_PAY_PATH_LIMIT = 101;

	//计费鉴权成功
	public static final String PAY_FAILED = "0";
	//计费鉴权失败
	public static final String PAY_SUCCESSED = "1";
}
