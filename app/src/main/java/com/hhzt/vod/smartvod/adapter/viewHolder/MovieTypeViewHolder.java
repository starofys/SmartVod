package com.hhzt.vod.smartvod.adapter.viewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;


public class MovieTypeViewHolder extends OpenPresenter.ViewHolder {

	public RelativeLayout mRlContainer;
	public TextView ivMovieTypeName;
	public View viewBackgroud;

	public MovieTypeViewHolder(View itemView) {
		super(itemView);
		mRlContainer = (RelativeLayout) itemView.findViewById(R.id.rl_container);
		ivMovieTypeName = (TextView)itemView.findViewById(R.id.tv_movie_type);
		viewBackgroud = (View)itemView.findViewById(R.id.view_backgroud);
	}

}
