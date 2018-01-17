package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.CategoryRepBean;
import com.hhzt.vod.api.repData.CategoryBoDatasRep;
import com.hhzt.vod.smartvod.BaseFragment;
import com.hhzt.vod.smartvod.MovieFactory;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieTypeContract;
import com.hhzt.vod.smartvod.mvp.model.IHomeType;
import com.hhzt.vod.smartvod.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @Author zengxiaoping
 */

public class HomeMovieTypeLinkPresenter implements HomeMovieTypeContract.HomeMovieTypePresenter {
	private Context mContext;
	private IHomeType mIHomeType;
	private HomeMovieTypeContract.IHomeMovieTypeView mIHomeMovieTypeView;

	private List<CategoryRepBean> mCategoryNames = new ArrayList<>();

	public HomeMovieTypeLinkPresenter(
			Context context,
			IHomeType IHomeType,
			HomeMovieTypeContract.IHomeMovieTypeView IHomeMovieTypeView) {
		mContext = context;
		mIHomeType = IHomeType;
		mIHomeMovieTypeView = IHomeMovieTypeView;

		mIHomeMovieTypeView.setPresenter(this);
	}

	@Override
	public void start() {

	}

	@Override
	public void init() {

	}

	@Override
	public void showData(int programGroupId) {
		mIHomeType.requestMovieType(programGroupId, new IHttpRetCallBack<CategoryBoDatasRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, CategoryBoDatasRep categoryBoDatasRep) {
				mCategoryNames.addAll(categoryBoDatasRep.getCategoryBoList());
				mIHomeMovieTypeView.showData(mCategoryNames);

				CategoryRepBean categoryRepBean = new CategoryRepBean();
				categoryRepBean.setName(mContext.getResources().getString(R.string.recommond));
				mCategoryNames.add(0, categoryRepBean);
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
		mCategoryNames.clear();
	}

	@Override
	public void destoryInit() {
		mContext = null;
	}

	@Override
	public void switchFragment(FragmentActivity activity, int layoutId, int position) {
		int movieShowType;
		BaseFragment moviePictureListFragment;
		switch (position) {
			case 0:
				movieShowType = MovieFactory.MOVIE_SHOW_TYPE_BIG_PICTURE;
				break;
			case 1:
//				break;
//			case 2:
			default:
				movieShowType = MovieFactory.MOVIE_SHOW_TYPE_MIX_PICTURE;
//				movieShowType = MovieFactory.MOVIE_SHOW_TYPE_SMALL_PICTURE;
				break;
		}
		moviePictureListFragment = MovieFactory.getFragment(movieShowType, mCategoryNames.get(position).getId());
		FragmentUtil.replace(activity, false, R.id.fragment_movie_container, moviePictureListFragment);
	}

	public List<CategoryRepBean> getCategoryNames() {
		return mCategoryNames;
	}
}
