package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public interface IMovieDetail {
	void requestMovieDetail(int programGroupId,int categoryId, int programId, IHttpRetCallBack<ProgramDetaiContentDataRep> iHttpRetCallBack);

	void requestVodPayResult(String contentId, String type, IHttpRetCallBack<CommonRspRetBean> iHttpRetCallBack);
}
