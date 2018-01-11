package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.WeatherCityBean;

/**
 * 天气
 * Created by zengxiaoping on 2018/1/11.
 *
 * @Author zengxiaoping
 */

public interface IWeather {

	void requestWeather(IHttpRetCallBack<WeatherCityBean> iHttpRetCallBack);
}
