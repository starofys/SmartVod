package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.api.HttpVod;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.ProgramDatasRep;

/**
 * Created by zengxiaoping on 2018/1/17.
 *
 * @Author zengxiaoping
 */

public class HomeMovieRecommondModel implements IHomeMovieRecommondList {
	@Override
	public void showData(IHttpRetCallBack<ProgramDatasRep> iHttpRetCallBack) {
		HttpVod.getVodRecommendDatas(
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				HttpConst.METHOD_01,
				HttpConst.VERSION,
				"",
				"",
				"",
				"getVodRecommendDatas",
				ProgramDatasRep.class,
				iHttpRetCallBack);
	}
}
