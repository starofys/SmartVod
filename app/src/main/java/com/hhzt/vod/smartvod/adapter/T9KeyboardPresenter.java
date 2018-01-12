package com.hhzt.vod.smartvod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.ArrayList;

/**
 * T9键盘
 * zengxiaoping
 */
public class T9KeyboardPresenter extends OpenPresenter {

	private ArrayList<KeyBean> mKeyList;
	private GeneralAdapter mAdapter;
	private Context mContext;

	public T9KeyboardPresenter(Context context, ArrayList<KeyBean> mEpisodeList) {
		this.mKeyList = mEpisodeList;
		this.mContext = context;
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
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
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t9_keyboard, parent, false);
		return new T9KeyboardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		T9KeyboardViewHolder t9KeyboardViewHolder = (T9KeyboardViewHolder) viewHolder;
	}

}
