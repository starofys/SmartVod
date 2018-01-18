package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repData.ProgramSuperDataRep;
import com.hhzt.vod.api.repData.SearchMainDatasRep;
import com.hhzt.vod.api.repData.VodSearchDataRep;

import java.util.ArrayList;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public interface ISearchMovie {
	void requestSearchHistory(IHttpRetCallBack<ProgramSuperDataRep> iHttpRetCallBack);

	void requestSearchMovieReult(int pageNum, int pageSize, String searchKeyWord, IHttpRetCallBack<VodSearchDataRep> iHttpRetCallBack);

	void requestHotMovie(IHttpRetCallBack<SearchMainDatasRep> iHttpRetCallBack);

	ArrayList<KeyBean> getT9BoardList();

	ArrayList<String> getFullBoardList();
}
