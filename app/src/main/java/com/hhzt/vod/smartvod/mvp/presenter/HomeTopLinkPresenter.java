package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.WeatherCityBean;
import com.hhzt.vod.smartvod.mvp.link.HomeTopConstract;
import com.hhzt.vod.smartvod.mvp.model.IDataTime;
import com.hhzt.vod.smartvod.mvp.model.IWeather;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @Author zengxiaoping
 */

public class HomeTopLinkPresenter implements HomeTopConstract.HomeTopPresenter {
	private Context mContext;
	private IDataTime mIDataTime;
	private IWeather mIWeather;
	private HomeTopConstract.HomeTopView mIHomeTopView;

	public HomeTopLinkPresenter(Context context, IDataTime IDataTime, IWeather IWeather, HomeTopConstract.HomeTopView IHomeTopView) {
		mContext = context;
		mIDataTime = IDataTime;
		mIWeather = IWeather;
		mIHomeTopView = IHomeTopView;

		mIHomeTopView.setPresenter(this);
	}

	@Override
	public void start() {

	}

	@Override
	public void init() {
		//todo 开一个时间得监听   去设置时间等参数
	}

	@Override
	public void showCurrentTime() {
		String currentTime = mIDataTime.requestCurreuntTime();
		mIHomeTopView.showCurrentTime(currentTime);
	}

	@Override
	public void showDataTime() {
		String datatTime = mIDataTime.requestDataTime();
		mIHomeTopView.showDataTime(datatTime);
	}

	@Override
	public void showWeek() {
		String week = mIDataTime.requestWeek();
		mIHomeTopView.showWeek(week);
	}

	@Override
	public void showWeather() {
		mIWeather.requestWeather(new IHttpRetCallBack<WeatherCityBean>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, WeatherCityBean weatherCityBean) {
				WeatherCityBean.WeatherBean weather = weatherCityBean.getWeather();
				mIHomeTopView.showWeather(weather.getLogourl(), weather.getTemp(), weather.getCityname() + weather.getWeather());
			}

			@Override
			public void onResponseFailed(CommonRspRetBean bean) {

			}

			@Override
			public void onError(String result) {

			}

			@Override
			public void onCancelled() {

			}

			@Override
			public void onFinish() {

			}
		});
	}

	@Override
	public void destoryInit() {
		//todo 删除监听
	}
}
