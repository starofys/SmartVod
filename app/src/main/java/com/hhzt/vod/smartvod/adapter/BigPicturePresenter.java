package com.hhzt.vod.smartvod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import org.xutils.x;

import java.util.List;

/**
 * 大图
 */
public class BigPicturePresenter extends OpenPresenter {

    private  List<MovieInfoData> mMovieInfoList;
    private GeneralAdapter mAdapter;

    public BigPicturePresenter( List<MovieInfoData> mMovieInfoList) {
        this.mMovieInfoList = mMovieInfoList;
    }

    @Override
    public void setAdapter(GeneralAdapter adapter) {
        this.mAdapter = adapter;
    }

    /**
     * 用于数据加载更多测试.
     */
    public void addDatas( List<MovieInfoData> mMovieInfoList) {
        this.mMovieInfoList = mMovieInfoList;
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovieInfoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_type_big_picture_fragment, parent, false);
        return new MovieGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        MovieGridViewHolder movieGridViewHolder = (MovieGridViewHolder) viewHolder;

        MovieInfoData movieInfoBean = mMovieInfoList.get(position);
        movieGridViewHolder.ivMovieName.setText(movieInfoBean.getName());

        //满足有付费的标签
//        movieGridViewHolder.ivPayLable.setVisibility(true ? View.VISIBLE : View.GONE);

        //设置背景图片
        x.image().bind(movieGridViewHolder.ivMoviePicture, movieInfoBean.getSmallPoster());
    }

}
