package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.api.HttpVod;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.CategoryBoDatasRep;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class HomeTypeModel implements IHomeType {
	@Override
	public void requestMovieType(int programGroupId, IHttpRetCallBack<CategoryBoDatasRep> iHttpRetCallBack) {
		HttpVod.getVodMenuByGroupDatas(
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				HttpConst.METHOD_02,
				HttpConst.VERSION,
				"",
				programGroupId,
				"getVodMenuByGroupDatas",
				CategoryBoDatasRep.class,
				iHttpRetCallBack
		);
	}
}
