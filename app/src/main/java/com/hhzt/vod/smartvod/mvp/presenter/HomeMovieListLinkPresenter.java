package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;
import com.hhzt.vod.smartvod.MovieDetailActivity;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieListContract;
import com.hhzt.vod.smartvod.mvp.model.IHomeTypeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @Author zengxiaoping
 */

public class HomeMovieListLinkPresenter implements HomeMovieListContract.HomeMovieListPresenter {
	private Context mContext;
	private IHomeTypeList mIHomeTypeList;
	private HomeMovieListContract.HomeMovieListView mHomeMovieListView;

	private List<MovieInfoData> mMovieInfoData = new ArrayList<>();

	public HomeMovieListLinkPresenter(
			Context context,
			IHomeTypeList IHomeTypeList,
			HomeMovieListContract.HomeMovieListView homeMovieListView) {
		mContext = context;
		mIHomeTypeList = IHomeTypeList;
		mHomeMovieListView = homeMovieListView;

		mHomeMovieListView.setPresenter(this);
	}

	@Override
	public void start() {

	}

	@Override
	public void init() {

	}

	@Override
	public void showData(int programGroupId, int categoryId, int pageNum, int pageSize) {
		mIHomeTypeList.showData(programGroupId, categoryId, pageNum, pageSize, new IHttpRetCallBack<VodGroupDetailDataRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, VodGroupDetailDataRep vodGroupDetailDataRep) {
				mMovieInfoData.clear();
				mMovieInfoData.addAll(vodGroupDetailDataRep.getProgramSimpleBoList());
				mHomeMovieListView.showData(mMovieInfoData);
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

	@Override
	public void clearData() {
		mMovieInfoData.clear();
	}

	@Override
	public void destoryInit() {
		mContext = null;
	}

	@Override
	public void toMovieDetail(Context packageContext, Class<?> cls, int position) {
		Intent intent = new Intent(packageContext, cls);
		intent.putExtra(MovieDetailActivity.MOVIE_CATEGORY_ID, mMovieInfoData.get(position).getCategoryId());
		intent.putExtra(MovieDetailActivity.MOVIE_PROGRAM_ID, mMovieInfoData.get(position).getId());
		packageContext.startActivity(intent);
	}
}
