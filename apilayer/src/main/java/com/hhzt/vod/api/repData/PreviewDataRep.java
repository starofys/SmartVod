package com.hhzt.vod.api.repData;

/**
 * Created by wujichang on 2018/1/26.
 */

public class PreviewDataRep {
	private String description;

	private int value;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PreviewDataRep{" +
				"description='" + description + '\'' +
				", value=" + value +
				'}';
	}
}
