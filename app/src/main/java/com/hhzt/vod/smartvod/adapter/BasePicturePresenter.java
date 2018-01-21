package com.hhzt.vod.smartvod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/11.
 *
 * @Author zengxiaoping
 */

public abstract class BasePicturePresenter extends OpenPresenter {
	private Context mContext;
	private List<MovieInfoData> mMovieInfoList;
	private GeneralAdapter mAdapter;

	public BasePicturePresenter(Context context, List<MovieInfoData> mMovieInfoList) {
		this.mContext = context;
		this.mMovieInfoList = mMovieInfoList;
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	public void addDatas(List<MovieInfoData> mMovieInfoList) {
		this.mMovieInfoList = mMovieInfoList;
		this.mAdapter.notifyDataSetChanged();
	}

	public List<MovieInfoData> getDatas() {
		return mMovieInfoList;
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
		View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
		return new MovieGridViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		MovieGridViewHolder movieGridViewHolder = (MovieGridViewHolder) viewHolder;

		MovieInfoData movieInfoBean = mMovieInfoList.get(position);
		movieGridViewHolder.ivMovieName.setText(movieInfoBean.getName());

		movieGridViewHolder.ivPayLable.setVisibility(movieInfoBean.getVipFlag() == ConfigX.NEED_VIP ? View.VISIBLE : View.GONE);

		Glide.with(mContext)
				.load(movieInfoBean.getSmallPoster())
				.placeholder(R.drawable.img_default)
				.crossFade()
				.into(movieGridViewHolder.ivMoviePicture);
	}

	abstract int getLayoutId();
}
