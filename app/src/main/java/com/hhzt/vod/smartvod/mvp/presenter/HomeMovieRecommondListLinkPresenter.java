package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.ProgramDatasRep;
import com.hhzt.vod.smartvod.MovieDetailActivity;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieRecommodListContract;
import com.hhzt.vod.smartvod.mvp.model.IHomeMovieRecommondList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @Author zengxiaoping
 */

public class HomeMovieRecommondListLinkPresenter implements HomeMovieRecommodListContract.HomeMovieListPresenter {
	private Context mContext;
	private IHomeMovieRecommondList mIHomeMovieRecommondList;
	private HomeMovieRecommodListContract.HomeMovieListView mHomeMovieListView;

	private List<MovieInfoData> mMovieInfoData = new ArrayList<>();

	public HomeMovieRecommondListLinkPresenter(
			Context context,
			IHomeMovieRecommondList iHomeMovieRecommondList,
			HomeMovieRecommodListContract.HomeMovieListView homeMovieListView) {
		mContext = context;
		mIHomeMovieRecommondList = iHomeMovieRecommondList;
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
	public void showData() {
		mIHomeMovieRecommondList.showData(new IHttpRetCallBack<ProgramDatasRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, ProgramDatasRep programDatasRep) {
				mMovieInfoData.clear();
				mMovieInfoData.addAll(programDatasRep.getProgramSimpleBoList());
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
		intent.putExtra(MovieDetailActivity.MOVIE_NEED_PAY_TAG, mMovieInfoData.get(position).getVipFlag());
		intent.putExtra(MovieDetailActivity.MOVIE_CATEGORY_ID, mMovieInfoData.get(position).getCategoryId());
		intent.putExtra(MovieDetailActivity.MOVIE_PROGRAM_ID, mMovieInfoData.get(position).getId());
		packageContext.startActivity(intent);
	}
}
