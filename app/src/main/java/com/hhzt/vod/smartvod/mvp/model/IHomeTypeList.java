package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public interface IHomeTypeList {
	void showData(int programGroupId, int categoryId, int pageNum, int pageSize, IHttpRetCallBack<VodGroupDetailDataRep> iHttpRetCallBack);
}
