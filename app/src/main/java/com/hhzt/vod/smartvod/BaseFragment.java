package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.observer.AchieveObserverWatched;
import com.hhzt.vod.smartvod.observer.ObserverConst;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.recycle.RecyclerViewTV;

import org.xutils.x;

/**
 * Created by wujichang on 2017/12/28.
 */

public class BaseFragment extends Fragment {

	private boolean injected = false;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		injected = true;
		return x.view().inject(this, inflater, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (!injected) {
			x.view().inject(this, this.getView());
		}
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void focusView(RecyclerViewBridge recyclerViewBridge, View view, float scale) {
		recyclerViewBridge.setFocusView(view, scale);
		if (view != null) {
			view.requestLayout();
			view.requestFocus();
		}
	}

	public void requestDefaultFocus(RecyclerViewBridge recyclerViewBridge, RecyclerViewTV recyclerViewTV) {
		AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_TRANSLATE, true);
		AchieveObserverWatched.getInstance().notifyWatcher(ObserverConst.CODE_MOVIE_TYPE_SHOW_OR_HINT, true);
		if (recyclerViewTV.getChildCount() == 0) return;
		View view = recyclerViewTV.getChildAt(0);
		recyclerViewBridge.setFocusView(view, ConfigX.SCALE_DEFAULT);
		if (null != view) {
			view.requestLayout();
			view.requestFocus();
		}
	}
}
