package com.hhzt.vod.smartvod.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.view.LinearMainLayout;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;


public class T9KeyboardViewHolder extends OpenPresenter.ViewHolder {
	public RelativeLayout mRlContent;

	public LinearMainLayout mLlT9;
	public ReflectItemView mRivT9;
	public TextView mTvKeyNumber;
	public TextView mTvKeyLetter;

	public LinearMainLayout mLlT9Expanding;
	public ReflectItemView mRivT9ExpandingUp;
	public TextView mTvT9ExpandingUp;
	public ReflectItemView mRivT9ExpandingLeft;
	public TextView mTvT9ExpandingLeft;
	public ReflectItemView mRivT9ExpandingDown;
	public TextView mTvT9ExpandingDown;
	public ReflectItemView mRivT9ExpandingRight;
	public TextView mTvT9ExpandingRight;

	public T9KeyboardViewHolder(View itemView) {
		super(itemView);
		mRlContent = (RelativeLayout) itemView.findViewById(R.id.rl_content);

		mLlT9 = (LinearMainLayout) itemView.findViewById(R.id.ll_t9);
		mRivT9 = (ReflectItemView) itemView.findViewById(R.id.riv_t9);
		mTvKeyNumber = (TextView) itemView.findViewById(R.id.tv_key_number);
		mTvKeyLetter = (TextView) itemView.findViewById(R.id.tv_key_letter);

		mLlT9Expanding = (LinearMainLayout) itemView.findViewById(R.id.ll_t9_expanding);
		mRivT9ExpandingUp = (ReflectItemView) itemView.findViewById(R.id.riv_t9_expanding_up);
		mTvT9ExpandingUp = (TextView) itemView.findViewById(R.id.tv_t9_expanding_up);
		mRivT9ExpandingLeft = (ReflectItemView) itemView.findViewById(R.id.riv_t9_expanding_left);
		mTvT9ExpandingLeft = (TextView) itemView.findViewById(R.id.tv_t9_expanding_left);
		mRivT9ExpandingDown = (ReflectItemView) itemView.findViewById(R.id.riv_t9_expanding_down);
		mTvT9ExpandingDown = (TextView) itemView.findViewById(R.id.tv_t9_expanding_down);
		mRivT9ExpandingRight = (ReflectItemView) itemView.findViewById(R.id.riv_t9_expanding_right);
		mTvT9ExpandingRight = (TextView) itemView.findViewById(R.id.tv_t9_expanding_right);
	}

}
