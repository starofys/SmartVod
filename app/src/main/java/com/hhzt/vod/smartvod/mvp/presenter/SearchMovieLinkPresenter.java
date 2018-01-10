package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.api.repData.SearchMainDatasRep;
import com.hhzt.vod.api.repData.VodSearchDataRep;
import com.hhzt.vod.smartvod.mvp.link.SearchMovieContract;
import com.hhzt.vod.smartvod.mvp.model.ISearchMovie;

import java.util.ArrayList;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class SearchMovieLinkPresenter implements SearchMovieContract.SearchMoviePresenter {
	private Context mContext;
	private ISearchMovie mISearchMovie;
	private SearchMovieContract.SearchMovieView mSearchMovieView;

	//全键盘、T9键盘
	private ArrayList<String> mFullBoardList = new ArrayList<>();
	private ArrayList<KeyBean> mT9BoardList = new ArrayList<>();

	//热门搜索 and  历史搜索
	private ArrayList<SimpleRepBean> mHotSearchList = new ArrayList<>();
	private ArrayList<MovieInfoData> mHotList = new ArrayList<>();
	private ArrayList<String> mSearchHistoryList = new ArrayList<>();

	//相关搜索==》即：搜索结果
	private ArrayList<MovieInfoData> mSearchMovieReultList = new ArrayList<>();

	public SearchMovieLinkPresenter(Context context, ISearchMovie ISearchMovie, SearchMovieContract.SearchMovieView searchMovieView) {
		mContext = context;
		mISearchMovie = ISearchMovie;
		mSearchMovieView = searchMovieView;

		mSearchMovieView.setPresenter(this);
	}

	@Override
	public void start() {

	}

	@Override
	public void init() {
		mFullBoardList.addAll(mISearchMovie.getFullBoardList());
		mT9BoardList.addAll(mISearchMovie.getT9BoardList());
	}

	@Override
	public void showHotMovieData() {
		mISearchMovie.requestHotMovie(new IHttpRetCallBack<SearchMainDatasRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, SearchMainDatasRep searchMainDatasRep) {
				mHotSearchList.addAll(searchMainDatasRep.getHotSearchList());
				mHotList.addAll(searchMainDatasRep.getHotList());

				mSearchMovieView.showHotMovieData(mHotSearchList, mHotList);
			}

			@Override
			public void onResponseFailed(CommonRspRetBean bean) {

			}

			@Override
			public void onError(String result) {

			}

			@Override
			public void onCancelled() {

			}

			@Override
			public void onFinish() {

			}
		});
	}

	@Override
	public void showSearchReultMovieData(int pageNum, int pageSize, String searchKeyWord) {
		mISearchMovie.requestSearchMovieReult(pageNum, pageSize, searchKeyWord, new IHttpRetCallBack<VodSearchDataRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, VodSearchDataRep vodSearchDataRep) {
				mSearchMovieReultList.addAll(vodSearchDataRep.getProgramSimpleBoList());
				mSearchMovieView.showSearchMovieReultData(mSearchMovieReultList);
			}

			@Override
			public void onResponseFailed(CommonRspRetBean bean) {

			}

			@Override
			public void onError(String result) {

			}

			@Override
			public void onCancelled() {

			}

			@Override
			public void onFinish() {

			}
		});
	}

	@Override
	public void showFullKeyboardData() {
		mSearchMovieView.showFullKeyBoardData(mFullBoardList);
	}

	@Override
	public void showT9KeyboardData() {
		mSearchMovieView.showT9KeyBoardData(mT9BoardList);
	}

	@Override
	public void destoryInit() {

	}
}
