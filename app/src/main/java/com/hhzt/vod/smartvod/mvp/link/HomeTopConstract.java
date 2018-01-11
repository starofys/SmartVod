package com.hhzt.vod.smartvod.mvp.link;

import com.hhzt.vod.smartvod.mvp.iview.BaseView;
import com.hhzt.vod.smartvod.mvp.presenter.BasePresenter;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @Author zengxiaoping
 */

public class HomeTopConstract {

	public interface HomeTopView extends BaseView<HomeTopPresenter> {
		void showCurrentTime(String currentTime);

		void showDataTime(String dataTime);

		void showWeek(String week);

		void showWeather(String weatherLogUrl, String temp, String city);
	}

	public interface HomeTopPresenter extends BasePresenter {
		void init();

		void showCurrentTime();

		void showDataTime();

		void showWeek();

		void showWeather();

		void destoryInit();
	}
}
