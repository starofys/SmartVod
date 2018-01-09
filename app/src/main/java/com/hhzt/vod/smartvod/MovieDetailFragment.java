package com.hhzt.vod.smartvod;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhzt.vod.api.otherBean.EpisodeBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;
import com.hhzt.vod.media.Clarity;
import com.hhzt.vod.media.NiceVideoPlayer;
import com.hhzt.vod.media.TxVideoPlayerController;
import com.hhzt.vod.smartvod.adapter.EpisodePresenter;
import com.hhzt.vod.smartvod.adapter.EpisodeRangePresenter;
import com.hhzt.vod.smartvod.adapter.SmallPicturePresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
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
	@ViewInject(R.id.riv_movie_pay)
	private ReflectItemView mRivMoviePay;

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
	private int mMovieTypeId;
	private int mMovieDetailId;

	private MovieDetailContract.MovieDetailPresenter mMovieDetailLinkPresenter;
	private MovieDetailCallBack mMovieDetailCallBack;

	public static MovieDetailFragment getInstance(int movieTypeId, int movieDetailId) {
		MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(MovieDetailActivity.MOVIE_TYPE_ID, movieTypeId);
		bundle.putInt(MovieDetailActivity.MOVIE_DETAIL_ID, movieDetailId);
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
		mMovieDetailLinkPresenter.start();
		mMovieDetailLinkPresenter.init();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mMovieDetailCallBack = (MovieDetailCallBack) activity;
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMovieTypeId = getArguments().getInt(MovieDetailActivity.MOVIE_TYPE_ID, 0);
		mMovieDetailId = getArguments().getInt(MovieDetailActivity.MOVIE_DETAIL_ID, 0);

		initView();
		mMovieDetailLinkPresenter.showData(ConfigX.PROGRAM_GROUP_ID, 29, 99999);
//		mMovieDetailLinkPresenter.showData(ConfigX.PROGRAM_GROUP_ID, mMovieTypeId, mMovieDetailId);
		initEvent();
	}

	private void initView() {
		mMainUpView.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
	}

	private void initEvent() {
		mRivMovieFullScreen.setOnClickListener(this);
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
				//todo
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
				if (mMovieDetailCallBack != null) mMovieDetailLinkPresenter.clickOtherMovieDetail(mMovieDetailCallBack, MovieDetailLinkPresenter.TYPE_HOT, position);
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
				if (mMovieDetailCallBack != null) mMovieDetailLinkPresenter.clickOtherMovieDetail(mMovieDetailCallBack, MovieDetailLinkPresenter.TYPE_HOT, position);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.riv_movie_full_screen:
				mMovieDetailLinkPresenter.toVideoPlayerActivity(getActivity(), VideoPlayerActivity.class, mPlayLocation);
				break;
			default:
				break;
		}
	}

	@Override
	public void onGlobalFocusChanged(View oldFocus, View newFocus) {
		if (newFocus != null)
			newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
		float scale = ConfigX.SCALE;
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
		controller.setLenght(117000);
		controller.setClarity(clarities, 0);
		Glide.with(getActivity())
				.load(urlIcon)
				.placeholder(R.drawable.img_default)
				.crossFade()
				.into(controller.imageView());
		mNiceVideoPlayer.setController(controller);
	}

	@Override
	public void showMovieDetail(List<EpisodeBean> episodeList, List<String> episodeRangeList, ProgrameDetailBo programDetailBo) {
		//电影详情(名字、时间、导演、主演、类型、简介)
		String writers = String.format(getResources().getString(R.string.movie_detail_director), programDetailBo.getName());
		String actors = String.format(getResources().getString(R.string.movie_detail_starring), programDetailBo.getYear());
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
			LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			mRcvRelateMovie.setLayoutManager(layoutManager);
			mRcvRelateMovie.setFocusable(false);
			GeneralAdapter generalAdapter = new GeneralAdapter(new SmallPicturePresenter(getContext(), relevantList));
			mRcvRelateMovie.setAdapter(generalAdapter);
		}
	}

	@Override
	public void showMovieHot(ArrayList<MovieInfoData> hotList) {
		if (hotList.size() == 0) {
			mLlRecommendMovie.setVisibility(View.GONE);
		} else {
			mLlRecommendMovie.setVisibility(View.VISIBLE);
			LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			mRcvRecommendMovie.setLayoutManager(layoutManager);
			mRcvRecommendMovie.setFocusable(false);
			GeneralAdapter generalAdapter = new GeneralAdapter(new SmallPicturePresenter(getContext(), hotList));
			mRcvRecommendMovie.setAdapter(generalAdapter);
		}
	}
}
