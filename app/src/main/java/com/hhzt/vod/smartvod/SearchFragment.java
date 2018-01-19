package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.smartvod.adapter.FullKeyboardPresenter;
import com.hhzt.vod.smartvod.adapter.SearchMovieKeyPresenter;
import com.hhzt.vod.smartvod.adapter.SearchMoviePicturePresenter;
import com.hhzt.vod.smartvod.adapter.T9KeyboardPresenter;
import com.hhzt.vod.smartvod.callback.T9ClickCallBack;
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
public class SearchFragment extends BaseFragment implements SearchMovieContract.SearchMovieView, View.OnClickListener, ViewTreeObserver.OnGlobalFocusChangeListener, RecyclerViewTV.OnItemListener {
	public static final int TYPE_KEY_BOARD_FULL = 0;
	public static final int TYPE_KEY_BOARD_t9 = 1;

	//左半部分------------------------------------------------------------------
	@ViewInject(R.id.lml_keyboard)
	private LinearMainLayout mLmlKeyboard;
	@ViewInject(R.id.riv_full_keyboard)
	private ReflectItemView mRivFullKeyboard;
	@ViewInject(R.id.tv_full_keyboard)
	private TextView mTvFullKeyboard;
	@ViewInject(R.id.riv_t9_keyboard)
	private ReflectItemView mRivT9Keyboard;
	@ViewInject(R.id.tv_t9_keyboard)
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
	@ViewInject(R.id.ll_search_history)
	private LinearLayout mLlSearchHistory;

	@ViewInject(R.id.ll_search_result)
	private LinearLayout mLlSearchResult;
	@ViewInject(R.id.rcv_search_result)
	private RecyclerViewTV mRcvSearchResult;

	@ViewInject(R.id.mainUpView1)
	private MainUpView mMainUpView;

	private int mKeyBoardType = TYPE_KEY_BOARD_FULL;
	private RecyclerViewBridge mRecyclerViewBridge;
	private SearchMovieContract.SearchMoviePresenter mSearchMoviePresenter;
	private Handler mHandler = new Handler();

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
		mSearchMoviePresenter.showSearchHistoryMovie();

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				View view = mRcvKeyboard.getChildAt(0);
				mRecyclerViewBridge.setFocusView(view, ConfigX.SCALE);
				if (view != null) {
					view.requestLayout();
					view.requestFocus();
				}
				mMainUpView.setVisibility(View.GONE);
			}
		}, 50);
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

		//全键盘(T9键盘特殊：走adpter里面的监听)
//		mRcvKeyboard.setItemSelected(0);
		mRcvKeyboard.setOnItemListener(this);
		mRcvKeyboard.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				if (mKeyBoardType == TYPE_KEY_BOARD_FULL) {
					String searchKeyWord = mTvSearchMovieName.getText().toString();
					mSearchMoviePresenter.clickFullKey(position, searchKeyWord);
				}
			}
		});

		//热门电影up
		mRcvHotSearchUp.setOnItemListener(this);
		mRcvHotSearchUp.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mSearchMoviePresenter.clickOtherMovieDetail(getActivity(), SearchMovieLinkPresenter.TYPE_HOT_LIST, position);
			}
		});

		//热门电影down
//		mRcvHotSearchDown.setOnItemListener(this);
		mRcvHotSearchDown.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mSearchMoviePresenter.clickOtherMovieDetail(getActivity(), SearchMovieLinkPresenter.TYPE_HOT_SEARCH_LIST, position);
			}
		});

		//历史搜索电影
//		mRcvSearchHistory.setOnItemKeyListener(this);
		mRcvSearchHistory.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mSearchMoviePresenter.clickOtherMovieDetail(getActivity(), SearchMovieLinkPresenter.TYPE_HOT_SEARCH_LIST, position);
			}
		});

		//搜索结果
//		mRcvSearchResult.setOnItemListener(this);
		mRcvSearchResult.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mSearchMoviePresenter.clickOtherMovieDetail(getActivity(), SearchMovieLinkPresenter.TYPE_SEARCH_RESULT, position);
			}
		});
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
	public void showSearchHistoryData(ArrayList<SimpleRepBean> historySearchMovieList) {
		mLlHotSearch.setVisibility(View.VISIBLE);
		mLlSearchResult.setVisibility(View.GONE);

		if (historySearchMovieList.size() == 0) {
			mLlSearchHistory.setVisibility(View.GONE);
			mRcvSearchHistory.setVisibility(View.GONE);
		}
		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 2);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		mRcvSearchHistory.setLayoutManager(gridlayoutManager);
		mRcvSearchHistory.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new SearchMovieKeyPresenter(historySearchMovieList));
		mRcvSearchHistory.setAdapter(generalAdapter);
	}

	@Override
	public void showSearchMovieReultData(ArrayList<MovieInfoData> searchMovieResultData) {
		mLlHotSearch.setVisibility(View.GONE);
		mLlSearchResult.setVisibility(View.VISIBLE);

		ArrayList<SimpleRepBean> movieList = new ArrayList<>();
		for (MovieInfoData movieInfoData : searchMovieResultData) {
			SimpleRepBean simplebean = new SimpleRepBean();
			simplebean.setId(movieInfoData.getId());
			simplebean.setCategoryId(movieInfoData.getCategoryId());
			simplebean.setName(movieInfoData.getName());
			movieList.add(simplebean);
		}

		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 2);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		mRcvSearchResult.setLayoutManager(gridlayoutManager);
		mRcvSearchResult.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new SearchMovieKeyPresenter(movieList));
		mRcvSearchResult.setAdapter(generalAdapter);
	}

	@Override
	public void showFullKeyBoardData(ArrayList<String> keyList) {
		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 6);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		mRcvKeyboard.setLayoutManager(gridlayoutManager);
		mRcvKeyboard.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new FullKeyboardPresenter(getContext(), keyList));
		mRcvKeyboard.setAdapter(generalAdapter);
	}

	@Override
	public void showT9KeyBoardData(ArrayList<KeyBean> keyBeanList) {
		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 3);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		mRcvKeyboard.setLayoutManager(gridlayoutManager);
		mRcvKeyboard.setFocusable(false);
		T9KeyboardPresenter t9KeyboardPresenter = new T9KeyboardPresenter(getContext(), keyBeanList, mHandler, mRecyclerViewBridge);
		GeneralAdapter generalAdapter = new GeneralAdapter(t9KeyboardPresenter);
		mRcvKeyboard.setAdapter(generalAdapter);
		t9KeyboardPresenter.setT9ClickCallBack(new T9ClickCallBack() {
			@Override
			public void t9KeyBoardClickPosition(int parentPosition, int childPosition, String parentKeyName, String childKeyName) {
				mSearchMoviePresenter.clickT9Key(parentPosition, childPosition, mTvSearchMovieName.getText().toString(), childKeyName);
			}
		});
	}

	@Override
	public void showSearchMovieName(String searchMovieName) {
		mTvSearchMovieName.setText(searchMovieName);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mSearchMoviePresenter.destoryInit();
		mHandler.removeCallbacks(null);
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
				mSearchMoviePresenter.clickClear();
				break;
			case R.id.riv_delete:
				mSearchMoviePresenter.clickDelete(mTvSearchMovieName.getText().toString());
				break;
			default:
				break;
		}
	}

	@Override
	public void onGlobalFocusChanged(View oldFocus, View newFocus) {
		if (newFocus != null)
			newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
		float scale = 1.0f;
		mMainUpView.setFocusView(newFocus, scale);
	}

	@Override
	public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
		mRecyclerViewBridge.setUnFocusView(itemView);
	}

	@Override
	public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
		mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
	}

	@Override
	public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
		mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
	}
}
