package com.hhzt.vod.smartvod;

import android.os.Bundle;

import com.hhzt.vod.logiclayer.FragmentUtil;
import com.hhzt.vod.smartvod.iview.IHomeViewer;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class HomeActivity extends BaseActivity implements IHomeViewer {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		HomeTopFragment homeTopFragment = new HomeTopFragment();
		HomeContentFragment homeContentFragment = new HomeContentFragment();
		FragmentUtil.replace(this, false, R.id.home_top_fragment_container, homeTopFragment);
		FragmentUtil.replace(this, false, R.id.home_content_fragment_container, homeContentFragment);
	}

	@Override
	public void showTypeList() {

	}

	@Override
	public void showTopInfo() {

	}

	@Override
	public void showContent(int type) {

	}
	
}
