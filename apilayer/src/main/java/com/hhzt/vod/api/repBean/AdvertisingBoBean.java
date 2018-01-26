package com.hhzt.vod.api.repBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2018/1/26.
 */

public class AdvertisingBoBean {
	private int id;
	private String name;
	private String title;
	private String content;
	private String infor;
	private String locationName;
	private String locationNumber;
	private String locationDesc;
	private int showType;
	private int swap;
	private int intervalTime;
	private ArrayList<AdvertisingResourcesBoBean> advertisingResourcesBo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public int getSwap() {
		return swap;
	}

	public void setSwap(int swap) {
		this.swap = swap;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public ArrayList<AdvertisingResourcesBoBean> getAdvertisingResourcesBo() {
		return advertisingResourcesBo;
	}

	public void setAdvertisingResourcesBo(ArrayList<AdvertisingResourcesBoBean> advertisingResourcesBo) {
		this.advertisingResourcesBo = advertisingResourcesBo;
	}

	@Override
	public String toString() {
		return "AdvertisingBoBean{" +
				"id=" + id +
				", name='" + name + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", infor='" + infor + '\'' +
				", locationName='" + locationName + '\'' +
				", locationNumber='" + locationNumber + '\'' +
				", locationDesc='" + locationDesc + '\'' +
				", showType=" + showType +
				", swap=" + swap +
				", intervalTime=" + intervalTime +
				", advertisingResourcesBo=" + advertisingResourcesBo +
				'}';
	}
}
