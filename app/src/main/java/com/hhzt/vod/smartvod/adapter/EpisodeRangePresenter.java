package com.hhzt.vod.smartvod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.List;

/**
 * 集数范围
 * zengxiaoping
 */
public class EpisodeRangePresenter extends OpenPresenter {

	private List<String> mEpisodeList;
	private GeneralAdapter mAdapter;

	public EpisodeRangePresenter(List<String> mEpisodeList) {
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
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_episode_range, parent, false);
		return new EpisodeViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		EpisodeViewHolder episodeViewHolder = (EpisodeViewHolder) viewHolder;
		episodeViewHolder.tvEpisode.setText(mEpisodeList.get(position));
	}

}
