package com.hhzt.vod.smartvod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @Author zengxiaoping
 */
public class SearchMovieKeyPresenter extends OpenPresenter {

	private List<SimpleRepBean> mMovieNameList;
	private GeneralAdapter mAdapter;

	public SearchMovieKeyPresenter(List<SimpleRepBean> mEpisodeList) {
		this.mMovieNameList = mEpisodeList;
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	public void addDatas(List<SimpleRepBean> episodeList) {
		this.mMovieNameList = episodeList;
		this.mAdapter.notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return mMovieNameList.size();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie_key, parent, false);
		return new SearchMovieKeyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		SearchMovieKeyViewHolder keyViewHolder = (SearchMovieKeyViewHolder) viewHolder;
		keyViewHolder.tvMovieName.setText(mMovieNameList.get(position).getName());
	}

}
