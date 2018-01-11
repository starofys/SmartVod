package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.api.HttpVod;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.WeatherCityBean;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class WeatherModel implements IWeather {
	@Override
	public void requestWeather(IHttpRetCallBack<WeatherCityBean> iHttpRetCallBack) {
		HttpVod.getWeather(HttpConst.VERSION,
				HttpConst.METHOD_11,
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				"",
				WeatherCityBean.class,
				iHttpRetCallBack);
	}
}
