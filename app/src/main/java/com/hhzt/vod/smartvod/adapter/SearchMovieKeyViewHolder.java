package com.hhzt.vod.smartvod.adapter;

import android.view.View;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;


public class SearchMovieKeyViewHolder extends OpenPresenter.ViewHolder {

	public TextView tvMovieName;

	public SearchMovieKeyViewHolder(View itemView) {
		super(itemView);
		tvMovieName = (TextView)itemView.findViewById(R.id.tv_search_movie_name_item);
	}

}
