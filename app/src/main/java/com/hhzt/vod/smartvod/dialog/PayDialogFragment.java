package com.hhzt.vod.smartvod.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hhzt.vod.api.HttpUrlCreator;
import com.hhzt.vod.media.NiceVideoPlayerManager;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.smartvod.constant.ConstTag;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class PayDialogFragment extends DialogFragment {

	private WebView mWebView;

	private String mUserName;
	private int mContentId;
	private int mMediaType;
	private int mPayPath;

	private Handler mUIHandler = new Handler();

	public static PayDialogFragment getInstance(String userName, int contentId, int type, int payPath) {
		PayDialogFragment fragment = new PayDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putString(ConstTag.TAG_USERNAME, userName);
		bundle.putInt(ConstTag.TAG_CONTENT, contentId);
		bundle.putInt(ConstTag.TAG_MEDIA_TYPE, type);
		bundle.putInt(ConstTag.TAG_PAY_PATH, payPath);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int style = DialogFragment.STYLE_NO_TITLE;
		setStyle(style, 0);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_pay_tip, container, false);

		WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
		params.gravity = Gravity.CENTER;
		params.windowAnimations = R.style.bottomSheet_animation;
		getDialog().getWindow().setAttributes(params);
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().setCanceledOnTouchOutside(false);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		mUserName = getArguments().getString(ConstTag.TAG_USERNAME);
		mContentId = getArguments().getInt(ConstTag.TAG_CONTENT);
		mMediaType = getArguments().getInt(ConstTag.TAG_MEDIA_TYPE);
		mPayPath = getArguments().getInt(ConstTag.TAG_PAY_PATH);

		initView(view);
		return view;
	}

	private void initView(View view) {
		mWebView = (WebView) view.findViewById(R.id.pay_webView);

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
//		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setUseWideViewPort(true);
//		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//		webSettings.setLoadWithOverviewMode(true);

		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		mWebView.clearHistory();
		mWebView.clearCache(true);

		mWebView.setBackgroundColor(0);

		mWebView.addJavascriptInterface(this, "hhzt");
		if (mPayPath == ConfigX.MEDIA_PAY_PATH_DIRECT) {
			mWebView.loadUrl(HttpUrlCreator.getVodPayActionTipsUrl(mUserName, mContentId, mMediaType));
		} else {
			mWebView.loadUrl(HttpUrlCreator.getVodPayLookActionTipsUrl(mUserName, mContentId, mMediaType));
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		getDialog().getWindow().setLayout(1024, 560);
	}

	@JavascriptInterface
	public void checkPayResult() {
		mUIHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				NiceVideoPlayerManager.instance().resumeNiceVideoPlayer();
				dismiss();
			}
		}, 1000);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
