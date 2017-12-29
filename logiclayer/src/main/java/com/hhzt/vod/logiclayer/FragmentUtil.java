package com.hhzt.vod.logiclayer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Johnson on 2017/12/29.
 */

public class FragmentUtil {

	private static final int ADD = 1;
	private static final int REMOVE = 2;
	private static final int REPLACE = 3;
	private static final int SHOW = 4;
	private static final int HIDE = 5;

	public static void add(FragmentActivity activity, boolean addToStack, int containerId, Fragment fragment) {
		action(ADD, activity, null, false, addToStack, containerId, fragment);
	}

	public static void add(FragmentActivity activity, Fragment subFragment, boolean addToStack, int containerId,
	                       Fragment fragment) {
		action(ADD, activity, subFragment, false, addToStack, containerId, fragment);
	}

	public static void remove(FragmentActivity activity, Fragment fragment) {
		action(REMOVE, activity, null, false, false, 0, fragment);
	}

	public static void remove(FragmentActivity activity, Fragment subFragment, Fragment fragment) {
		action(REMOVE, activity, subFragment, false, false, 0, fragment);
	}

	public static void replace(FragmentActivity activity, boolean addToStack, int containerId, Fragment fragment) {
		replace(activity, false, addToStack, containerId, fragment);
	}

	public static void replace(FragmentActivity activity, Fragment subFragment, boolean addToStack, int containerId, Fragment fragment) {
		replace(activity, subFragment, false, addToStack, containerId, fragment);
	}

	public static void replace(FragmentActivity activity, boolean needPop, boolean addToStack, int containerId,
	                           Fragment fragment) {
		action(REPLACE, activity, null, needPop, addToStack, containerId, fragment);
	}

	public static void replace(FragmentActivity activity, Fragment subFragment, boolean needPop, boolean addToStack, int containerId, Fragment fragment) {
		action(REPLACE, activity, subFragment, needPop, addToStack, containerId, fragment);
	}

	public static void show(FragmentActivity activity, Fragment fragment) {
		action(SHOW, activity, null, false, false, 0, fragment);
	}

	public static void show(FragmentActivity activity, Fragment subFragment, Fragment fragment) {
		action(SHOW, activity, subFragment, false, false, 0, fragment);
	}

	public static void hide(FragmentActivity activity, Fragment fragment) {
		action(HIDE, activity, null, false, false, 0, fragment);
	}

	public static void hide(FragmentActivity activity, Fragment subFragment, Fragment fragment) {
		action(HIDE, activity, subFragment, false, false, 0, fragment);
	}

	private static void action(int antionType, FragmentActivity activity, Fragment subFragment, boolean needPop,
	                           boolean addToStack, int containerId, Fragment fragment) {
		if (null != activity && (activity.isDestroyed() || activity.isFinishing())) {
			return;
		}
		FragmentManager supportFragmentManager = null;
		if (subFragment == null) {
			supportFragmentManager = activity.getSupportFragmentManager();
		} else {
			supportFragmentManager = subFragment.getChildFragmentManager();
		}
		// 实例化fragment事务管理器
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		if (needPop) {
			supportFragmentManager.popBackStack();
		}
		switch (antionType) {
			case ADD:
				transaction.add(containerId, fragment);
				break;
			case REMOVE:
				transaction.remove(fragment);
				break;
			case REPLACE:
				transaction.replace(containerId, fragment);
				break;
			case SHOW:
				transaction.show(fragment);
				break;
			case HIDE:
				transaction.hide(fragment);
				break;
			default:
				break;
		}

		if (addToStack) {
			// 添加进栈中
			transaction.addToBackStack(null);
		}

		// 提交事务
		if (isTopAct(activity)) {
			transaction.commitAllowingStateLoss();
		} else {
			transaction.commit();
		}
	}

	/**********************************以下是android.app包（不推荐用）*********************************************************/

	public static void replaceByHighEdition(Activity activity, boolean addToStack, int containerId, android.app.Fragment
			fragment) {
		replaceByHighEdition(activity, false, addToStack, containerId, fragment);
	}

	public static void replaceByHighEdition(Activity activity, android.app.Fragment subFragment,
	                                        boolean addToStack, int containerId,
	                                        android.app.Fragment fragment) {
		replaceByHighEdition(activity, subFragment, false, addToStack, containerId, fragment);
	}

	public static void replaceByHighEdition(Activity activity, boolean needPop, boolean addToStack, int containerId, android.app.Fragment fragment) {
		actionByHighEdition(REPLACE, activity, null, needPop, addToStack, containerId, fragment);
	}

	public static void replaceByHighEdition(Activity activity, android.app.Fragment subFragment, boolean needPop, boolean
			addToStack, int containerId, android.app.Fragment fragment) {
		actionByHighEdition(REPLACE, activity, subFragment, needPop, addToStack, containerId, fragment);
	}

	private static void actionByHighEdition(int antionType, Activity activity, android.app.Fragment subFragment,
	                                        boolean needPop, boolean addToStack, int containerId,
	                                        android.app.Fragment fragment) {
		if (null != activity && (activity.isDestroyed() || activity.isFinishing())) {
			return;
		}
		android.app.FragmentManager supportFragmentManager = null;
		if (subFragment == null) {
			supportFragmentManager = activity.getFragmentManager();
		} else {
			supportFragmentManager = subFragment.getChildFragmentManager();
		}
		// 实例化fragment事务管理器
		android.app.FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		if (needPop) {
			supportFragmentManager.popBackStack();
		}
		switch (antionType) {
			case ADD:
				transaction.add(containerId, fragment);
				break;
			case REMOVE:
				transaction.remove(fragment);
				break;
			case REPLACE:
				transaction.replace(containerId, fragment);
				break;
			case SHOW:
				transaction.show(fragment);
				break;
			case HIDE:
				transaction.hide(fragment);
				break;
			default:
				break;
		}

		if (addToStack) {
			// 添加进栈中
			transaction.addToBackStack(null);
		}

		// 提交事务
		if (!isTopAct(activity)) {
			transaction.commitAllowingStateLoss();
		} else {
			transaction.commit();
		}
	}

	public static boolean isTopAct(Activity activity) {
		ActivityManager am = (ActivityManager) activity.getSystemService(activity.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		boolean isTop = cn.getClassName().contains(activity.getLocalClassName());
		return isTop;
	}
}
