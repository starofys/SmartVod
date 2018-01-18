package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.EpisodeBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;
import com.hhzt.vod.api.repData.PayResultRep;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;
import com.hhzt.vod.media.Clarity;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.VideoPlayerActivity;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.dialog.PayDialogFragment;
import com.hhzt.vod.smartvod.mvp.link.MovieDetailContract;
import com.hhzt.vod.smartvod.mvp.model.IMovieDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class MovieDetailLinkPresenter implements MovieDetailContract.MovieDetailPresenter {
	public static final int TYPE_RELATE = 1;
	public static final int TYPE_HOT = 2;
	public static final int RAGNGE_SIZE = 10;

	private Context mContext;
	private IMovieDetail mIMovieDetail;
	private MovieDetailContract.MovieDetailView mMovieDetailView;

	private ProgramDetaiContentDataRep mProgramDetaiContentDataRep;
	private ProgrameDetailBo mProgramDetailBo;
	private List<EpisodeBean> mEpisodeList = new ArrayList<>();
	private List<String> mEpisodeRangeList = new ArrayList<>();
	private ArrayList<MovieInfoData> mReleteList = new ArrayList<>();
	private ArrayList<MovieInfoData> mHotList = new ArrayList<>();

	public MovieDetailLinkPresenter(Context context, IMovieDetail IMovieDetail, MovieDetailContract.MovieDetailView movieDetailView) {
		mContext = context;
		mIMovieDetail = IMovieDetail;
		mMovieDetailView = movieDetailView;

		mMovieDetailView.setPresenter(this);
	}

	@Override
	public void start() {

	}

	@Override
	public void init() {

	}

	@Override
	public void showData(int programGroupId, int categoryId, int programId) {
		mIMovieDetail.requestMovieDetail(programGroupId, categoryId, programId, new IHttpRetCallBack<ProgramDetaiContentDataRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, ProgramDetaiContentDataRep programDetaiContentDataRep) {
				mProgramDetaiContentDataRep = programDetaiContentDataRep;

				mProgramDetailBo = mProgramDetaiContentDataRep.programDetailBo;
				mReleteList.addAll(new ArrayList<MovieInfoData>());
				mHotList.addAll(mProgramDetaiContentDataRep.hotList);

				int number = mProgramDetailBo.getMediaList().size();
				for (int i = 1; i <= number; i++) {
					EpisodeBean episodeBean = new EpisodeBean();
					episodeBean.setEpisode(i + "");
					if (mProgramDetailBo.getVipFlag() == ConfigX.NEED_VIP)
						episodeBean.setTable((i < ConfigX.FREE_SERIES_NUMBER) ? ConfigX.FREE : ConfigX.NEED_VIP);
					else episodeBean.setTable(ConfigX.FREE);

					mEpisodeList.add(episodeBean);
				}

				boolean divided = number % RAGNGE_SIZE == 0;
				if (divided) {
					number = number / RAGNGE_SIZE;
				} else {
					number = number / RAGNGE_SIZE + 1;
				}
				for (int i = 0; i < number; i++) {
					mEpisodeRangeList.add("" + (i * RAGNGE_SIZE + 1) + "-" + (i * RAGNGE_SIZE + RAGNGE_SIZE));
				}
				if (number > 1) mMovieDetailView.isTvSeries(true);
				else mMovieDetailView.isTvSeries(false);

				mMovieDetailView.showMovieDetail(mEpisodeList, mEpisodeRangeList, mProgramDetailBo);
				mMovieDetailView.showMovieRelate(mReleteList);
				mMovieDetailView.showMovieHot(mHotList);

				List<Clarity> clarities = new ArrayList<>();
				Resources resources = mContext.getResources();
				clarities.add(new Clarity(resources.getString(R.string.media_clarities_normal), resources.getString(R.string.media_clarities_270p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
				clarities.add(new Clarity(resources.getString(R.string.media_clarities_high), resources.getString(R.string.media_clarities_480p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
				clarities.add(new Clarity(resources.getString(R.string.media_clarities_super), resources.getString(R.string.media_clarities_720p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
				clarities.add(new Clarity(resources.getString(R.string.media_clarities_blue), resources.getString(R.string.media_clarities_1080p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
				mMovieDetailView.showSmallVideo(clarities, mProgramDetailBo.getName(), mProgramDetailBo.getSmallPoster());
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
	public void clickOtherMovieDetail(MovieDetailCallBack movieDetailCallBack, int type, int position) {
		int categoryId = 0;
		int programId = 0;
		switch (type) {
			case TYPE_RELATE:
				categoryId = mReleteList.get(position).getCategoryId();
				programId = mReleteList.get(position).getId();
				break;
			case TYPE_HOT:
				categoryId = mHotList.get(position).getCategoryId();
				programId = mHotList.get(position).getId();
				break;
			default:
				break;
		}
		movieDetailCallBack.showMovieDetailCallBack(0, categoryId, programId);
	}

	@Override
	public void clearData() {
		mReleteList.clear();
		mHotList.clear();
	}

	@Override
	public void destoryInit() {
		mContext = null;
	}

	@Override
	public void toVideoPlayerActivity(Context packageContext, Class<?> cls, int position) {
		Intent intent = new Intent(packageContext, cls);
		intent.putExtra(VideoPlayerActivity.MOVIEW_PLAYER_URL, mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(position).getFilePath());
		packageContext.startActivity(intent);
	}

	@Override
	public void showPayWeb(FragmentManager fragmentManager) {
		int contentId = mProgramDetaiContentDataRep.programDetailBo.getId();
		PayDialogFragment payDialogFragment = PayDialogFragment.getInstance(
				ConfigMgr.getInstance().getUserName(),
				contentId,
				ConfigX.MEDIA_TYPE_VOD,
				ConfigX.MEDIA_PAY_PATH_DIRECT);
		payDialogFragment.show(fragmentManager, "showPayWeb");
	}

	@Override
	public void checkPayResult(final int contentId, final int type, final IHttpRetCallBack<PayResultRep> iHttpRetCallBack) {
		mIMovieDetail.requestVodPayResult(contentId, type, new IHttpRetCallBack<PayResultRep>() {

			@Override
			public void onResponseSuccess(CommonRspRetBean bean, PayResultRep payResultRep) {
				System.out.print("onResponseSuccess:" + bean.msg);
				if (null != iHttpRetCallBack) {
					iHttpRetCallBack.onResponseSuccess(bean, payResultRep);
				}
			}

			@Override
			public void onResponseFailed(CommonRspRetBean bean) {
				System.out.print("onResponseFailed:" + bean.msg);
				if (null != iHttpRetCallBack) {
					iHttpRetCallBack.onResponseFailed(bean);
				}
			}

			@Override
			public void onError(String result) {
				System.out.print("onError:" + result);
				if (null != iHttpRetCallBack) {
					iHttpRetCallBack.onError(result);
				}
			}

			@Override
			public void onCancelled() {
				System.out.print("onCancelled");
				if (null != iHttpRetCallBack) {
					iHttpRetCallBack.onCancelled();
				}
			}

			@Override
			public void onFinish() {
				System.out.print("onFinish");
				if (null != iHttpRetCallBack) {
					iHttpRetCallBack.onFinish();
				}
			}
		});
	}
}
