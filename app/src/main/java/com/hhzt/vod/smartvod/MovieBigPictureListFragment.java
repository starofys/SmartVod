package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;
import com.hhzt.vod.smartvod.adapter.BigPicturePresenter;
import com.hhzt.vod.smartvod.api.HttpApiTestEng;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
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
 * @Author 大图
 */

@ContentView(R.layout.fragment_big_picture_list)
public class MovieBigPictureListFragment extends BaseFragment {
    public static final String MOVIE_TYPE = "movie_type";

    @ViewInject(R.id.rcv_movie_type)
    private RecyclerViewTV rcv_movie_type;
    @ViewInject(R.id.mainUpView1)
    private MainUpView mainUpView1;

    private int mMovieType;
    private RecyclerViewBridge mRecyclerViewBridge;

    private VodGroupDetailDataRep mVodGroupDetailDataRep;
    private List<MovieInfoData> mMovieInfoList = new ArrayList<>();

    /**
     * @param movieType 电影类型
     * @return
     */
    public static MovieBigPictureListFragment getIntance(int movieType) {
        MovieBigPictureListFragment fragment = new MovieBigPictureListFragment();
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        requestData();

        rcv_movie_type.requestLayout();
        rcv_movie_type.requestFocus();
    }

    private void initView() {
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
    }

    private void requestData() {
        HttpApiTestEng.testHttpVod03(1, 30, mMovieType, new IHttpRetCallBack<VodGroupDetailDataRep>() {
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
        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv_movie_type.setLayoutManager(layoutManager);
        rcv_movie_type.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new BigPicturePresenter(mMovieInfoList));
        rcv_movie_type.setAdapter(generalAdapter);
//        rcv_movie_type.setSelectedItemOffset(111, 111); // 测试移动间距.
    }

    private void initEvent() {
        rcv_movie_type.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
        rcv_movie_type.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_TYPE_ID, mMovieType);
                intent.putExtra(MovieDetailActivity.MOVIE_DETAIL_ID, mMovieInfoList.get(position).id);
                startActivity(intent);
            }
        });
    }

    public float getDimension(int id) {
        return getResources().getDimension(id);
    }
}
