package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.api.HttpVod;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class HomeTypeListModelModel implements IHomeTypeList {
	@Override
	public void showData(int programGroupId, int categoryId, int pageNum, int pageSize, IHttpRetCallBack<VodGroupDetailDataRep> iHttpRetCallBack) {
		HttpVod.getVodCategoryDetailByGroupDatas(
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				HttpConst.METHOD_03,
				HttpConst.VERSION,
				"",
				pageNum,
				pageSize,
				categoryId,
				programGroupId,
				"",
				"getVodCategoryDetailByGroupDatas",
				VodGroupDetailDataRep.class,
				iHttpRetCallBack
		);
	}
}
