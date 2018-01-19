package com.hhzt.vod.smartvod.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.otherBean.EpisodeBean;
import com.hhzt.vod.logiclayer.App;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.List;

/**
 * 集数
 * zengxiaoping
 */
public class EpisodePresenter extends OpenPresenter {

	private List<EpisodeBean> mEpisodeList;
	private GeneralAdapter mAdapter;
	private int mSelectPosition = 0;
	private Resources mResource;

	public EpisodePresenter(List<EpisodeBean> mEpisodeList) {
		this.mEpisodeList = mEpisodeList;
		mResource = App.mContext.getResources();
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	public void setSelectPosition(int selectPosition) {
		this.mSelectPosition = selectPosition;
		mAdapter.notifyDataSetChanged();
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
		episodeViewHolder.tvEpisode.setText(mEpisodeList.get(position).getEpisode());
		episodeViewHolder.ivPayLable.setVisibility(mEpisodeList.get(position).getTable() != 0 ? View.VISIBLE : View.GONE);
		episodeViewHolder.tvEpisode.setTextColor(position == mSelectPosition ? mResource.getColor(R.color.blue) : mResource.getColor(R.color.tv_white));
	}

}
