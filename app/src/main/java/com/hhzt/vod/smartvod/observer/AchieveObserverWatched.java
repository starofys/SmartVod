package com.hhzt.vod.smartvod.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/21.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class AchieveObserverWatched implements ObserverWatched {
	private static AchieveObserverWatched watched = new AchieveObserverWatched();
	private List<ObserverWatcher> list = new ArrayList();

	public AchieveObserverWatched() {
	}

	public static AchieveObserverWatched getInstance() {
		return watched;
	}

	public void add(ObserverWatcher watcher) {
		this.list.add(watcher);
	}

	public void remove(ObserverWatcher watcher) {
		this.list.remove(watcher);
	}

	public void notifyWatcher(int code, Object o) {
		Iterator var3 = this.list.iterator();

		while (var3.hasNext()) {
			ObserverWatcher watcher = (ObserverWatcher) var3.next();
			watcher.updataNotify(code, o);
		}

	}
}
