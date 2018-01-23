package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;

import java.util.ArrayList;

/**
 * Created by Johnson on 2018/1/23.
 */

public class SearchMainDataV1_1Rep {
	private ArrayList<MovieInfoData> hotSearchList = new ArrayList<>();
	private ArrayList<SimpleRepBean> searchHistoryList = new ArrayList<>();

	public ArrayList<MovieInfoData> getHotSearchList() {
		return hotSearchList;
	}

	public void setHotSearchList(ArrayList<MovieInfoData> hotSearchList) {
		this.hotSearchList = hotSearchList;
	}

	public ArrayList<SimpleRepBean> getSearchHistoryList() {
		return searchHistoryList;
	}

	public void setSearchHistoryList(ArrayList<SimpleRepBean> searchHistoryList) {
		this.searchHistoryList = searchHistoryList;
	}

	@Override
	public String toString() {
		return "SearchMainDataV1_1Rep{" +
				"hotSearchList=" + hotSearchList +
				", searchHistoryList=" + searchHistoryList +
				'}';
	}
}
