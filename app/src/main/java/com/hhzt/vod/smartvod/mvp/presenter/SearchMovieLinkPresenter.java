package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.api.repData.ProgramSuperDataRep;
import com.hhzt.vod.api.repData.SearchMainDataV1_1Rep;
import com.hhzt.vod.api.repData.VodSearchDataRep;
import com.hhzt.vod.smartvod.MovieDetailActivity;
import com.hhzt.vod.smartvod.constant.ConfigX;
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
	public static final int TYPE_HOT_SEARCH_LIST = 0;   //热门搜素down
	public static final int TYPE_HOT_LIST = 1;          //热门搜索up
	public static final int TYPE_SEARCH_RESULT = 2;     //搜索结果

	private Context mContext;
	private ISearchMovie mISearchMovie;
	private SearchMovieContract.SearchMovieView mSearchMovieView;

	//全键盘、T9键盘
	private ArrayList<String> mFullBoardList = new ArrayList<>();
	private ArrayList<KeyBean> mT9BoardList = new ArrayList<>();

	//热门搜索 and  历史搜索
	private ArrayList<MovieInfoData> mHotSearchList = new ArrayList<>();
	private ArrayList<SimpleRepBean> mSearchHistoryList = new ArrayList<>();

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
		mISearchMovie.requestHotMovie(new IHttpRetCallBack<SearchMainDataV1_1Rep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, SearchMainDataV1_1Rep searchMainDatasRep) {
				mHotSearchList.addAll(searchMainDatasRep.getHotSearchList());
				mSearchHistoryList.addAll(searchMainDatasRep.getSearchHistoryList());

				mSearchMovieView.showHotMovieData(mSearchHistoryList, mHotSearchList);
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
				mSearchMovieReultList.clear();
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
	public void showSearchHistoryMovie() {
		mISearchMovie.requestSearchHistory(new IHttpRetCallBack<ProgramSuperDataRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, ProgramSuperDataRep programSuperDataRep) {
				mSearchHistoryList.clear();
				ArrayList<SimpleRepBean> programSuperSimpleBoList = programSuperDataRep.getProgramSuperSimpleBoList();
				for (int i = 0; i < programSuperSimpleBoList.size(); i++) {
					if (i < 4) {
						mSearchHistoryList.add(programSuperSimpleBoList.get(i));
					}
				}
				mSearchMovieView.showSearchHistoryData(mSearchHistoryList);
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
	public void clickOtherMovieDetail(Context packageContext, int type, int position) {
		Intent intent = new Intent(packageContext, MovieDetailActivity.class);
		int categoryId = 0;
		int programId = 0;
		boolean vipFlag = false;
		switch (type) {
			case TYPE_HOT_SEARCH_LIST:
				categoryId = mHotSearchList.get(position).getCategoryId();
				programId = mHotSearchList.get(position).getId();
				vipFlag = mHotSearchList.get(position).getVipFlag() == ConfigX.NEED_VIP;
				break;
			case TYPE_HOT_LIST:
				categoryId = mHotSearchList.get(position).getCategoryId();
				programId = mHotSearchList.get(position).getId();
				vipFlag = mHotSearchList.get(position).getVipFlag() == ConfigX.NEED_VIP;
				break;
			case TYPE_SEARCH_RESULT:
				categoryId = mSearchMovieReultList.get(position).getCategoryId();
				programId = mSearchMovieReultList.get(position).getId();
				vipFlag = mSearchMovieReultList.get(position).getVipFlag() == ConfigX.NEED_VIP;
				break;
		}
		intent.putExtra(MovieDetailActivity.MOVIE_NEED_PAY_TAG, vipFlag);
		intent.putExtra(MovieDetailActivity.MOVIE_CATEGORY_ID, categoryId);
		intent.putExtra(MovieDetailActivity.MOVIE_PROGRAM_ID, programId);
		packageContext.startActivity(intent);
	}

	@Override
	public void postSearchPlayRecord(int position, IHttpRetCallBack<String> iHttpRetCallBack) {
		int programId = mSearchMovieReultList.get(position).getId();
		String requestIp = ConfigMgr.getInstance().getEpgUrlHost();
		mISearchMovie.postSearchPlayRecord(programId, requestIp, iHttpRetCallBack);
	}


	@Override
	public void clickFullKey(int position, String currentName) {
		currentName += mFullBoardList.get(position);
		mSearchMovieView.showSearchMovieName(currentName);
		showSearchReultMovieData(1, 18, currentName);
	}

	@Override
	public void clickT9Key(int parentPosition, int childPosition, String currentName, String addName) {
		currentName += addName;
		mSearchMovieView.showSearchMovieName(currentName);
		showSearchReultMovieData(1, 18, currentName);
	}

	@Override
	public void clickDelete(String currentName) {
		if (currentName.length() <= 1) {
			mSearchMovieView.showSearchMovieName("");
			showHotMovieData();
		} else {
			currentName = currentName.substring(0, currentName.length() - 1);
			mSearchMovieView.showSearchMovieName(currentName);
			showSearchReultMovieData(1, 18, currentName);
		}
	}

	@Override
	public void clickClear() {
		mSearchMovieView.showSearchMovieName("");
		showHotMovieData();
	}

	@Override
	public void destoryInit() {

	}
}
