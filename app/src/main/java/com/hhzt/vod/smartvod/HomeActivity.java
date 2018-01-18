package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.utils.FragmentUtil;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class HomeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initConfig();

		HomeTopFragment homeTopFragment = new HomeTopFragment();
		HomeContentFragment homeContentFragment = new HomeContentFragment();
		FragmentUtil.replace(this, false, R.id.home_top_fragment_container, homeTopFragment);
		FragmentUtil.replace(this, false, R.id.home_content_fragment_container, homeContentFragment);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		initConfig();
	}

	private void initConfig() {
		Intent intent = getIntent();
		if (null != intent) {
			String host = intent.getStringExtra(ConfigX.HOST);
			String userName = intent.getStringExtra(ConfigX.USERNAME);
			int programProupID = intent.getIntExtra(ConfigX.GROUPID, HttpConst.DEFAULT_GROUP_ID);

			ConfigMgr.getInstance().initEpgUrl(host);
			ConfigMgr.getInstance().initUserName(userName);
			ConfigMgr.getInstance().initGroupID(programProupID);

			Log.d(ConfigX.HHZT_SMART_LOG, "host=" + host + ";userName=" + userName + ";programProupID=" + programProupID);
		}
	}
}
