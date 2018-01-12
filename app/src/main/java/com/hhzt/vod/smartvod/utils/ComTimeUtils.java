package com.hhzt.vod.smartvod.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zengxiaoping on 2018/1/12.
 *
 * @Author zengxiaoping
 */

public class ComTimeUtils {

	/**
	 * 获取今天的日期yyyy-MM-dd格式
	 *
	 * @return Data
	 */
	public static String getTodayDate() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取今天的日期HH:mm格式
	 *
	 * @return Data
	 */
	public static String getTodayTime() {
		return formatDate(new Date(), "HH:mm");
	}

	/**
	 * 获取今天的日期HH:mm格式
	 *
	 * @return Data
	 */
	public static String getTodayWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 字符串转换到时间格式
	 *
	 * @param dateStr   需要转换的字符串
	 * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 */
	private static Date stringToDate(String dateStr, String formatStr) {
		@SuppressLint("SimpleDateFormat") DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private static String formatDate(Date date, String format) {
		return getFormatDate(date, format);
	}

	/**
	 * 格式时间
	 *
	 * @param date   时间
	 * @param format 格式
	 * @return String
	 */
	private static String getFormatDate(Date date, String format) {
		if (date != null) {
			@SuppressLint("SimpleDateFormat") SimpleDateFormat f = new SimpleDateFormat(format);
			return f.format(date);
		} else {
			return null;
		}
	}
}
