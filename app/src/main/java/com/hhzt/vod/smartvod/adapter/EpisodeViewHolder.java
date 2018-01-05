package com.hhzt.vod.smartvod.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;


public class EpisodeViewHolder extends OpenPresenter.ViewHolder {

	public ImageView ivPayLable;
	public TextView tvEpisode;

	public EpisodeViewHolder(View itemView) {
		super(itemView);
		ivPayLable = (ImageView)itemView.findViewById(R.id.iv_movie_label_item);
		tvEpisode = (TextView)itemView.findViewById(R.id.tv_movie_episode_item);
	}

}
