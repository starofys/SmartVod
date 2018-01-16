package com.hhzt.vod.api;

import android.text.TextUtils;

/**
 * Created by wujichang on 2018/1/7.
 */

public class ConfigMgr {

	private static ConfigMgr mConfigMgr;

	private String mEpgUrlHost;
	private String mUserName;
	private int mGroupID;

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

	public void initEpgUrl(String url) {
		mEpgUrlHost = url;
	}

	public void initUserName(String name) {
		mUserName = name;
	}

	public void initGroupID(int groupID) {
		this.mGroupID = groupID;
	}

	public String getUserName() {
		return TextUtils.isEmpty(mUserName) ? HttpConst.DEFAULT_NAME : mUserName;
	}

	public String getEpgUrlHost() {
		return TextUtils.isEmpty(mEpgUrlHost) ? HttpConst.DEFAULT_HOST : mEpgUrlHost + HttpConst.EPG_PATH;
	}

	public String getPayUrlHost() {
		return TextUtils.isEmpty(mEpgUrlHost) ? HttpConst.DEFAULT_PAY_HOST : mEpgUrlHost + HttpConst.PAY_PATH;
	}

	public int getGroupID() {
		return this.mGroupID == 0 ? HttpConst.DEFAULT_GROUP_ID : this.mGroupID;
	}
}
