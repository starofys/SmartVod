package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.PayResultRep;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public interface IMovieDetail {
	void requestMovieDetail(int programGroupId, int categoryId, int programId, IHttpRetCallBack<ProgramDetaiContentDataRep> iHttpRetCallBack);

	void requestVodPayResult(int contentId, int type, IHttpRetCallBack<PayResultRep> iHttpRetCallBack);
}
