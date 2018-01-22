package com.hhzt.vod.smartvod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.logiclayer.keydispatch.KeyBroadcastSender;
import com.hhzt.vod.logiclayer.keydispatch.KeyFactoryConst;
import com.hhzt.vod.smartvod.adapter.HomeBigPicturePresenter;
import com.hhzt.vod.smartvod.adapter.HomeSmallPicturePresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieListContract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeMovieListLinkPresenter;
import com.hhzt.vod.smartvod.observer.AchieveObserverWatched;
import com.hhzt.vod.smartvod.observer.ObserverConst;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.hhzt.vod.smartvod.mvp.presenter.HomeMovieListLinkPresenter.PAGE_SIZE;

/**
 * Created by zengxaioping on 2017/12/29.
 *
 * @description TODO
 * @Author 混合大小图
 */
@ContentView(R.layout.fragment_mix_picture_list)
public class MovieMixPictureListFragment extends MovieListFragment implements HomeMovieListContract.HomeMovieListView {

	@ViewInject(R.id.rcv_movie_big_picture)
	private RecyclerViewTV mRcvMovieBigPicture;
	@ViewInject(R.id.rcv_movie_small_picture)
	private RecyclerViewTV mRcvMovieSmallPicture;
	@ViewInject(R.id.mainUpView)
	private MainUpView mMainUpView;

	private int mCategoryId;
	private RecyclerViewBridge mRecyclerViewBridge;

	private List<MovieInfoData> mMovieBigPictureList = new ArrayList<>();
	private List<MovieInfoData> mMovieSmallPictureList = new ArrayList<>();

	private HomeMovieListContract.HomeMovieListPresenter mHomeMovieListLinkPresenter;

	private int mSelectRecylerType;
	private int mSelectSmallRecyclerIndex;
	private int mSelectBigRecyclerIndex;
	private int mPageNumber = 1;

	private MovieBroadCastReceiver mMovieBroadCastReceiver;
	private GridLayoutManagerTV mGridlayoutManager;
	private Handler mHandler = new Handler();

	/**
	 * @param catagoryid
	 * @return
	 */
	public static MovieMixPictureListFragment getIntance(int catagoryid) {
		MovieMixPictureListFragment fragment = new MovieMixPictureListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(MovieDetailActivity.MOVIE_CATEGORY_ID, catagoryid);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mHomeMovieListLinkPresenter = new HomeMovieListLinkPresenter(context, InJection.initHomeTypeList(), this);
		mHomeMovieListLinkPresenter.init();
		mHomeMovieListLinkPresenter.start();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCategoryId = getArguments().getInt(MovieDetailActivity.MOVIE_CATEGORY_ID);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		mHomeMovieListLinkPresenter.showData(ConfigMgr.getInstance().getGroupID(), mCategoryId, mPageNumber, PAGE_SIZE);

		mMovieBroadCastReceiver = new MovieBroadCastReceiver();
		IntentFilter intentFilter = new IntentFilter(KeyFactoryConst.KEY_LISTEN_ACTION);
		getActivity().registerReceiver(mMovieBroadCastReceiver, intentFilter);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(null);
		getActivity().unregisterReceiver(mMovieBroadCastReceiver);
	}

	private void initView() {
		if (null != mMainUpView) {
			mMainUpView.setEffectBridge(new RecyclerViewBridge());
			mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
			mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
		}
	}

	private void bindAdater() {
		LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvMovieBigPicture.setLayoutManager(layoutManager);
		mRcvMovieBigPicture.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new HomeBigPicturePresenter(getContext(), mMovieBigPictureList));
		mRcvMovieBigPicture.setAdapter(generalAdapter);

		mGridlayoutManager = new GridLayoutManagerTV(getContext(), 2);
		mGridlayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
		mRcvMovieSmallPicture.setLayoutManager(mGridlayoutManager);
		mRcvMovieSmallPicture.setFocusable(false);
		generalAdapter = new GeneralAdapter(new HomeSmallPicturePresenter(getContext(), mMovieSmallPictureList));
		mRcvMovieSmallPicture.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mRcvMovieBigPicture.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setUnFocusView(itemView);
			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
				mSelectRecylerType = 0;
				mSelectBigRecyclerIndex = position;
				mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
				if (position == 0) {
					AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_TRANSLATE, true);
					AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_SHOW_OR_HINT, true);
				}
			}

			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
			}
		});

		mRcvMovieBigPicture.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieListLinkPresenter.toMovieDetail(getActivity(), MovieDetailActivity.class, position);
			}
		});

		mRcvMovieSmallPicture.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setUnFocusView(itemView);
			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
				mSelectRecylerType = 1;
				mSelectSmallRecyclerIndex = position;
				mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
				if ((position == mMovieSmallPictureList.size() - 1 || position == mMovieSmallPictureList.size() - 2
						|| position == mMovieSmallPictureList.size() - 3 || position == mMovieSmallPictureList.size() - 4)
						&& (mMovieSmallPictureList.size() + mMovieBigPictureList.size()) % PAGE_SIZE == 0) {
					mPageNumber++;
					mHomeMovieListLinkPresenter.showData(ConfigMgr.getInstance().getGroupID(), mCategoryId, mPageNumber, PAGE_SIZE);
				}

				int currentPosition = position + 2;
				int currentPage = currentPosition / PAGE_SIZE + 1;
				AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_CURRENT_PAGE, currentPage);

				if (currentPage > 1 && (position == mMovieSmallPictureList.size() - 1 || position == mMovieSmallPictureList.size() - 2)) {
					AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_TRANSLATE, true);
					AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_SHOW_OR_HINT, false);
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							focusView(mRecyclerViewBridge, mRcvMovieSmallPicture.getChildAt(mSelectSmallRecyclerIndex), ConfigX.SCALE);
						}
					}, 201);
				}
			}

			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
			}
		});

		mRcvMovieSmallPicture.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieListLinkPresenter.toMovieDetail(getActivity(), MovieDetailActivity.class, position + mMovieBigPictureList.size());
			}
		});

		mRcvMovieSmallPicture.setOnItemKeyListener(new RecyclerViewTV.OnItemKeyListener() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				int keyCode = event.getKeyCode();
				if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
					int index = mRcvMovieSmallPicture.getSelectPostion();
					View itemView = mRcvMovieSmallPicture.getFocusedChild();
					if (index % 2 == 0) {
						mRecyclerViewBridge.setUnFocusView(itemView);
						KeyBroadcastSender.getInstance().sendUpBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
						return true;
					}
				} else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
					int index = mRcvMovieSmallPicture.getSelectPostion();
					if (index % 2 != 0) {
						AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_TRANSLATE, true);
						AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_SHOW_OR_HINT, true);
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								focusView(mRecyclerViewBridge, mRcvMovieSmallPicture.getChildAt(mSelectSmallRecyclerIndex), ConfigX.SCALE);
							}
						}, 201);
						return true;
					}
				} else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
					if (mSelectSmallRecyclerIndex == mMovieSmallPictureList.size() - 3
							|| mSelectSmallRecyclerIndex == mMovieSmallPictureList.size() - 4) {
						return false;
					}
				}
				return false;
			}
		});
		mRcvMovieBigPicture.setOnItemKeyListener(new RecyclerViewTV.OnItemKeyListener() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				int keyCode = event.getKeyCode();
				if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
					int index = mRcvMovieBigPicture.getSelectPostion();
					if (index == 0) {
						KeyBroadcastSender.getInstance().sendLeftBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
						return true;
					}
				} else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
					int index = mRcvMovieBigPicture.getSelectPostion();
					if (index % 2 == 0) {
						if (index <= 1) {
							mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
						}
						KeyBroadcastSender.getInstance().sendUpBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
						return true;
					} else {
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
					}
				} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
					if (mSelectRecylerType == 0 && mSelectBigRecyclerIndex == 1) {
						mSelectRecylerType = 1;
						mSelectSmallRecyclerIndex = 0;
						mRecyclerViewBridge.setUnFocusView(mRcvMovieBigPicture.getChildAt(1));
						mRecyclerViewBridge.setFocusView(mRcvMovieSmallPicture.getChildAt(0), ConfigX.SCALE);
						mRcvMovieSmallPicture.getChildAt(0).requestLayout();
						mRcvMovieSmallPicture.getChildAt(0).requestFocus();
						return true;
					}
				} else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
					AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_TRANSLATE, true);
					AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_SHOW_OR_HINT, true);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public void setPresenter(HomeMovieListContract.HomeMovieListPresenter presenter) {
		mHomeMovieListLinkPresenter = presenter;
	}

	@Override
	public void showData(List<MovieInfoData> movieInfoData) {
		int size = movieInfoData.size();
		mMovieBigPictureList.clear();
		mMovieSmallPictureList.clear();
		for (int i = 0; i < size; i++) {
			if (i <= 1) {
				mMovieBigPictureList.add(movieInfoData.get(i));
			} else {
				mMovieSmallPictureList.add(movieInfoData.get(i));
			}
		}
		bindAdater();
		initEvent();

		if (mPageNumber != 1) {
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					focusView(mRecyclerViewBridge, mRcvMovieSmallPicture.getChildAt(mSelectSmallRecyclerIndex), ConfigX.SCALE);
				}
			}, 10);
		}
	}

	@Override
	public void showTotalPage(int totalPage) {
		AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TOTAL_PAGE, totalPage);
	}

	private final class MovieBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (KeyFactoryConst.KEY_LISTEN_ACTION.equals(intent.getAction())) {
				String keyType = intent.getStringExtra(KeyFactoryConst.KEY_CODE_TAG);
				switch (keyType) {
					case KeyFactoryConst.KEY_CODE_DOWN: {
						View view;
						if (mSelectRecylerType == 0) {
							view = mRcvMovieBigPicture.getChildAt(mSelectBigRecyclerIndex);
						} else {
							view = mRcvMovieSmallPicture.getChildAt(mSelectSmallRecyclerIndex);
						}
						view.requestLayout();
						view.requestFocus();
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
					}
					break;
					case KeyFactoryConst.KEY_CODE_RIGHT: {
						View view = mRcvMovieBigPicture.getChildAt(0);
						if (null != view) {
							view.requestLayout();
							view.requestFocus();
						}
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
					}
					break;
				}
			}
		}
	}

	private void focusView(RecyclerViewBridge recyclerViewBridge, View view, float scale) {
		recyclerViewBridge.setFocusView(view, scale);
		if (view != null) {
			view.requestLayout();
			view.requestFocus();
		}
	}
}
