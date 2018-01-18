package com.hhzt.vod.smartvod;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.EpisodeBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;
import com.hhzt.vod.api.repData.PayResultRep;
import com.hhzt.vod.media.Clarity;
import com.hhzt.vod.media.IPayLogic;
import com.hhzt.vod.media.NiceVideoPlayer;
import com.hhzt.vod.media.TxVideoPlayerController;
import com.hhzt.vod.smartvod.adapter.EpisodePresenter;
import com.hhzt.vod.smartvod.adapter.EpisodeRangePresenter;
import com.hhzt.vod.smartvod.adapter.MovieSmallPicturePresenter;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.dialog.PayDialogFragment;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.link.MovieDetailContract;
import com.hhzt.vod.smartvod.mvp.presenter.MovieDetailLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.LinearMainLayout;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_movie_detail)
public class MovieDetailFragment extends BaseFragment implements View.OnClickListener, ViewTreeObserver.OnGlobalFocusChangeListener, MovieDetailContract.MovieDetailView {

	//播放
	@ViewInject(R.id.lml_movie_play)
	private LinearMainLayout mLmlMoviePlay;
	@ViewInject(R.id.rl_movie_preview)
	private ReflectItemView mVideoItemView;
	@ViewInject(R.id.nice_video_player)
	private NiceVideoPlayer mNiceVideoPlayer;

	//电影详情(名字、时间、导演、主演、类型、简介)
	@ViewInject(R.id.tv_movie_name)
	private TextView mTvMovieName;
	@ViewInject(R.id.tv_movie_time)
	private TextView mTvMovieTime;
	@ViewInject(R.id.tv_movie_director)
	private TextView mTvMovieDirector;
	@ViewInject(R.id.tv_movie_starring)
	private TextView mTvMovieStarring;
	@ViewInject(R.id.tv_movie_type)
	private TextView mTvMovieType;
	@ViewInject(R.id.tv_movie_description)
	private TextView mTvMovieDescription;

	//全屏、付费
	@ViewInject(R.id.lml_fullscreen_or_pay)
	private LinearMainLayout mLmlFullscreenOrPay;
	@ViewInject(R.id.riv_movie_full_screen)
	private ReflectItemView mRivMovieFullScreen;
	@ViewInject(R.id.btn_movie_full_screen)
	private TextView mTtnMovieFullScreen;
	@ViewInject(R.id.riv_movie_pay)
	private ReflectItemView mRivMoviePay;
	@ViewInject(R.id.tv_movie_watch_for_free_time)
	private TextView mTtvMovieWatchForFreeTime;

	//电视剧
	@ViewInject(R.id.ll_tv_series)
	private LinearLayout mLlTvSeries;
	@ViewInject(R.id.rcv_episode)
	private RecyclerViewTV mRcvEpisode;
	@ViewInject(R.id.rcv_episode_range)
	private RecyclerViewTV mRcvEpisodeRange;

	//推荐电影、相关电影集合
	@ViewInject(R.id.ll_relate_movie)
	private LinearLayout mLlRelateMovie;
	@ViewInject(R.id.ll_recommend_movie)
	private LinearLayout mLlRecommendMovie;
	@ViewInject(R.id.rev_relate_movie)
	private RecyclerViewTV mRcvRelateMovie;
	@ViewInject(R.id.rev_recommend_movie)
	private RecyclerViewTV mRcvRecommendMovie;

	@ViewInject(R.id.mainUpView1)
	private MainUpView mMainUpView;

	private RecyclerViewBridge mRecyclerViewBridge;

	private int mPlayLocation;
	private boolean mNeedPayTag;
	private int mMovieCategoryId;
	private int mMovieProgramId;

	private MovieDetailContract.MovieDetailPresenter mMovieDetailLinkPresenter;
	private MovieDetailCallBack mMovieDetailCallBack;

	public static MovieDetailFragment getInstance(int categoryId, int movieProgramId, boolean mNeedPayTag) {
		MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(MovieDetailActivity.MOVIE_NEED_PAY_TAG, mNeedPayTag);
		bundle.putInt(MovieDetailActivity.MOVIE_CATEGORY_ID, categoryId);
		bundle.putInt(MovieDetailActivity.MOVIE_PROGRAM_ID, movieProgramId);
		movieDetailFragment.setArguments(bundle);
		return movieDetailFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mMovieDetailLinkPresenter = new MovieDetailLinkPresenter(context, InJection.initMovieDetail(), this);
		mMovieDetailLinkPresenter.init();
		mMovieDetailLinkPresenter.start();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mMovieDetailCallBack = (MovieDetailCallBack) activity;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mNeedPayTag = getArguments().getBoolean(MovieDetailActivity.MOVIE_NEED_PAY_TAG, false);
		mMovieCategoryId = getArguments().getInt(MovieDetailActivity.MOVIE_CATEGORY_ID, 0);
		mMovieProgramId = getArguments().getInt(MovieDetailActivity.MOVIE_PROGRAM_ID, 0);

		initView();

		mMovieDetailLinkPresenter.showData(ConfigMgr.getInstance().getGroupID(), mMovieCategoryId, mMovieProgramId);
		initEvent();
		initDefaultFouces();
		updatePayUILogic();
		delayPlayVideo();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		mNiceVideoPlayer.release();
	}

	private void initView() {
		mMainUpView.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
	}

	private void initEvent() {
		mRivMovieFullScreen.setNextFocusLeftId(R.id.riv_movie_pay);
		mRivMovieFullScreen.setNextFocusLeftId(R.id.riv_movie_full_screen);

		mVideoItemView.setOnClickListener(this);
		mRivMovieFullScreen.setOnClickListener(this);
		mRivMoviePay.setOnClickListener(this);
		mVideoItemView.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
		mLmlMoviePlay.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
		mLmlFullscreenOrPay.getViewTreeObserver().addOnGlobalFocusChangeListener(this);

		mRcvEpisode.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvEpisode.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				mPlayLocation = position;
			}
		});


		mRcvEpisodeRange.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvEpisodeRange.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
//				mRcvEpisode.getSelectPostion()
//				mRcvEpisode.setItemSelected(position * MovieDetailLinkPresenter.RAGNGE_SIZE);
//				mRcvEpisode.scrollToPosition(position * MovieDetailLinkPresenter.RAGNGE_SIZE);
			}
		});


		mRcvRelateMovie.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvRelateMovie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				if (mMovieDetailCallBack != null)
					mMovieDetailLinkPresenter.clickOtherMovieDetail(mMovieDetailCallBack, MovieDetailLinkPresenter.TYPE_HOT, position);
			}
		});


		mRcvRecommendMovie.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvRecommendMovie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				if (mMovieDetailCallBack != null)
					mMovieDetailLinkPresenter.clickOtherMovieDetail(mMovieDetailCallBack, MovieDetailLinkPresenter.TYPE_HOT, position);
			}
		});
	}

	private void initDefaultFouces() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mRecyclerViewBridge.setFocusView(mRivMovieFullScreen, ConfigX.SCALE);
				mRivMovieFullScreen.requestLayout();
				mRivMovieFullScreen.requestFocus();
			}
		}, 10);
	}

	private void delayPlayVideo() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mNiceVideoPlayer.start();
			}
		}, 500);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_movie_preview:
				if (mNiceVideoPlayer.isPlaying() || mNiceVideoPlayer.isBufferingPlaying()) {
					mNiceVideoPlayer.pause();
				} else if (mNiceVideoPlayer.isPaused() || mNiceVideoPlayer.isBufferingPaused()) {
					mNiceVideoPlayer.restart();
				} else {
					mNiceVideoPlayer.start();
				}
				break;
			case R.id.riv_movie_full_screen:
				mNiceVideoPlayer.enterFullScreen();
				break;
			case R.id.riv_movie_pay:
				mMovieDetailLinkPresenter.showPayWeb(getChildFragmentManager());
				break;
			default:
				break;
		}
	}

	@Override
	public void onGlobalFocusChanged(View oldFocus, View newFocus) {
		if (newFocus != null)
			newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
		float scale = 1.0f;
		mMainUpView.setFocusView(newFocus, scale);
	}

	@Override
	public void setPresenter(MovieDetailContract.MovieDetailPresenter presenter) {
		mMovieDetailLinkPresenter = presenter;
	}

	@Override
	public void showSmallVideo(List<Clarity> clarities, String movieName, String urlIcon) {
		mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE); // IjkPlayer or MediaPlayer
		TxVideoPlayerController controller = new TxVideoPlayerController(getActivity());
		controller.setTitle(movieName);
		controller.setPreViewLimit(NiceVideoPlayer.PREVIEW_LIMIT_TIME);
		controller.setClarity(clarities, 0);

		/**
		 * 需要支付的影片才需要设置视频预览时间到达逻辑
		 */
		if (mNeedPayTag) {
			controller.setPreviewPayLogic(new IPayLogic() {
				@Override
				public void showPayTips() {
					PayDialogFragment payDialogFragment = PayDialogFragment.getInstance(
							ConfigMgr.getInstance().getUserName(),
							mMovieProgramId,
							ConfigX.MEDIA_TYPE_VOD,
							ConfigX.MEDIA_PAY_PATH_LIMIT);
					payDialogFragment.show(getChildFragmentManager(), "showPayTips");
				}
			});
		}

		Glide.with(getActivity())
				.load(urlIcon)
				.placeholder(R.drawable.img_default)
				.crossFade()
				.into(controller.imageView());
		mNiceVideoPlayer.setController(controller);
	}

	@Override
	public void showMovieDetail(List<EpisodeBean> episodeList, List<String> episodeRangeList, ProgrameDetailBo programDetailBo) {
		mRivMoviePay.setVisibility(programDetailBo.getVipFlag() == ConfigX.NEED_VIP ? View.VISIBLE : View.GONE);
		mTtvMovieWatchForFreeTime.setVisibility((programDetailBo.getVipFlag() == ConfigX.NEED_VIP && programDetailBo.getMediaList().size() <= 1) ? View.VISIBLE : View.GONE);
		if (programDetailBo.getMediaList().size() > 1) {
			mTtnMovieFullScreen.setText(getActivity().getResources().getString(R.string.first_episode));
		}

		//电影详情(名字、时间、导演、主演、类型、简介)
		String writers = String.format(getResources().getString(R.string.movie_detail_director), programDetailBo.getWriters());
		String actors = String.format(getResources().getString(R.string.movie_detail_starring), programDetailBo.getActors());
		String areaname = String.format(getResources().getString(R.string.movie_detail_type), programDetailBo.getAreaname());
		String description = String.format(getResources().getString(R.string.movie_detail_description), programDetailBo.getDescription());
		mTvMovieName.setText(programDetailBo.getName());
		mTvMovieTime.setText(programDetailBo.getYear());
		mTvMovieDirector.setText(writers);
		mTvMovieStarring.setText(actors);
		mTvMovieType.setText(areaname);
		mTvMovieDescription.setText(description);

		LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvEpisode.setLayoutManager(layoutManager);
		mRcvEpisode.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new EpisodePresenter(episodeList));
		mRcvEpisode.setAdapter(generalAdapter);

		LinearLayoutManagerTV layoutManagerRange = new LinearLayoutManagerTV(getActivity().getApplicationContext());
		layoutManagerRange.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvEpisodeRange.setLayoutManager(layoutManagerRange);
		mRcvEpisodeRange.setFocusable(false);
		GeneralAdapter generalAdapterRange = new GeneralAdapter(new EpisodeRangePresenter(episodeRangeList));
		mRcvEpisodeRange.setAdapter(generalAdapterRange);
	}

	@Override
	public void showMovieRelate(ArrayList<MovieInfoData> relevantList) {
		if (relevantList.size() == 0) {
			mLlRelateMovie.setVisibility(View.GONE);
		} else {
			mLlRelateMovie.setVisibility(View.VISIBLE);
			LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			mRcvRelateMovie.setLayoutManager(layoutManager);
			mRcvRelateMovie.setFocusable(false);
			GeneralAdapter generalAdapter = new GeneralAdapter(new MovieSmallPicturePresenter(getContext(), relevantList));
			mRcvRelateMovie.setAdapter(generalAdapter);
		}
	}

	@Override
	public void showMovieHot(ArrayList<MovieInfoData> hotList) {
		if (hotList.size() == 0) {
			mLlRecommendMovie.setVisibility(View.GONE);
		} else {
			mLlRecommendMovie.setVisibility(View.VISIBLE);
			LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			mRcvRecommendMovie.setLayoutManager(layoutManager);
			mRcvRecommendMovie.setFocusable(false);
			GeneralAdapter generalAdapter = new GeneralAdapter(new MovieSmallPicturePresenter(getContext(), hotList));
			mRcvRecommendMovie.setAdapter(generalAdapter);
		}
	}

	@Override
	public void isTvSeries(boolean isTvSeries) {
		mLlTvSeries.setVisibility(isTvSeries ? View.VISIBLE : View.GONE);
	}

	private void updatePayUILogic() {
		if (mNeedPayTag) {
			mMovieDetailLinkPresenter.checkPayResult(mMovieProgramId, ConfigX.MEDIA_TYPE_VOD, new IHttpRetCallBack<PayResultRep>() {

				@Override
				public void onResponseSuccess(CommonRspRetBean bean, PayResultRep payResultRep) {
					Log.d(ConfigX.HHZT_SMART_LOG, "checkPayResult:" + payResultRep.getMsg());
					updatePayUI(!ConfigX.PAY_SUCCESSED.equals(payResultRep.getCode()));
				}

				@Override
				public void onResponseFailed(CommonRspRetBean bean) {
					Log.d(ConfigX.HHZT_SMART_LOG, "checkPayResult:" + bean.msg);
				}

				@Override
				public void onError(String result) {
					Log.d(ConfigX.HHZT_SMART_LOG, "checkPayResult:" + result);
				}

				@Override
				public void onCancelled() {
					Log.d(ConfigX.HHZT_SMART_LOG, "checkPayResult:onCancelled");
				}

				@Override
				public void onFinish() {
					Log.d(ConfigX.HHZT_SMART_LOG, "checkPayResult:onFinish");
				}
			});
		} else {
			updatePayUI(false);
		}
	}

	private void updatePayUI(boolean show) {
		if (show) {
			mRivMoviePay.setVisibility(View.VISIBLE);
			mTtvMovieWatchForFreeTime.setVisibility(View.VISIBLE);
		} else {
			mRivMoviePay.setVisibility(View.GONE);
			mTtvMovieWatchForFreeTime.setVisibility(View.GONE);
		}
	}
}
