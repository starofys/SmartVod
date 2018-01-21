package com.hhzt.vod.smartvod.observer;

/**
 * Created by zengxiaoping on 2018/1/21.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public interface ObserverWatched {
	void add(ObserverWatcher var1);

	void remove(ObserverWatcher var1);

	void notifyWatcher(int var1, Object var2);

}
