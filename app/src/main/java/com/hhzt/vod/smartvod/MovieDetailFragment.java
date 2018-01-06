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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;
import com.hhzt.vod.smartvod.adapter.EpisodePresenter;
import com.hhzt.vod.smartvod.adapter.EpisodeRangePresenter;
import com.hhzt.vod.smartvod.adapter.SmallPicturePresenter;
import com.hhzt.vod.smartvod.api.HttpApiTestEng;
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
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_movie_detail)
public class MovieDetailFragment extends BaseFragment implements IMovieDetail, View.OnClickListener {

    //播放
    @ViewInject(R.id.lml_movie_play)
    private LinearMainLayout lml_movie_play;
    @ViewInject(R.id.iv_movie_detail_icon)
    private ImageView iv_movie_detail_icon;

    //电影详情(名字、时间、导演、主演、类型、简介)
    @ViewInject(R.id.tv_movie_name)
    private TextView tv_movie_name;
    @ViewInject(R.id.tv_movie_time)
    private TextView tv_movie_time;
    @ViewInject(R.id.tv_movie_director)
    private TextView tv_movie_director;
    @ViewInject(R.id.tv_movie_starring)
    private TextView tv_movie_starring;
    @ViewInject(R.id.tv_movie_type)
    private TextView tv_movie_type;
    @ViewInject(R.id.tv_movie_description)
    private TextView tv_movie_description;

    //全屏、付费
    @ViewInject(R.id.lml_fullscreen_or_pay)
    private LinearMainLayout lml_fullscreen_or_pay;
    @ViewInject(R.id.riv_movie_full_screen)
    private ReflectItemView riv_movie_full_screen;
    @ViewInject(R.id.riv_movie_pay)
    private ReflectItemView riv_movie_pay;

    //电视剧
    @ViewInject(R.id.ll_tv_series)
    private LinearLayout ll_tv_series;
    @ViewInject(R.id.rcv_episode)
    private RecyclerViewTV rcv_episode;
    @ViewInject(R.id.rcv_episode_range)
    private RecyclerViewTV rcv_episode_range;

    //推荐电影、相关电影集合
    @ViewInject(R.id.ll_relate_movie)
    private LinearLayout ll_relate_movie;
    @ViewInject(R.id.ll_recommend_movie)
    private LinearLayout ll_recommend_movie;
    @ViewInject(R.id.rev_relate_movie)
    private RecyclerViewTV rev_relate_movie;
    @ViewInject(R.id.rev_recommend_movie)
    private RecyclerViewTV rev_recommend_movie;

    @ViewInject(R.id.mainUpView1)
    private MainUpView mainUpView1;

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
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
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
        //播放电影的背景图片
        x.image().bind(iv_movie_detail_icon, mProgramDetaiContentDataRep.programDetailBo.smallPoster);

        //电影详情(名字、时间、导演、主演、类型、简介)
        String writers = String.format(getResources().getString(R.string.movie_detail_director), mProgramDetaiContentDataRep.programDetailBo.name);
        String actors = String.format(getResources().getString(R.string.movie_detail_starring), mProgramDetaiContentDataRep.programDetailBo.year);
        String areaname = String.format(getResources().getString(R.string.movie_detail_type), mProgramDetaiContentDataRep.programDetailBo.areaname);
        String description = String.format(getResources().getString(R.string.movie_detail_description), mProgramDetaiContentDataRep.programDetailBo.description);
        tv_movie_name.setText(mProgramDetaiContentDataRep.programDetailBo.name);
        tv_movie_time.setText(mProgramDetaiContentDataRep.programDetailBo.year);
        tv_movie_director.setText(writers);
        tv_movie_starring.setText(actors);
        tv_movie_type.setText(areaname);
        tv_movie_description.setText(description);

        int number = mProgramDetaiContentDataRep.programDetailBo.mediaList.size();
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
        rcv_episode.setLayoutManager(layoutManager);
        rcv_episode.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new EpisodePresenter(mEpisodeList));
        rcv_episode.setAdapter(generalAdapter);

        layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv_episode_range.setLayoutManager(layoutManager);
        rcv_episode_range.setFocusable(false);
        generalAdapter = new GeneralAdapter(new EpisodeRangePresenter(mEpisodeRangeList));
        rcv_episode_range.setAdapter(generalAdapter);

        if (mRelateMovie.size() == 0) {
            ll_relate_movie.setVisibility(View.GONE);
        } else {
            ll_relate_movie.setVisibility(View.VISIBLE);
            layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rev_relate_movie.setLayoutManager(layoutManager);
            rev_relate_movie.setFocusable(false);
//        mProgramDetaiContentDataRep.relevantList
            generalAdapter = new GeneralAdapter(new SmallPicturePresenter(mRelateMovie));
            rev_relate_movie.setAdapter(generalAdapter);
        }

        if (mHotMovie.size() == 0) {
            ll_recommend_movie.setVisibility(View.GONE);
        } else {
            ll_recommend_movie.setVisibility(View.VISIBLE);
            layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rev_recommend_movie.setLayoutManager(layoutManager);
            rev_recommend_movie.setFocusable(false);
            generalAdapter = new GeneralAdapter(new SmallPicturePresenter(mHotMovie));
            rev_recommend_movie.setAdapter(generalAdapter);
        }
    }

    private void initEvent() {
        riv_movie_full_screen.setOnClickListener(this);
        lml_movie_play.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.0f;
                mainUpView1.setFocusView(newFocus, scale);
            }
        });
        lml_fullscreen_or_pay.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.0f;
                mainUpView1.setFocusView(newFocus, scale);
            }
        });


        rcv_episode.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }
        });
        rcv_episode.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
//                // 测试.
//                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
//                oldView = itemView;
//                //
//                onViewItemClick(itemView, position);
            }
        });


        rcv_episode_range.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }
        });
        rcv_episode_range.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
//                // 测试.
//                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
//                oldView = itemView;
//                //
//                onViewItemClick(itemView, position);
            }
        });


        rev_relate_movie.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }
        });
        rev_relate_movie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
//                // 测试.
//                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
//                oldView = itemView;
//                //
//                onViewItemClick(itemView, position);
            }
        });


        rev_recommend_movie.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
            }
        });
        rev_recommend_movie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
//                // 测试.
//                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
//                oldView = itemView;
//                //
//                onViewItemClick(itemView, position);
            }
        });
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
                intent.putExtra("url", mProgramDetaiContentDataRep.programDetailBo.mediaList.get(0).filePath);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
