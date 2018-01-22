package com.hhzt.vod.smartvod.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.repBean.CategoryRepBean;
import com.hhzt.vod.logiclayer.App;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView demo 的左侧菜单.
 * Created by hailongqiu on 2016/8/24.
 */
public class LeftMenuPresenter extends OpenPresenter {

	private List<CategoryRepBean> mCategoryName = new ArrayList<>();
	private GeneralAdapter mAdapter;
	private int mSelectPosition = 0;
	private Resources mResources;

	public LeftMenuPresenter(List<CategoryRepBean> mCategoryName) {
		this.mCategoryName = mCategoryName;
		mResources = App.mContext.getResources();
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	@Override
	public int getItemCount() {
		return mCategoryName.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_type_fragment, parent, false);
		return new MovieTypeViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		MovieTypeViewHolder movieTypeViewHolder = (MovieTypeViewHolder) viewHolder;
		movieTypeViewHolder.ivMovieTypeName.setText(mCategoryName.get(position).getName());
		movieTypeViewHolder.ivMovieTypeName.setFocusableInTouchMode(true);
		movieTypeViewHolder.ivMovieTypeName.setFocusable(true);

		movieTypeViewHolder.viewBackgroud.setBackground(mSelectPosition==position?mResources.getDrawable(R.mipmap.ic_movie_type_selected):mResources.getDrawable(R.drawable.bg_border_translate_selector));
	}

	public void setSelectPosition(int selectPosition) {
		this.mSelectPosition = selectPosition;
		mAdapter.notifyDataSetChanged();
	}
}
