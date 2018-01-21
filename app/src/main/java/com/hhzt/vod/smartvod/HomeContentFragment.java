package com.hhzt.vod.smartvod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
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
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_home_main)
public class HomeContentFragment extends BaseFragment implements HomeMovieTypeContract.IHomeMovieTypeView, ObserverWatcher {
	@ViewInject(R.id.rcv_movie_type_list)
	private RecyclerViewTV mRcvMovieTypeList;
	@ViewInject(R.id.tv_current_page)
	private TextView mTvCurrentPage;

	private HomeMovieTypeContract.HomeMovieTypePresenter mHomeMovieTypeLinkPresenter;

	private int mVodListItemSelectedIndex;
	private int mPageTotal = 1;
	private int mPageCurrent = 1;
	private boolean mShowCurrentPage = true;
	private boolean mShowTranslate = false;

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
		mHomeMovieTypeLinkPresenter.showData(ConfigMgr.getInstance().getGroupID());

		mListSelectFoucsBroadCastReceiver = new ListSelectFoucsBroadCastReceiver();
		IntentFilter intentFilter = new IntentFilter(KeyFactoryConst.KEY_LISTEN_ACTION);
		getActivity().registerReceiver(mListSelectFoucsBroadCastReceiver, intentFilter);
	}

	private void bindAdater(List<CategoryRepBean> movieTypeNames) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRcvMovieTypeList.setLayoutManager(layoutManager);
		GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter(movieTypeNames));
		mRcvMovieTypeList.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mRcvMovieTypeList.setItemSelected(0);
		mRcvMovieTypeList.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {

			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
				if (position != mVodListItemSelectedIndex) {
					mVodListItemSelectedIndex = position;
					mHomeMovieTypeLinkPresenter.switchFragment(getActivity(), R.id.fragment_movie_container, position);
				}
			}

			/**
			 * 这里是调整开头和结尾的移动边框.
			 */
			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
			}
		});
		mRcvMovieTypeList.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieTypeLinkPresenter.switchFragment(getActivity(), R.id.fragment_movie_container, position);
			}
		});

		mRcvMovieTypeList.setOnItemKeyListener(new RecyclerViewTV.OnItemKeyListener() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				int keyCode = event.getKeyCode();
				int size = ((HomeMovieTypeLinkPresenter) mHomeMovieTypeLinkPresenter).getCategoryNames().size();
				if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
					if (mVodListItemSelectedIndex == 0) {
						mRcvMovieTypeList.setItemSelected(size - 1);
						return true;
					}
				} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
					if (mVodListItemSelectedIndex == size - 1) {
						mRcvMovieTypeList.setItemSelected(0);
						return true;
					}
				} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
					KeyBroadcastSender.getInstance().sendRightBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_LIST);
					return true;
				}

				return false;
			}
		});
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
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHomeMovieTypeLinkPresenter.destoryInit();
		getActivity().unregisterReceiver(mListSelectFoucsBroadCastReceiver);
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
				int visibility = mRcvMovieTypeList.getVisibility();
				int showVisible = show ? View.VISIBLE : View.GONE;
				mRcvMovieTypeList.setVisibility(show ? View.VISIBLE : View.GONE);
				if (mShowTranslate && visibility != showVisible) {
					//todo 开始一个动画
					Animation animation1 = AnimationUtils.loadAnimation(getActivity(), show ? R.anim.slide_in_left : R.anim.slide_out_left);
					animation1.setInterpolator(new DecelerateInterpolator());
					animation1.setAnimationListener(
							new Animation.AnimationListener() {
								@Override
								public void onAnimationStart(Animation animation) {

								}

								@Override
								public void onAnimationEnd(Animation animation) {

								}

								@Override
								public void onAnimationRepeat(Animation animation) {

								}
							}
					);
					mRcvMovieTypeList.clearAnimation();
					mRcvMovieTypeList.startAnimation(animation1);
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

	private final class ListSelectFoucsBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (null != intent) {
				switch (intent.getAction()) {
					case KeyFactoryConst.KEY_LISTEN_ACTION:
						String keyType = intent.getStringExtra(KeyFactoryConst.KEY_CODE_TAG);
						if (KeyFactoryConst.KEY_CODE_LEFT.equalsIgnoreCase(keyType)) {
							mRcvMovieTypeList.setItemSelected(mVodListItemSelectedIndex);
						}
						break;
					default:
						break;
				}
			}
		}
	}
}
