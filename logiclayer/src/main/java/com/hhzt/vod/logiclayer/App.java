package com.hhzt.vod.logiclayer;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by wujichang on 2017/12/28.
 */

public class App extends Application {

	public static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();

		mContext = this;

		x.Ext.init(this);
		x.Ext.setDebug(com.hhzt.vod.logiclayer.BuildConfig.DEBUG);
	}
}
