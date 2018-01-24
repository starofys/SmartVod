package com.hhzt.vod.smartvod.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.logiclayer.App;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.List;

/**
 * 抽象的基类适配器
 * Created by zengxiaoping on 2018/1/24.
 *
 * @Author zengxiaoping
 */

public abstract class BasePresenter<T> extends OpenPresenter {
	private Context mContext;
	private List<T> mDataList;
	private GeneralAdapter mAdapter;

	private Resources mResource;
	private int mSelectPosition = 0;

	public BasePresenter(Context context, List<T> dataList) {
		this.mContext = context;
		this.mDataList = dataList;
		mResource = App.mContext.getResources();
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	@Override
	public int getItemCount() {
		return mDataList.size();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(createLayoutId(), parent, false);
		return createViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		super.onBindViewHolder(viewHolder, position);
		bindViewHolder(viewHolder, position, getDataByPosition(position));
	}

	public void setSelectPosition(int selectPosition) {
		this.mSelectPosition = selectPosition;
		mAdapter.notifyDataSetChanged();
	}

	public void setDataList(List<T> dataList) {
		this.mDataList = dataList;
		mAdapter.notifyDataSetChanged();
	}

	public void addDataList(List<T> dataList) {
		this.mDataList.addAll(dataList);
		mAdapter.notifyDataSetChanged();
	}

	public void addData(T t, int position) {
		if (position < 0) mDataList.add(t);
		else mDataList.add(position, t);
	}

	public void removeData(int position) {
		mDataList.remove(position);
		mAdapter.notifyDataSetChanged();
	}

	public T getDataByPosition(int position) {
		return mDataList.get(position);
	}

	public abstract int createLayoutId();

	public abstract OpenPresenter.ViewHolder createViewHolder(View view);

	public abstract void bindViewHolder(ViewHolder viewHolder, int position, T t);
}
