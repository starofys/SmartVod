package com.hhzt.vod.api.repBean;

/**
 * Created by wujichang on 2017/12/30.
 */

public class SimpleRepBean {
	private int id;
	private int categoryId;
	private String name;
	private int vipFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVipFlag() {
		return vipFlag;
	}

	public void setVipFlag(int vipFlag) {
		this.vipFlag = vipFlag;
	}

	@Override
	public String toString() {
		return "SimpleRepBean{" +
				"id=" + id +
				", categoryId=" + categoryId +
				", name='" + name + '\'' +
				", vipFlag=" + vipFlag +
				'}';
	}
}
