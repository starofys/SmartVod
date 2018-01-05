package com.hhzt.vod.smartvod.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;


public class MovieGridViewHolder extends OpenPresenter.ViewHolder {
	
	public ImageView ivMoviePicture;
	public ImageView ivPayLable;
	public TextView ivMovieName;

	public MovieGridViewHolder(View itemView) {
		super(itemView);
		ivMoviePicture = (ImageView)itemView.findViewById(R.id.iv_movie_picture_item);
		ivPayLable = (ImageView)itemView.findViewById(R.id.iv_movie_label_item);
		ivMovieName = (TextView)itemView.findViewById(R.id.tv_movie_name_item);
	}

}
