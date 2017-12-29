package com.hhzt.vod.smartvod.iview;

/**
 * Created by Johnson on 2017/12/29.
 */

public interface IPayViewer {
	void showPayType(int process);

	void showPayProcing();

	void onPaySuccess();

	void onPayFailed(int errorCode);
}
