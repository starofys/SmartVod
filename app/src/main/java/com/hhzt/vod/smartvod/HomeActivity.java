package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;

import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.iview.IHomeViewer;
import com.hhzt.vod.smartvod.utils.FragmentUtil;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class HomeActivity extends BaseActivity implements IHomeViewer {

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

    @Override
    public void showTypeList() {

    }

    @Override
    public void showTopInfo() {

    }

    @Override
    public void showContent(int type) {

    }

    private void initConfig() {
        Intent intent = getIntent();
        if (null != intent) {
            ConfigMgr.getInstance().initUrl(intent.getStringExtra(ConfigX.HOST));
            ConfigMgr.getInstance().initUserName(intent.getStringExtra(ConfigX.USERNAME));
        }
    }
}
