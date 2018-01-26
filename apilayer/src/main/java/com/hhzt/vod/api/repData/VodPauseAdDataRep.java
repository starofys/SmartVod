package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.AdvertisingBoBean;

/**
 * Created by wujichang on 2018/1/26.
 */

public class VodPauseAdDataRep {

	private AdvertisingBoBean advertisingBo;

	public AdvertisingBoBean getAdvertisingBo() {
		return advertisingBo;
	}

	public void setAdvertisingBo(AdvertisingBoBean advertisingBo) {
		this.advertisingBo = advertisingBo;
	}

	@Override
	public String toString() {
		return "VodPauseAdDataRep{" +
				"advertisingBo=" + advertisingBo +
				'}';
	}
}
