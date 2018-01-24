package com.hhzt.vod.smartvod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.adapter.viewHolder.FullKeyboardViewHolder;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.ArrayList;

/**
 * 全键盘
 * zengxiaoping
 */
public class FullKeyboardPresenter extends OpenPresenter {

	private ArrayList<String> mKeyList;
	private GeneralAdapter mAdapter;
	private Context mContext;

	public FullKeyboardPresenter(Context context, ArrayList<String> mEpisodeList) {
		this.mKeyList = mEpisodeList;
		this.mContext = context;
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	public void addDatas(ArrayList<String> episodeList) {
		this.mKeyList = episodeList;
		this.mAdapter.notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return mKeyList.size();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_keyboard, parent, false);
		return new FullKeyboardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		FullKeyboardViewHolder episodeViewHolder = (FullKeyboardViewHolder) viewHolder;
		episodeViewHolder.tvFullKeyBoard.setText(mKeyList.get(position));
	}

}
