package com.hhzt.vod.api.repBean;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class MovieInfoData {
	private int id;
	private int programId;
	private String name;
	private int recommend;
	private int vipFlag;
	private String score;
	private String smallPoster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getVipFlag() {
		return vipFlag;
	}

	public void setVipFlag(int vipFlag) {
		this.vipFlag = vipFlag;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getSmallPoster() {
		return smallPoster;
	}

	public void setSmallPoster(String smallPoster) {
		this.smallPoster = smallPoster;
	}

	@Override
	public String toString() {
		return "MovieInfoData{" +
				"id=" + id +
				", programId=" + programId +
				", name='" + name + '\'' +
				", recommend=" + recommend +
				", vipFlag=" + vipFlag +
				", score='" + score + '\'' +
				", smallPoster='" + smallPoster + '\'' +
				'}';
	}
}