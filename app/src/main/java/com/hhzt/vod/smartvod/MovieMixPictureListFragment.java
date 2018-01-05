package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;
import com.hhzt.vod.smartvod.adapter.BigPicturePresenter;
import com.hhzt.vod.smartvod.adapter.SmallPicturePresenter;
import com.hhzt.vod.smartvod.api.HttpApiTestEng;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxaioping on 2017/12/29.
 *
 * @description TODO
 * @Author 混合大小图
 */
public class MovieMixPictureListFragment extends BaseFragment {

    public static final String MOVIE_TYPE = "movie_type";

    private View mView;
    private RecyclerViewTV mRcvMovieBigPicture;
    private RecyclerViewTV mRcvMovieSmallPicture;
    private MainUpView mMainUpView;

    private int mMovieType;
    private RecyclerViewBridge mRecyclerViewBridge;

    private VodGroupDetailDataRep mVodGroupDetailDataRep;
    private List<MovieInfoData> mMovieBigPictureList = new ArrayList<>();
    private List<MovieInfoData> mMovieSmallPictureList = new ArrayList<>();

    /**
     * @param movieType 电影类型
     * @return
     */
    public static MovieMixPictureListFragment getIntance(int movieType) {
        MovieMixPictureListFragment fragment = new MovieMixPictureListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_TYPE, movieType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieType = getArguments().getInt(MOVIE_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mix_picture_list, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        bindAdater();
        initEvent();
    }

    private void initView() {
        mMainUpView = mView.findViewById(R.id.mainUpView1);
        mRcvMovieBigPicture = mView.findViewById(R.id.rcv_movie_big_picture);
        mRcvMovieSmallPicture = mView.findViewById(R.id.rcv_movie_small_picture);

        if (null != mMainUpView) {
            mMainUpView.setEffectBridge(new RecyclerViewBridge());
            // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
            mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
            mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
        }
    }

    private void initData() {
        HttpApiTestEng.testHttpVod03(1, 30, mMovieType, new IHttpRetCallBack<VodGroupDetailDataRep>() {
            @Override
            public void onResponseSuccess(CommonRspRetBean bean, VodGroupDetailDataRep vodGroupDetailDataRep) {
                mVodGroupDetailDataRep = vodGroupDetailDataRep;

                if (mMovieBigPictureList.size() == 0) {
                    ArrayList<MovieInfoData> programSimpleBoList = mVodGroupDetailDataRep.getProgramSimpleBoList();
                    int size = programSimpleBoList.size();
                    for (int i = 0; i < size; i++) {
                        if (i <= 1) {
                            mMovieBigPictureList.add(programSimpleBoList.get(i));
                        } else {
                            mMovieSmallPictureList.add(programSimpleBoList.get(i));
                        }
                    }
                } else {
                    mMovieSmallPictureList.addAll(mVodGroupDetailDataRep.getProgramSimpleBoList());
                }
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
        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcvMovieBigPicture.setLayoutManager(layoutManager);
        mRcvMovieBigPicture.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new BigPicturePresenter(mMovieBigPictureList));
        mRcvMovieBigPicture.setAdapter(generalAdapter);

        GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 2); // 解决快速长按焦点丢失问题.
        gridlayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRcvMovieSmallPicture.setLayoutManager(gridlayoutManager);
        mRcvMovieSmallPicture.setFocusable(false);
        mRcvMovieSmallPicture.setSelectedItemOffset(111, 111); // 测试移动间距.
        generalAdapter = new GeneralAdapter(new SmallPicturePresenter(mMovieSmallPictureList));
        mRcvMovieSmallPicture.setAdapter(generalAdapter);
    }

    private void initEvent() {
        mRcvMovieBigPicture.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
        mRcvMovieBigPicture.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_TYPE_ID, mMovieType);
                if(position<=1){
                    intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID, mMovieBigPictureList.get(position).id);
                }else{
                    intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID, mMovieSmallPictureList.get(position).id);
                }
                startActivity(intent);
            }
        });


        mRcvMovieSmallPicture.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
        mRcvMovieSmallPicture.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
               Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_TYPE_ID, mMovieType);
                if(position<=1){
                    intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID, mMovieBigPictureList.get(position).id);
                }else{
                    intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID, mMovieSmallPictureList.get(position).id);
                }
                startActivity(intent);
            }
        });
    }
}
