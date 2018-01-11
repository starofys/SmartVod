package com.hhzt.vod.smartvod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhzt.vod.smartvod.mvp.link.HomeTopConstract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeTopLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Johnson on 2017/12/29.
 */
@ContentView(R.layout.fragment_top)
public class HomeTopFragment extends BaseFragment implements HomeTopConstract.HomeTopView {

	@ViewInject(R.id.tv_current_time)
	private TextView mTvCurrentTime;
	@ViewInject(R.id.tv_data)
	private TextView mTvData;
	@ViewInject(R.id.tv_week)
	private TextView mTvWeek;

	@ViewInject(R.id.iv_climate_icon)
	private ImageView mIvClimateIcon;
	@ViewInject(R.id.tv_temperature)
	private TextView mTvTemperature;
	@ViewInject(R.id.tv_city)
	private TextView mTvCity;

	@ViewInject(R.id.ll_search)
	private ReflectItemView mLlSearch;

	private HomeTopConstract.HomeTopPresenter mHomeTopPresenter;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mHomeTopPresenter = new HomeTopLinkPresenter(context, InJection.initDataTime(), InJection.initWeather(), this);
		mHomeTopPresenter.start();
		mHomeTopPresenter.init();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mHomeTopPresenter.showCurrentTime();
		mHomeTopPresenter.showDataTime();
		mHomeTopPresenter.showWeek();
		mHomeTopPresenter.showWeather();
		initEvent();
	}

	@Override
	public void onResume() {
		super.onResume();
		mHomeTopPresenter.showWeather();
	}

	private void initEvent() {
		mLlSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void setPresenter(HomeTopConstract.HomeTopPresenter presenter) {
		mHomeTopPresenter = presenter;
	}

	@Override
	public void showCurrentTime(String currentTime) {
		mTvCurrentTime.setText(currentTime);
	}

	@Override
	public void showDataTime(String dataTime) {
		mTvData.setText(dataTime);
	}

	@Override
	public void showWeek(String week) {
		mTvWeek.setText(week);
	}

	@Override
	public void showWeather(String weatherLogUrl, String temp, String city) {
		Glide.with(getActivity())
				.load(weatherLogUrl)
				.placeholder(R.drawable.img_default)
				.crossFade()
				.into(mIvClimateIcon);
		mTvTemperature.setText(temp);
		mTvCity.setText(city);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHomeTopPresenter.destoryInit();
	}
}
