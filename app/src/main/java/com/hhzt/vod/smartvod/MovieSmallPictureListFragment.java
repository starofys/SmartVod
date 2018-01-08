package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;
import com.hhzt.vod.smartvod.adapter.SmallPicturePresenter;
import com.hhzt.vod.smartvod.api.HttpApiTestEng;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2017/12/29.
 *
 * @description TODO
 * @Author 小图
 */
@ContentView(R.layout.fragment_big_picture_list)
public class MovieSmallPictureListFragment extends BaseFragment {

    @ViewInject(R.id.rcv_movie_type)
    private RecyclerViewTV mRcvMovieType;
    @ViewInject(R.id.mainUpView1)
    private MainUpView mMainUpView;

    private int mMovieTypeId;
    private RecyclerViewBridge mRecyclerViewBridge;

    private VodGroupDetailDataRep mVodGroupDetailDataRep;
    private List<MovieInfoData> mMovieInfoList = new ArrayList<>();

    /**
     * @param movieTypeId 电影类型
     * @return
     */
    public static MovieSmallPictureListFragment getIntance(int movieTypeId) {
        MovieSmallPictureListFragment fragment = new MovieSmallPictureListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailActivity.MOVIE_TYPE_ID, movieTypeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieTypeId = getArguments().getInt(MovieDetailActivity.MOVIE_TYPE_ID);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mMainUpView.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
    }

    private void initData() {
        HttpApiTestEng.testHttpVod03(1, 30, mMovieTypeId, new IHttpRetCallBack<VodGroupDetailDataRep>() {
            @Override
            public void onResponseSuccess(CommonRspRetBean bean, VodGroupDetailDataRep vodGroupDetailDataRep) {
                mVodGroupDetailDataRep = vodGroupDetailDataRep;
                mMovieInfoList = mVodGroupDetailDataRep.getProgramSimpleBoList();
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
        GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(getContext(), 2); // 解决快速长按焦点丢失问题.
        gridlayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRcvMovieType.setLayoutManager(gridlayoutManager);
        mRcvMovieType.setFocusable(false);
//        mRcvMovieType.setSelectedItemOffset(111, 111); // 测试移动间距.
        GeneralAdapter generalAdapter = new GeneralAdapter(new SmallPicturePresenter(getContext(), mMovieInfoList));
        mRcvMovieType.setAdapter(generalAdapter);
    }

    private void initEvent() {
        mRcvMovieType.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
//                oldView = itemView;
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, ConfigX.SCALE);
//                oldView = itemView;
            }
        });
        mRcvMovieType.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_TYPE_ID, mMovieTypeId);
                intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID, mMovieInfoList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public float getDimension(int id) {
        return getResources().getDimension(id);
    }
}
