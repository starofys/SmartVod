package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.smartvod.adapter.LeftMenuPresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieTypeContract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeMovieTypeLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_home_main)
public class HomeContentFragment extends BaseFragment implements HomeMovieTypeContract.IHomeMovieTypeView {
	@ViewInject(R.id.rcv_movie_type_list)
	private RecyclerViewTV mRcvMovieTypeList;

	private HomeMovieTypeContract.HomeMovieTypePresenter mHomeMovieTypeLinkPresenter;

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
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mHomeMovieTypeLinkPresenter.showData(ConfigX.PROGRAM_GROUP_ID);
	}

	private void bindAdater(List<SimpleRepBean> movieTypeNames) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRcvMovieTypeList.setLayoutManager(layoutManager);
		GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter(movieTypeNames));
		mRcvMovieTypeList.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mRcvMovieTypeList.setDefaultSelect(0);
		mRcvMovieTypeList.setOnItemListener(new RecyclerViewTV.OnItemListener() {
			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {

			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
				mHomeMovieTypeLinkPresenter.switchFragment(getActivity(), R.id.fragment_movie_container, position);
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

		mRcvMovieTypeList.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.ACTION_UP) {

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
	public void showData(List<SimpleRepBean> movieTypeNames) {
		mHomeMovieTypeLinkPresenter.switchFragment(getActivity(), R.id.fragment_movie_container, 0);
		bindAdater(movieTypeNames);
		initEvent();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHomeMovieTypeLinkPresenter.destoryInit();
	}
}
