package com.hhzt.vod.smartvod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.logiclayer.keydispatch.KeyBroadcastSender;
import com.hhzt.vod.logiclayer.keydispatch.KeyFactoryConst;
import com.hhzt.vod.smartvod.adapter.HomeBigPicturePresenter;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieListContract;
import com.hhzt.vod.smartvod.mvp.link.InJection;
import com.hhzt.vod.smartvod.mvp.presenter.HomeMovieListLinkPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.hhzt.vod.viewlayer.androidtvwidget.view.MainUpView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by zengxiaoping on 2017/12/29.
 *
 * @description TODO
 * @Author 大图
 */

@ContentView(R.layout.fragment_big_picture_list)
public class MovieBigPictureListFragment extends MovieListFragment implements HomeMovieListContract.HomeMovieListView {

    @ViewInject(R.id.rcv_movie_item_container)
    private RecyclerViewTV mRcvMovieItemContainer;
    @ViewInject(R.id.mainUpView)
    private MainUpView mMainUpView;

    private int mMovieTypeId;
    private RecyclerViewBridge mRecyclerViewBridge;

    private HomeMovieListContract.HomeMovieListPresenter mHomeMovieListLinkPresenter;

    private MovieBroadCastReceiver mMovieBroadCastReceiver;

    private int mSelectRecyclerIndex;

    /**
     * @param movieTypeId 电影类型
     * @return
     */
    public static MovieBigPictureListFragment getIntance(int movieTypeId) {
        MovieBigPictureListFragment fragment = new MovieBigPictureListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailActivity.MOVIE_TYPE_ID, movieTypeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHomeMovieListLinkPresenter = new HomeMovieListLinkPresenter(context, InJection.initHomeTypeList(), this);
        mHomeMovieListLinkPresenter.init();
        mHomeMovieListLinkPresenter.start();
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
        mHomeMovieListLinkPresenter.showData(ConfigX.PROGRAM_GROUP_ID, 1, 30, mMovieTypeId);

        mMovieBroadCastReceiver = new MovieBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter(KeyFactoryConst.KEY_LISTEN_ACTION);
        getActivity().registerReceiver(mMovieBroadCastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(mMovieBroadCastReceiver);
    }

    private void initView() {
        mMainUpView.setEffectBridge(new RecyclerViewBridge());
        mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.bg_border_selector);
    }

    private void bindAdater(List<MovieInfoData> movieInfoList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcvMovieItemContainer.setLayoutManager(layoutManager);
        mRcvMovieItemContainer.setHasFixedSize(true);
        mRcvMovieItemContainer.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new HomeBigPicturePresenter(getContext(), movieInfoList));
        mRcvMovieItemContainer.setAdapter(generalAdapter);
    }

    private void initEvent() {
        mRcvMovieItemContainer.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mSelectRecyclerIndex = position;
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
        mRcvMovieItemContainer.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                mHomeMovieListLinkPresenter.toMovieDetail(getActivity(), MovieDetailActivity.class, position, mMovieTypeId);
            }
        });
        mRcvMovieItemContainer.setOnItemKeyListener(new RecyclerViewTV.OnItemKeyListener() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                int index = mRcvMovieItemContainer.getSelectPostion();
                View itemView = mRcvMovieItemContainer.getFocusedChild();
                if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
                    if (index == 0) {
                        mRecyclerViewBridge.setUnFocusView(itemView);
                        KeyBroadcastSender.getInstance().sendLeftBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
                        return true;
                    }
                } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
                    if (index % 2 == 0) {
                        mRecyclerViewBridge.setUnFocusView(itemView);
                        KeyBroadcastSender.getInstance().sendUpBordKey(KeyFactoryConst.KEY_SOURCE_ITEM_CONTENT);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void setPresenter(HomeMovieListContract.HomeMovieListPresenter presenter) {
        mHomeMovieListLinkPresenter = presenter;
    }

    @Override
    public void showData(List<MovieInfoData> movieInfoData) {
        bindAdater(movieInfoData);
        initEvent();
    }

    private final class MovieBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (KeyFactoryConst.KEY_LISTEN_ACTION.equals(intent.getAction())) {
                String keyType = intent.getStringExtra(KeyFactoryConst.KEY_CODE_TAG);
                switch (keyType) {
                    case KeyFactoryConst.KEY_CODE_DOWN: {
                        View view = mRcvMovieItemContainer.getChildAt(mSelectRecyclerIndex);
                        view.requestLayout();
                        view.requestFocus();
                    }
                    break;
                    case KeyFactoryConst.KEY_CODE_RIGHT: {
                        View view = mRcvMovieItemContainer.getChildAt(0);
                        if (null != view) {
                            view.requestLayout();
                            view.requestFocus();
                        }
                    }
                    break;
                    default:
                        break;
                }
            }
        }
    }
}
