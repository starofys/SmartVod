package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.adapter.HomeBigPicturePresenter;
import com.hhzt.vod.smartvod.adapter.HomeSmallPicturePresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieListContract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeMovieListLinkPresenter;
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
	@ViewInject(R.id.mainUpView1)
	private MainUpView mMainUpView;

	private int mMovieTypeId;
	private RecyclerViewBridge mRecyclerViewBridge;

	private List<MovieInfoData> mMovieBigPictureList = new ArrayList<>();
	private List<MovieInfoData> mMovieSmallPictureList = new ArrayList<>();

	private HomeMovieListContract.HomeMovieListPresenter mHomeMovieListLinkPresenter;

	/**
	 * @param movieTypeid 电影类型
	 * @return
	 */
	public static MovieMixPictureListFragment getIntance(int movieTypeid) {
		MovieMixPictureListFragment fragment = new MovieMixPictureListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(MovieDetailActivity.MOVIE_TYPE_ID, movieTypeid);
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
		mMovieTypeId = getArguments().getInt(MovieDetailActivity.MOVIE_TYPE_ID);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		mHomeMovieListLinkPresenter.showData(ConfigX.PROGRAM_GROUP_ID, 1, 30, mMovieTypeId);
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

		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 2);
		gridlayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
		mRcvMovieSmallPicture.setLayoutManager(gridlayoutManager);
		mRcvMovieSmallPicture.setFocusable(false);
		generalAdapter = new GeneralAdapter(new HomeSmallPicturePresenter(getContext(), mMovieSmallPictureList));
		mRcvMovieSmallPicture.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mRcvMovieBigPicture.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
				// 传入 itemView也可以, 自己保存的 oldView也可以.
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

		mRcvMovieBigPicture.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieListLinkPresenter.toMovieDetail(getActivity(), MovieDetailActivity.class, position, mMovieTypeId);
			}
		});

		mRcvMovieSmallPicture.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
				// 传入 itemView也可以, 自己保存的 oldView也可以.
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

		mRcvMovieSmallPicture.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieListLinkPresenter.toMovieDetail(getActivity(), MovieDetailActivity.class, position + mMovieBigPictureList.size(), mMovieTypeId);
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
		for (int i = 0; i < size; i++) {
			if (i <= 1) {
				mMovieBigPictureList.add(movieInfoData.get(i));
			} else {
				mMovieSmallPictureList.add(movieInfoData.get(i));
			}
		}
		bindAdater();
		initEvent();
	}
}
