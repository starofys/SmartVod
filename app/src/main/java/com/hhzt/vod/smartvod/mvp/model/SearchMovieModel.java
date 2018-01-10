package com.hhzt.vod.smartvod.mvp.model;

import com.hhzt.vod.api.HttpConst;
import com.hhzt.vod.api.HttpVod;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repData.SearchMainDatasRep;
import com.hhzt.vod.api.repData.VodSearchDataRep;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.VodApp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class SearchMovieModel implements ISearchMovie {

	@Override
	public void requestSearchMovieReult(int pageNum, int pageSize, String searchKeyWord, IHttpRetCallBack<VodSearchDataRep> iHttpRetCallBack) {
		HttpVod.getVodSearchKeyCodeDatas(
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				HttpConst.METHOD_09,
				HttpConst.VERSION,
				"",
				pageNum,
				pageSize,
				searchKeyWord,
				"getVodSearchKeyCodeDatas",
				VodSearchDataRep.class,
				iHttpRetCallBack
		);
	}

	@Override
	public void requestHotMovie(IHttpRetCallBack<SearchMainDatasRep> iHttpRetCallBack) {
		HttpVod.getVodSearchMainDatas(
				HttpConst.APP_KEY,
				HttpConst.FORMAT,
				HttpConst.METHOD_08,
				HttpConst.VERSION,
				"",
				"getVodSearchMainDatas",
				SearchMainDatasRep.class,
				iHttpRetCallBack
		);
	}

	@Override
	public ArrayList<KeyBean> getT9BoardList() {
		ArrayList<KeyBean> keyBeans = new ArrayList<>();
		String[] numbers = VodApp.mContext.getResources().getStringArray(R.array.number_keyboard);
		String[] letters = VodApp.mContext.getResources().getStringArray(R.array.english_letter_keyboard);
		int size = numbers.length;
		for (int i = 0; i < size; i++) {
			KeyBean keyBean = new KeyBean();
			keyBean.setNumberKeyBoard(numbers[i]);
			keyBean.setLetterKeyBoard(letters[i]);
			keyBeans.add(keyBean);
		}

		return keyBeans;
	}

	@Override
	public ArrayList<String> getFullBoardList() {
		ArrayList<String> keyList = new ArrayList<>();
		String[] keys = VodApp.mContext.getResources().getStringArray(R.array.full_keyboard);
		keyList.addAll(Arrays.asList(keys));
		return keyList;
	}
}
