package com.hhzt.vod.smartvod.callback;

/**
 * Created by zengxiaoping on 2018/1/13.
 *
 * @Author zengxiaoping
 */

public interface T9ClickCallBack {
	/**
	 * @param parentPosition 大位置
	 * @param childPosition  小位置（0：up   1：left   2：down    3：right）
	 * @param parentKeyName  keyName全名（如：abc）
	 * @param childKeyName   keyname的letter（如abc中的a）
	 *                       <p>
	 *                       注意：特殊的数字数据，parentKeyName=childKeyName（即up的位置）
	 */
	void t9KeyBoardClickPosition(int parentPosition, int childPosition, String parentKeyName, String childKeyName);
}
