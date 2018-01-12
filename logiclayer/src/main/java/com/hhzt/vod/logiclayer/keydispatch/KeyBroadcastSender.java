package com.hhzt.vod.logiclayer.keydispatch;

import android.content.Intent;
import android.view.KeyEvent;

import com.hhzt.vod.logiclayer.App;

/**
 * Created by Johnson on 2018/1/11.
 */

public class KeyBroadcastSender {
	private static volatile KeyBroadcastSender mKeyBroadcastSender;

	private KeyBroadcastSender() {
	}

	public static KeyBroadcastSender getInstance() {
		if (null == mKeyBroadcastSender) {
			synchronized (KeyBroadcastSender.class) {
				if (null == mKeyBroadcastSender) {
					mKeyBroadcastSender = new KeyBroadcastSender();
				}
			}
		}

		return mKeyBroadcastSender;
	}

	public void sendUpBordKey(int source) {
		Intent intent = new Intent();
		intent.setAction(KeyFactoryConst.KEY_LISTEN_ACTION);
		intent.putExtra(KeyFactoryConst.KEY_CODE_TAG, KeyFactoryConst.KEY_CODE_UP);
		intent.putExtra(KeyFactoryConst.KEY_SOURCE_TAG, source);
		App.mContext.sendBroadcast(intent);
	}

	public void sendBottonBordKey(int source) {
		Intent intent = new Intent();
		intent.setAction(KeyFactoryConst.KEY_LISTEN_ACTION);
		intent.putExtra(KeyFactoryConst.KEY_CODE_TAG, KeyFactoryConst.KEY_CODE_DOWN);
		intent.putExtra(KeyFactoryConst.KEY_SOURCE_TAG, source);
		App.mContext.sendBroadcast(intent);
	}

	public void sendLeftBordKey(int source) {
		Intent intent = new Intent();
		intent.setAction(KeyFactoryConst.KEY_LISTEN_ACTION);
		intent.putExtra(KeyFactoryConst.KEY_CODE_TAG, KeyFactoryConst.KEY_CODE_LEFT);
		intent.putExtra(KeyFactoryConst.KEY_SOURCE_TAG, source);
		App.mContext.sendBroadcast(intent);
	}

	public void sendRightBordKey(int source) {
		Intent intent = new Intent();
		intent.setAction(KeyFactoryConst.KEY_LISTEN_ACTION);
		intent.putExtra(KeyFactoryConst.KEY_CODE_TAG, KeyFactoryConst.KEY_CODE_RIGHT);
		intent.putExtra(KeyFactoryConst.KEY_SOURCE_TAG, source);
		App.mContext.sendBroadcast(intent);
	}

}
