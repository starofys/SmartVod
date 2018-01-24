package com.hhzt.vod.smartvod.adapter.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;


public class FullKeyboardViewHolder extends OpenPresenter.ViewHolder {

	public TextView tvFullKeyBoard;

	public FullKeyboardViewHolder(View itemView) {
		super(itemView);
		tvFullKeyBoard = (TextView) itemView.findViewById(R.id.tv_full_keyboard_item);
	}

}
