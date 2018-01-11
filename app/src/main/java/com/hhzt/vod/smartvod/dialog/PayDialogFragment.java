package com.hhzt.vod.smartvod.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hhzt.vod.api.HttpUrlCreator;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.constant.ConstTag;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class PayDialogFragment extends DialogFragment {

    private WebView mWebView;

    private String mUserName;
    private String mContentId;
    private String mMediaType;

    private Handler mUIHandler = new Handler();

    public static PayDialogFragment getInstance(String userName, String contentId, String type) {
        PayDialogFragment fragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstTag.TAG_USERNAME, userName);
        bundle.putString(ConstTag.TAG_CONTENT, contentId);
        bundle.putString(ConstTag.TAG_MEDIA_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pay_tip, null);

        mUserName = getArguments().getString(ConstTag.TAG_USERNAME);
        mContentId = getArguments().getString(ConstTag.TAG_CONTENT);
        mMediaType = getArguments().getString(ConstTag.TAG_MEDIA_TYPE);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebView = view.findViewById(R.id.pay_webView);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "hhzt");
        mWebView.loadUrl(HttpUrlCreator.getVodPayActionTipsUrl(mUserName, mContentId, mMediaType));
    }

    @JavascriptInterface
    public void checkPayResult() {
        mUIHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
