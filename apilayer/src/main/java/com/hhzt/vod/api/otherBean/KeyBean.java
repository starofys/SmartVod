package com.hhzt.vod.api.otherBean;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class KeyBean {
	/***
	 * 字符切割成数组
	 * for(int i = 0; i < test.length(); i++){
	 *   arr[i] = test.substring(i, i+1);
	 *   System.out.println(arr[i]);
	 * }
	 */
	private String letterKeyBoard;//字母
	private String numberKeyBoard;//数字

	public String getLetterKeyBoard() {
		return letterKeyBoard;
	}

	public void setLetterKeyBoard(String letterKeyBoard) {
		this.letterKeyBoard = letterKeyBoard;
	}

	public String getNumberKeyBoard() {
		return numberKeyBoard;
	}

	public void setNumberKeyBoard(String numberKeyBoard) {
		this.numberKeyBoard = numberKeyBoard;
	}

	@Override
	public String toString() {
		return "KeyBean{" +
				"letterKeyBoard='" + letterKeyBoard + '\'' +
				", numberKeyBoard='" + numberKeyBoard + '\'' +
				'}';
	}
}
