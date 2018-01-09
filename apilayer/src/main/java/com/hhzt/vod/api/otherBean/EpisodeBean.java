package com.hhzt.vod.api.otherBean;

/**
 * Created by Administrator on 2018/1/1 0001.
 */

public class EpisodeBean {
	private String episode;
	private int table; //标签

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return "EpisodeBean{" +
				"episode='" + episode + '\'' +
				", table=" + table +
				'}';
	}
}
