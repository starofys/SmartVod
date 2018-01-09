package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.CategoryBoDatasRep;

/**
 * Created by Johnson on 2017/12/29.
 */

public interface IHomeType {
	void requestMovieType(int programGroupId, IHttpRetCallBack<CategoryBoDatasRep> iHttpRetCallBack);
}
