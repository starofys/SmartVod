package com.hhzt.vod.smartvod;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;
import com.hhzt.vod.media.Clarity;
import com.hhzt.vod.media.NiceVideoPlayer;
import com.hhzt.vod.media.TxVideoPlayerController;
import com.hhzt.vod.smartvod.adapter.EpisodePresenter;
import com.hhzt.vod.smartvod.adapter.EpisodeRangePresenter;
import com.hhzt.vod.smartvod.adapter.SmallPicturePresenter;
import com.hhzt.vod.smartvod.api.HttpApiTestEng;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.iview.IMovieDetail;
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
public class MovieDetailFragment extends BaseFragment implements IMovieDetail, View.OnClickListener, ViewTreeObserver.OnGlobalFocusChangeListener {

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

	private ProgramDetaiContentDataRep mProgramDetaiContentDataRep;
	private List<MovieInfoData> mRelateMovie = new ArrayList<>();
	private List<MovieInfoData> mHotMovie = new ArrayList<>();
	private List<String> mEpisodeList = new ArrayList<>();
	private List<String> mEpisodeRangeList = new ArrayList<>();

	private int mMovieTypeId;
	private int mMovieDetailId;

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
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMovieTypeId = getArguments().getInt(MovieDetailActivity.MOVIE_TYPE_ID, 0);
		mMovieDetailId = getArguments().getInt(MovieDetailActivity.MOVIE_DETAIL_ID, 0);

		initView();
		requestData();
	}

	private void initView() {
		mMainUpView.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
	}

	private void requestData() {
		HttpApiTestEng.testHttpVod04(29, 99999, new IHttpRetCallBack<ProgramDetaiContentDataRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, ProgramDetaiContentDataRep programDetaiContentDataRep) {
				Log.e("aaaaaonResponseSuccess", programDetaiContentDataRep.toString());
				Log.e("aaaaaonResponseSuccess", mMovieTypeId + "   ====     " + mMovieDetailId);
				mProgramDetaiContentDataRep = programDetaiContentDataRep;
				mRelateMovie.addAll(new ArrayList<MovieInfoData>());
				mHotMovie.addAll(mProgramDetaiContentDataRep.hotList);

				initData();
				bindAdapter();
				initEvent();
				initMedia();
			}

			@Override
			public void onResponseFailed(CommonRspRetBean bean) {
				Log.e("aaaaaonResponseFailed", mMovieTypeId + "   ====     " + mMovieDetailId);
			}

			@Override
			public void onError(String result) {
				Log.e("aaaaaonError", mMovieTypeId + "   ====     " + mMovieDetailId + "     result=" + result);
			}

			@Override
			public void onCancelled() {
				Log.e("aaaaaonCancelled", mMovieTypeId + "   ====     " + mMovieDetailId);
			}

			@Override
			public void onFinish() {
				Log.e("aaaaaonFinish", mMovieTypeId + "   ====     " + mMovieDetailId);
			}
		});
	}

	private void initData() {
		//电影详情(名字、时间、导演、主演、类型、简介)
		String writers = String.format(getResources().getString(R.string.movie_detail_director), mProgramDetaiContentDataRep.programDetailBo.getName());
		String actors = String.format(getResources().getString(R.string.movie_detail_starring), mProgramDetaiContentDataRep.programDetailBo.getYear());
		String areaname = String.format(getResources().getString(R.string.movie_detail_type), mProgramDetaiContentDataRep.programDetailBo.getAreaname());
		String description = String.format(getResources().getString(R.string.movie_detail_description), mProgramDetaiContentDataRep.programDetailBo.getDescription());
		mTvMovieName.setText(mProgramDetaiContentDataRep.programDetailBo.getName());
		mTvMovieTime.setText(mProgramDetaiContentDataRep.programDetailBo.getYear());
		mTvMovieDirector.setText(writers);
		mTvMovieStarring.setText(actors);
		mTvMovieType.setText(areaname);
		mTvMovieDescription.setText(description);

		int number = mProgramDetaiContentDataRep.programDetailBo.getMediaList().size();
		for (int i = 1; i <= number; i++) {
			mEpisodeList.add(i + "");
		}

		boolean divided = number % 10 == 0;
		if (divided) {
			number = number / 10;
		} else {
			number = number / 10 + 1;
		}
		for (int i = 0; i < number; i++) {
			mEpisodeRangeList.add("" + (i * 10 + 1) + "-" + (i * 10 + 10));
		}
	}

	private void bindAdapter() {
		LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvEpisode.setLayoutManager(layoutManager);
		mRcvEpisode.setFocusable(false);
		GeneralAdapter generalAdapter = new GeneralAdapter(new EpisodePresenter(mEpisodeList));
		mRcvEpisode.setAdapter(generalAdapter);

		layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRcvEpisodeRange.setLayoutManager(layoutManager);
		mRcvEpisodeRange.setFocusable(false);
		generalAdapter = new GeneralAdapter(new EpisodeRangePresenter(mEpisodeRangeList));
		mRcvEpisodeRange.setAdapter(generalAdapter);

		if (mRelateMovie.size() == 0) {
			mLlRelateMovie.setVisibility(View.GONE);
		} else {
			mLlRelateMovie.setVisibility(View.VISIBLE);
			layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			mRcvRelateMovie.setLayoutManager(layoutManager);
			mRcvRelateMovie.setFocusable(false);
//        mProgramDetaiContentDataRep.relevantList
			generalAdapter = new GeneralAdapter(new SmallPicturePresenter(getContext(), mRelateMovie));
			mRcvRelateMovie.setAdapter(generalAdapter);
		}

		if (mHotMovie.size() == 0) {
			mLlRecommendMovie.setVisibility(View.GONE);
		} else {
			mLlRecommendMovie.setVisibility(View.VISIBLE);
			layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			mRcvRecommendMovie.setLayoutManager(layoutManager);
			mRcvRecommendMovie.setFocusable(false);
			generalAdapter = new GeneralAdapter(new SmallPicturePresenter(getContext(), mHotMovie));
			mRcvRecommendMovie.setAdapter(generalAdapter);
		}
	}

	private void initEvent() {
		mRivMovieFullScreen.setOnClickListener(this);
		mLmlMoviePlay.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
		mLmlFullscreenOrPay.getViewTreeObserver().addOnGlobalFocusChangeListener(this);


		mRcvEpisode.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvEpisode.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				//todo
			}
		});


		mRcvEpisodeRange.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvEpisodeRange.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				//todo
			}
		});


		mRcvRelateMovie.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvRelateMovie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				//todo
			}
		});


		mRcvRecommendMovie.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
		mRcvRecommendMovie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				//todo
			}
		});
	}

	private void initMedia() {
		mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE); // IjkPlayer or MediaPlayer
		TxVideoPlayerController controller = new TxVideoPlayerController(getActivity());
		controller.setTitle(mProgramDetaiContentDataRep.programDetailBo.getName());
		controller.setLenght(117000);
		controller.setClarity(getClarites(), 0);
		Glide.with(getActivity())
				.load(mProgramDetaiContentDataRep.programDetailBo.getSmallPoster())
				.placeholder(R.drawable.img_default)
				.crossFade()
				.into(controller.imageView());
		mNiceVideoPlayer.setController(controller);
	}

	public List<Clarity> getClarites() {
		List<Clarity> clarities = new ArrayList<>();
		clarities.add(new Clarity(getString(R.string.media_clarities_normal), getString(R.string.media_clarities_270p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
		clarities.add(new Clarity(getString(R.string.media_clarities_high), getString(R.string.media_clarities_480p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
		clarities.add(new Clarity(getString(R.string.media_clarities_super), getString(R.string.media_clarities_720p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
		clarities.add(new Clarity(getString(R.string.media_clarities_blue), getString(R.string.media_clarities_1080p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
		return clarities;
	}

	@Override
	public void showSmallVideo() {

	}

	@Override
	public void showMovieDetail() {

	}

	@Override
	public void showMovieItemTips() {

	}

	@Override
	public void showMovieRecommend() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.riv_movie_full_screen:
				Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
				intent.putExtra(VideoPlayerActivity.MOVIEW_PLAYER_URL, mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath());
				startActivity(intent);
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
}
