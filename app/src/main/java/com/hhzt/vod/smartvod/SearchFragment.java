package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhzt.vod.smartvod.iview.ISearchView;

import org.xutils.view.annotation.ContentView;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.fragment_search)
public class SearchFragment extends BaseFragment implements ISearchView{

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void showKeyBoradContainer(int type) {

	}

	@Override
	public void showRecommentSearch() {

	}

	@Override
	public void showSearchHistory() {

	}
}
