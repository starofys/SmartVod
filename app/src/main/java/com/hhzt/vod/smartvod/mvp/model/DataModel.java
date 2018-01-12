package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.smartvod.utils.ComTimeUtils;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class DataModel implements IDataTime {
	@Override
	public String requestCurreuntTime() {
		return ComTimeUtils.getTodayTime();
	}

	@Override
	public String requestDataTime() {
		return ComTimeUtils.getTodayDate();
	}

	@Override
	public String requestWeek() {
		return ComTimeUtils.getTodayWeek();
	}
}
