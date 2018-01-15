package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.ProgramDatasRep;

/**
 * Created by zengxiaoping on 2018/1/17.
 *
 * @Author zengxiaoping
 */

public interface IHomeMovieRecommondList {
	void showData(IHttpRetCallBack<ProgramDatasRep> iHttpRetCallBack);
}
