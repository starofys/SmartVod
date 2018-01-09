package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.api.HttpVod;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class MovieDetailModel implements IMovieDetail{
	@Override
	public void requestMovieDetail(int programGroupId, int programId, int categoryId, IHttpRetCallBack<ProgramDetaiContentDataRep> iHttpRetCallBack) {
		HttpVod.getVodItemDetailDatas(
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				HttpConst.METHOD_04,
				HttpConst.VERSION,
				"",
				programId,
				categoryId,
				programGroupId,
				"getVodItemDetailDatas",
				ProgramDetaiContentDataRep.class,
				iHttpRetCallBack
		);
	}
}
