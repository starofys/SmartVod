package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.smartvod.adapter.FullKeyboardPresenter;
import com.hhzt.vod.smartvod.adapter.SearchMovieKeyPresenter;
import com.hhzt.vod.smartvod.adapter.SearchMoviePicturePresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.link.SearchMovieContract;
import com.hhzt.vod.smartvod.mvp.presenter.SearchMovieLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.LinearMainLayout;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_search)
public class SearchFragment extends BaseFragment implements SearchMovieContract.SearchMovieView, View.OnClickListener, ViewTreeObserver.OnGlobalFocusChangeListener {

	//左半部分------------------------------------------------------------------
	@ViewInject(R.id.lml_keyboard)
	private LinearMainLayout mLmlKeyboard;
	@ViewInject(R.id.riv_full_keyboard)
	private ReflectItemView mRivFullKeyboard;
	@ViewInject(R.id.tv_full_keyboard)
	private TextView mTvFullKeyboard;
	@ViewInject(R.id.riv_t9_keyboard)
	private ReflectItemView mRivT9Keyboard;
	@ViewInject(R.id.tv_full_keyboard)
	private TextView mTvT9Keyboard;

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

	@ViewInject(R.id.mainUpView1)
	private MainUpView mMainUpView;

	private RecyclerViewBridge mRecyclerViewBridge;

	private SearchMovieContract.SearchMoviePresenter mSearchMoviePresenter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mSearchMoviePresenter = new SearchMovieLinkPresenter(context, InJection.initSearchMovie(), this);
		mSearchMoviePresenter.init();
		mSearchMoviePresenter.start();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initEvent();
		mSearchMoviePresenter.showFullKeyboardData();
		mSearchMoviePresenter.showHotMovieData();
	}

	private void initView() {
		mMainUpView.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
	}

	private void initEvent() {
		mRivFullKeyboard.setOnClickListener(this);
		mRivT9Keyboard.setOnClickListener(this);
		mRivClear.setOnClickListener(this);
		mRivDelete.setOnClickListener(this);
		mLmlKeyboard.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
		mLmlDeleteOrClear.getViewTreeObserver().addOnGlobalFocusChangeListener(this);

		//键盘
		mRcvKeyboard.setDefaultSelect(0);
		mRcvKeyboard.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {

			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
			}

			/**
			 * 这里是调整开头和结尾的移动边框.
			 */
			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
			}
		});
		mRcvKeyboard.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				//todo 未做:去搜索
				Toast.makeText(getActivity(), "点击了：" + position, Toast.LENGTH_SHORT).show();
			}
		});

		//热门电影up
		//热门电影down
		//搜索结果

	}

	@Override
	public void setPresenter(SearchMovieContract.SearchMoviePresenter presenter) {
		mSearchMoviePresenter = presenter;
	}

	@Override
	public void showHotMovieData(ArrayList<SimpleRepBean> hotSearchList, ArrayList<MovieInfoData> hotList) {
		mLlHotSearch.setVisibility(View.VISIBLE);
		mLlSearchResult.setVisibility(View.GONE);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvHotSearchUp.setLayoutManager(layoutManager);
		mRcvHotSearchUp.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new SearchMoviePicturePresenter(getContext(), hotList));
		mRcvHotSearchUp.setAdapter(generalAdapter);


		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 2);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		mRcvHotSearchDown.setLayoutManager(gridlayoutManager);
		mRcvHotSearchDown.setFocusable(false);
		generalAdapter = new GeneralAdapter(new SearchMovieKeyPresenter(hotSearchList));
		mRcvHotSearchDown.setAdapter(generalAdapter);
	}

	@Override
	public void showSearchHistoryData(ArrayList<String> keyList) {
		mLlHotSearch.setVisibility(View.VISIBLE);
		mLlSearchResult.setVisibility(View.GONE);
		//todo 数据暂定.目前还不确定是后台返回还是本地存储
	}

	@Override
	public void showSearchMovieReultData(ArrayList<MovieInfoData> searchMovieResultData) {
		mLlHotSearch.setVisibility(View.GONE);
		mLlSearchResult.setVisibility(View.VISIBLE);
	}

	@Override
	public void showFullKeyBoardData(ArrayList<String> keyList) {
		if (mRcvKeyboard != null) {
			GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 6);
			gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
			mRcvKeyboard.setLayoutManager(gridlayoutManager);
			mRcvKeyboard.setFocusable(false);
			GeneralAdapter generalAdapter = new GeneralAdapter(new FullKeyboardPresenter(getContext(), keyList));
			mRcvKeyboard.setAdapter(generalAdapter);
		}
	}

	@Override
	public void showT9KeyBoardData(ArrayList<KeyBean> keyBeanList) {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mSearchMoviePresenter.destoryInit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.riv_full_keyboard:
				mSearchMoviePresenter.showFullKeyboardData();
				mTvFullKeyboard.setTextColor(getResources().getColor(R.color.blue));
				mTvT9Keyboard.setTextColor(getResources().getColor(R.color.tv_white));
				break;
			case R.id.riv_t9_keyboard:
				mSearchMoviePresenter.showT9KeyboardData();
				mTvFullKeyboard.setTextColor(getResources().getColor(R.color.tv_white));
				mTvT9Keyboard.setTextColor(getResources().getColor(R.color.blue));
				break;
			case R.id.riv_clear:
				mTvSearchMovieName.setText("");
				mSearchMoviePresenter.showHotMovieData();
				break;
			case R.id.riv_delete:
				String searchKeyWord = mTvSearchMovieName.getText().toString();
				if (searchKeyWord.length() == 1) {
					mTvSearchMovieName.setText("");
					mSearchMoviePresenter.showHotMovieData();
				} else {
					searchKeyWord = searchKeyWord.substring(0, searchKeyWord.length() - 2);
					mTvSearchMovieName.setText(searchKeyWord);
					mSearchMoviePresenter.showSearchReultMovieData(1, 18, searchKeyWord);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public void onGlobalFocusChanged(View oldFocus, View newFocus) {
		if (newFocus != null)
			newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
		float scale = ConfigX.SCALE;
		mMainUpView.setFocusView(newFocus, scale);
	}
}
