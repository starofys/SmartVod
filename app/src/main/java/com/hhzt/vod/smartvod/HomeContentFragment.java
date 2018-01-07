package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.api.repData.CategoryBoDatasRep;
import com.hhzt.vod.smartvod.adapter.LeftMenuPresenter;
import com.hhzt.vod.smartvod.api.HttpApiTestEng;
import com.hhzt.vod.smartvod.utils.FragmentUtil;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_main)
public class HomeContentFragment extends BaseFragment {
	private int mMovieShowType = MovieFactory.MOVIE_SHOW_TYPE_BIG_PICTURE;

	@ViewInject(R.id.rcv_movie_type)
	private RecyclerViewTV mRcvMovieType;

	//暂时用工厂模式。。如需完美的满足编码以及接口的原则，可以使用工厂模式+反射方式实现。满足接口的思想："封装隔离"
	private BaseFragment mMoviePictureListFragment;
	private List<SimpleRepBean> mMovieTypeName = new ArrayList<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		requestData();
	}

	private void requestData() {
		HttpApiTestEng.testHttpVod02(new IHttpRetCallBack<CategoryBoDatasRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, CategoryBoDatasRep categoryBoDatasRep) {
				mMovieTypeName = categoryBoDatasRep.getCategoryBoList();

				mMoviePictureListFragment = MovieFactory.getFragment(mMovieShowType, mMovieTypeName.get(0).getId());
				FragmentUtil.replace(getActivity(), false, R.id.fragment_movie_container, mMoviePictureListFragment);
				bindAdater();
				initEvent();
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

	private void bindAdater() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRcvMovieType.setLayoutManager(layoutManager);
		GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter(mMovieTypeName));
		mRcvMovieType.setAdapter(generalAdapter);
	}

	private void initEvent() {
		mRcvMovieType.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvMovieType.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				onViewItemClick(itemView, position);
			}
		});

		mRcvMovieType.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.ACTION_UP) {

				}
				return false;
			}
		});
	}

	// 左边侧边栏的单击事件.
	private void onViewItemClick(View v, int pos) {
		switch (pos) {
			case 0:
				mMovieShowType = MovieFactory.MOVIE_SHOW_TYPE_BIG_PICTURE;
				break;
			case 1:
				mMovieShowType = MovieFactory.MOVIE_SHOW_TYPE_MIX_PICTURE;
				break;
			case 2:
			default:
				mMovieShowType = MovieFactory.MOVIE_SHOW_TYPE_SMALL_PICTURE;
				break;
		}
		mMoviePictureListFragment = MovieFactory.getFragment(mMovieShowType, mMovieTypeName.get(pos).getId());
		FragmentUtil.replace(getActivity(), false, R.id.fragment_movie_container, mMoviePictureListFragment);
	}

	public float getDimension(int id) {
		return getResources().getDimension(id);
	}
}
