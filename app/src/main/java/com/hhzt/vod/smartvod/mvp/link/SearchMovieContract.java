package com.hhzt.vod.smartvod.mvp.link;

import android.content.Context;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.smartvod.mvp.iview.BaseView;
import com.hhzt.vod.smartvod.mvp.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class SearchMovieContract {
	public interface SearchMovieView extends BaseView<SearchMoviePresenter> {
		void showHotMovieData(ArrayList<SimpleRepBean> hotSearchList, ArrayList<MovieInfoData> hotList);

		void showSearchHistoryData(ArrayList<String> keyList);

		void showSearchMovieReultData(ArrayList<MovieInfoData> searchMovieResultData);

		void showFullKeyBoardData(ArrayList<String> keyList);

		void showT9KeyBoardData(ArrayList<KeyBean> keyBeanList);

		void showSearchMovieName(String searchMovieName);
	}

	public interface SearchMoviePresenter extends BasePresenter {
		void init();

		void showHotMovieData();

		void showSearchReultMovieData(int pageNum, int pageSize, String key);

		void showFullKeyboardData();

		void showT9KeyboardData();

		void clickOtherMovieDetail(Context packageContext, int type, int position);

		void clickFullKey(int position, String currentName);

		void clickT9Key(int parentPosition, int childPosition, String currentName, String addName);

		void clickDelete(String currentName);

		void clickClear();

		void destoryInit();
	}
}

