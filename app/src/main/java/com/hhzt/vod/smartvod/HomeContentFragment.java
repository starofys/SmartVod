package com.hhzt.vod.smartvod;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.repBean.CategoryRepBean;
import com.hhzt.vod.logiclayer.keydispatch.KeyBroadcastSender;
import com.hhzt.vod.logiclayer.keydispatch.KeyFactoryConst;
import com.hhzt.vod.smartvod.adapter.LeftMenuPresenter;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieTypeContract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeMovieTypeLinkPresenter;
import com.hhzt.vod.smartvod.observer.AchieveObserverWatched;
import com.hhzt.vod.smartvod.observer.ObserverConst;
import com.hhzt.vod.smartvod.observer.ObserverWatcher;
import com.hhzt.vod.smartvod.view.ViewWrapper;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.LinearMainLayout;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_home_main)
public class HomeContentFragment extends BaseFragment implements HomeMovieTypeContract.IHomeMovieTypeView, ObserverWatcher ,ViewTreeObserver.OnGlobalFocusChangeListener{

	@ViewInject(R.id.lml_type)
	private LinearMainLayout mLmlType;
	@ViewInject(R.id.riv_search)
	private ReflectItemView mRivSearch;
	@ViewInject(R.id.rcv_movie_type_list)
	private RecyclerViewTV mRcvMovieTypeList;
	@ViewInject(R.id.tv_current_page)
	private TextView mTvCurrentPage;
	@ViewInject(R.id.mainUpView1)
	private MainUpView mMainUpView;

	private RecyclerViewBridge mRecyclerViewBridge;
	private HomeMovieTypeContract.HomeMovieTypePresenter mHomeMovieTypeLinkPresenter;

	private int mVodListItemSelectedIndex;
	private int mPageTotal = 1;
	private int mPageCurrent = 1;
	private boolean mShowCurrentPage = true;
	private boolean mShowTranslate = false;
	private LeftMenuPresenter mLeftMenuPresenter;
	private Handler mHandler = new Handler();

	private ListSelectFoucsBroadCastReceiver mListSelectFoucsBroadCastReceiver;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mHomeMovieTypeLinkPresenter = new HomeMovieTypeLinkPresenter(context, InJection.initHomeType(), this);
		mHomeMovieTypeLinkPresenter.init();
		mHomeMovieTypeLinkPresenter.start();
		AchieveObserverWatched.getInstance().add(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		AchieveObserverWatched.getInstance().remove(this);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		mHomeMovieTypeLinkPresenter.showData(ConfigMgr.getInstance().getGroupID());

		ViewWrapper wrapper = new ViewWrapper(mLmlType);
		int dimension = (int) getResources().getDimension(R.dimen.layx365);
		ObjectAnimator.ofInt(wrapper, "width", dimension).setDuration(200).start();

		mListSelectFoucsBroadCastReceiver = new ListSelectFoucsBroadCastReceiver();
		IntentFilter intentFilter = new IntentFilter(KeyFactoryConst.KEY_LISTEN_ACTION);
		getActivity().registerReceiver(mListSelectFoucsBroadCastReceiver, intentFilter);
	}

	private void initView() {
		mMainUpView.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
	}

	private void bindAdater(List<CategoryRepBean> movieTypeNames) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRcvMovieTypeList.setLayoutManager(layoutManager);
		mLeftMenuPresenter = new LeftMenuPresenter(movieTypeNames);
		GeneralAdapter generalAdapter = new GeneralAdapter(mLeftMenuPresenter);
		mRcvMovieTypeList.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mLmlType.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
		mRcvMovieTypeList.setItemSelected(0);
		mRcvMovieTypeList.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setUnFocusView(itemView);
			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
				mVodListItemSelectedIndex = position;
				mRecyclerViewBridge.setFocusView(itemView, 1.0f);
			}

			/**
			 * 这里是调整开头和结尾的移动边框.
			 */
			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setFocusView(itemView, 1.0f);
			}
		});
		mRcvMovieTypeList.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, final int position) {
				mVodListItemSelectedIndex = position;
				mHomeMovieTypeLinkPresenter.switchFragment(getActivity(), R.id.fragment_movie_container, position);
				mLeftMenuPresenter.setSelectPosition(position);
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						focusView(mRecyclerViewBridge, mRcvMovieTypeList.getChildAt(position), 1.0f);
					}
				}, 10);
			}
		});

		mRcvMovieTypeList.setOnItemKeyListener(new RecyclerViewTV.OnItemKeyListener() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				int keyCode = event.getKeyCode();
				int size = ((HomeMovieTypeLinkPresenter) mHomeMovieTypeLinkPresenter).getCategoryNames().size();
				if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
					KeyBroadcastSender.getInstance().sendRightBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_LIST);
					mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
					return true;
				}

				return false;
			}
		});

		mRivSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initDefaultFouces() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mRcvMovieTypeList.setItemSelected(0);
			}
		}, 200);
	}

	@Override
	public void setPresenter(HomeMovieTypeContract.HomeMovieTypePresenter presenter) {
		mHomeMovieTypeLinkPresenter = presenter;
	}

	@Override
	public void showData(List<CategoryRepBean> movieTypeNames) {
		mHomeMovieTypeLinkPresenter.switchFragment(getActivity(), R.id.fragment_movie_container, 0);
		bindAdater(movieTypeNames);
		initEvent();
		initDefaultFouces();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHomeMovieTypeLinkPresenter.destoryInit();
		getActivity().unregisterReceiver(mListSelectFoucsBroadCastReceiver);
		mHandler.removeCallbacks(null);
	}

	@Override
	public void updataNotify(int code, Object var2) {
		switch (code) {
			case ObserverConst.CODE_MOVIE_CURRENT_PAGE:
				mPageCurrent = (int) var2;
				mShowCurrentPage = true;
				break;
			case ObserverConst.CODE_MOVIE_TOTAL_PAGE:
				mPageTotal = (int) var2;
				mShowCurrentPage = true;
				break;
			case ObserverConst.CODE_MOVIE_SHOW_OR_HINT_PAGE:
				mShowCurrentPage = false;
				break;
			case ObserverConst.CODE_MOVIE_TYPE_SHOW_OR_HINT:
				final boolean show = (boolean) var2;
				if (mShowTranslate) {
					ViewWrapper wrapper = new ViewWrapper(mLmlType);
					int dimension = (int) getResources().getDimension(R.dimen.layx365);
					ObjectAnimator.ofInt(wrapper, "width", show ? dimension : 0).setDuration(200).start();
				}
				break;
			case ObserverConst.CODE_MOVIE_TYPE_TRANSLATE:
				mShowTranslate = (boolean) var2;
				break;
			default:
				break;
		}
		mTvCurrentPage.setText(mPageCurrent + "/" + mPageTotal);
		mTvCurrentPage.setVisibility(mShowCurrentPage ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onGlobalFocusChanged(View oldFocus, View newFocus) {
		if (newFocus != null)
			newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
		float scale = 1.0f;
		mMainUpView.setFocusView(newFocus, scale);
	}

	private final class ListSelectFoucsBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (null != intent) {
				switch (intent.getAction()) {
					case KeyFactoryConst.KEY_LISTEN_ACTION:
						String keyType = intent.getStringExtra(KeyFactoryConst.KEY_CODE_TAG);
						if (KeyFactoryConst.KEY_CODE_LEFT.equalsIgnoreCase(keyType)) {
							mRcvMovieTypeList.setItemSelected(mVodListItemSelectedIndex);
							mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
						} else if (KeyFactoryConst.KEY_CODE_UP.equalsIgnoreCase(keyType)) {
							mHandler.postDelayed(new Runnable() {
								@Override
								public void run() {
//									focusView(mRecyclerViewBridge, mRivSearch, ConfigX.SCALE);
									mRcvMovieTypeList.setItemSelected(mVodListItemSelectedIndex);
									mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
								}
							}, 200);

						} else if (KeyFactoryConst.KEY_CODE_DOWN.equalsIgnoreCase(keyType)) {
							mHandler.postDelayed(new Runnable() {
								@Override
								public void run() {
									mRcvMovieTypeList.setItemSelected(mVodListItemSelectedIndex);
									mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
								}
							}, 200);
						}
						break;
					default:
						break;
				}
			}
		}
	}
}
