package com.hhzt.vod.smartvod;

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

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.logiclayer.keydispatch.KeyBroadcastSender;
import com.hhzt.vod.logiclayer.keydispatch.KeyFactoryConst;
import com.hhzt.vod.smartvod.adapter.HomeBigPicturePresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieRecommodListContract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeMovieRecommondListLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * 大图
 * <p>
 * Created by zengxiaoping on 2017/12/29.
 *
 * @Author
 */

@ContentView(R.layout.fragment_big_picture_list)
public class MovieBigPictureListFragment extends MovieListFragment implements HomeMovieRecommodListContract.HomeMovieListView {

	@ViewInject(R.id.rcv_movie_item_container)
	private RecyclerViewTV mRcvMovieItemContainer;
	@ViewInject(R.id.mainUpView)
	private MainUpView mMainUpView;

	private Handler mHandler = new Handler();
	private RecyclerViewBridge mRecyclerViewBridge;

	private HomeMovieRecommodListContract.HomeMovieListPresenter mHomeMovieListLinkPresenter;

	private MovieBroadCastReceiver mMovieBroadCastReceiver;

	/**
	 * @return
	 */
	public static MovieBigPictureListFragment getIntance() {
		MovieBigPictureListFragment fragment = new MovieBigPictureListFragment();
		Bundle bundle = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mHomeMovieListLinkPresenter = new HomeMovieRecommondListLinkPresenter(context, InJection.initMovieRecommond(), this);
		mHomeMovieListLinkPresenter.init();
		mHomeMovieListLinkPresenter.start();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		mHomeMovieListLinkPresenter.showData();

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
		mMainUpView.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
	}

	private void bindAdater(List<MovieInfoData> movieInfoList) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvMovieItemContainer.setLayoutManager(layoutManager);
		mRcvMovieItemContainer.setHasFixedSize(true);
		mRcvMovieItemContainer.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new HomeBigPicturePresenter(getContext(), movieInfoList));
		mRcvMovieItemContainer.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mRcvMovieItemContainer.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setUnFocusView(itemView);
			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
			}

			/**
			 * 这里是调整开头和结尾的移动边框.
			 */
			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
				mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
			}
		});
		mRcvMovieItemContainer.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieListLinkPresenter.toMovieDetail(getActivity(), MovieDetailActivity.class, position);
			}
		});
		mRcvMovieItemContainer.setOnItemKeyListener(new RecyclerViewTV.OnItemKeyListener() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				int index = mRcvMovieItemContainer.getSelectPostion();
				View itemView = mRcvMovieItemContainer.getFocusedChild();
				if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
					if (index == 0) {
						mRecyclerViewBridge.setUnFocusView(itemView);
						KeyBroadcastSender.getInstance().sendLeftBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
						return true;
					}
				} else {
					requestDefaultFocus(mRecyclerViewBridge, mRcvMovieItemContainer);
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
						mRecyclerViewBridge.setUnFocusView(itemView);
						KeyBroadcastSender.getInstance().sendUpBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
						return true;
					} else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
						mRecyclerViewBridge.setUnFocusView(itemView);
						KeyBroadcastSender.getInstance().sendDownBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
						mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_translate_selector);
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	public void setPresenter(HomeMovieRecommodListContract.HomeMovieListPresenter presenter) {
		mHomeMovieListLinkPresenter = presenter;
	}

	@Override
	public void showData(List<MovieInfoData> movieInfoData) {
		bindAdater(movieInfoData);
		initEvent();
		mMainUpView.setVisibility(View.GONE);
	}

	private final class MovieBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (KeyFactoryConst.KEY_LISTEN_ACTION.equals(intent.getAction())) {
				String keyType = intent.getStringExtra(KeyFactoryConst.KEY_CODE_TAG);
				switch (keyType) {
					case KeyFactoryConst.KEY_CODE_RIGHT: {
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								View view = mRcvMovieItemContainer.getChildAt(0);
								if (null != view) {
									view.requestLayout();
									view.requestFocus();
								}
								mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
							}
						}, 150);
					}
					break;
					default:
						break;
				}
			}
		}
	}
}
