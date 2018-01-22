package com.hhzt.vod.smartvod.view;

import android.view.View;

/**
 * Created by zengxiaoping on 2018/1/22.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class ViewWrapper {
	private View mTarget;

	public ViewWrapper(View target) {
		mTarget = target;
	}

	public int getWidth() {
		return mTarget.getLayoutParams().width;
	}

	public void setWidth(int width) {
		mTarget.getLayoutParams().width = width;
		mTarget.requestLayout();
	}
}