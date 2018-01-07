package com.hhzt.vod.api;

import android.text.TextUtils;

/**
 * Created by wujichang on 2018/1/7.
 */

public class ConfigMgr {

    private static ConfigMgr mConfigMgr;

    private String mUrlHost;
    private String mUserName;

    private ConfigMgr() {

    }

    public static ConfigMgr getInstance() {
        if (null == mConfigMgr) {
            synchronized (ConfigMgr.class) {
                if (null == mConfigMgr) {
                    mConfigMgr = new ConfigMgr();
                }
            }
        }

        return mConfigMgr;
    }

    public void initUrl(String url) {
        mUrlHost = url;
    }

    public void initUserName(String name) {
        mUserName = name;
    }

    public String getUserName() {
        return TextUtils.isEmpty(mUserName) ? HttpConst.DEFAULT_NAME : mUserName;
    }

    public String getUrlHost() {
        return TextUtils.isEmpty(mUrlHost) ? HttpConst.DEFAULT_HOST : mUrlHost;
    }
}
