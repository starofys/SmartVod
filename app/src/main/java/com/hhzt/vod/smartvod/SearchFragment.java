package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.link.SearchMovieContract;
import com.hhzt.vod.smartvod.mvp.presenter.SearchMovieLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.LinearMainLayout;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_search)
public class SearchFragment extends BaseFragment implements SearchMovieContract.SearchMovieView {

	//左半部分------------------------------------------------------------------
	@ViewInject(R.id.lml_keyboard)
	private LinearMainLayout mLmlKeyboard;
	@ViewInject(R.id.riv_full_keyboard)
	private ReflectItemView mRivFullKeyboard;
	@ViewInject(R.id.riv_t9_keyboard)
	private ReflectItemView mRivT9Keyboard;

	@ViewInject(R.id.tv_search_movie_name)
	private TextView mTvSearchMovieName;
	@ViewInject(R.id.rcv_keyboard)
	private RecyclerViewTV mRcvKeyboard;

	@ViewInject(R.id.lml_delete_or_clear)
	private LinearMainLayout mLmlDeleteOrClear;
	@ViewInject(R.id.riv_delete)
	private ReflectItemView mRivDelete;
	@ViewInject(R.id.riv_clear)
	private ReflectItemView mRivClear;

	//右半部分------------------------------------------------------------------
	@ViewInject(R.id.ll_hot_search)
	private LinearLayout mLlHotSearch;
	@ViewInject(R.id.rcv_hot_search_up)
	private RecyclerViewTV mRcvHotSearchUp;
	@ViewInject(R.id.rcv_hot_search_down)
	private RecyclerViewTV mRcvHotSearchDown;
	@ViewInject(R.id.rcv_search_history)
	private RecyclerViewTV mRcvSearchHistory;

	@ViewInject(R.id.ll_search_result)
	private LinearLayout mLlSearchResult;
	@ViewInject(R.id.rcv_search_result)
	private RecyclerViewTV mRcvSearchResult;

	private SearchMovieContract.SearchMoviePresenter mSearchMoviePresenter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mSearchMoviePresenter = new SearchMovieLinkPresenter(context, InJection.initSearchMovie(), this);
		mSearchMoviePresenter.start();
		mSearchMoviePresenter.init();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void setPresenter(SearchMovieContract.SearchMoviePresenter presenter) {
		mSearchMoviePresenter = presenter;
	}

	@Override
	public void showHotMovieData(ArrayList<SimpleRepBean> hotSearchList, ArrayList<MovieInfoData> hotList) {

	}

	@Override
	public void showSearchHistoryData(ArrayList<String> keyList) {

	}

	@Override
	public void showSearchMovieReultData(ArrayList<MovieInfoData> searchMovieResultData) {

	}

	@Override
	public void showFullKeyBoardData(ArrayList<String> keyList) {

	}

	@Override
	public void showT9KeyBoardData(ArrayList<KeyBean> keyBeanList) {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mSearchMoviePresenter.destoryInit();
	}
}
