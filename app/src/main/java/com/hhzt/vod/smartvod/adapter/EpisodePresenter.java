package com.hhzt.vod.smartvod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.bean.EpisodeBean;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * 集数
 */
public class EpisodePresenter extends OpenPresenter {

    private List<String> mEpisodeList;
    private GeneralAdapter mAdapter;

    public EpisodePresenter(List<String> mEpisodeList) {
        this.mEpisodeList = mEpisodeList;
    }

    @Override
    public void setAdapter(GeneralAdapter adapter) {
        this.mAdapter = adapter;
    }

    /**
     * 用于数据加载更多测试.
     */
    public void addDatas(List<String> episodeList) {
        this.mEpisodeList = episodeList;
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEpisodeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        EpisodeViewHolder episodeViewHolder = (EpisodeViewHolder) viewHolder;

        episodeViewHolder.tvEpisode.setText(mEpisodeList.get(position));

        //满足有付费的标签
//        movieGridViewHolder.ivPayLable.setVisibility(true ? View.VISIBLE : View.GONE);
    }

}
