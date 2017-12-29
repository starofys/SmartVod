package com.hhzt.vod.logiclayer;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wujichang on 2017/12/28.
 */

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		x.Ext.init(this);
		x.Ext.setDebug(com.hhzt.vod.logiclayer.BuildConfig.DEBUG);
	}
}
