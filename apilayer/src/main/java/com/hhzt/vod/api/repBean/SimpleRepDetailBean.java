package com.hhzt.vod.api.repBean;

/**
 * Created by wujichang on 2017/12/30.
 */

public class SimpleRepDetailBean {
    private int id;
    private String name;
    private int recommend;
    private int vipFlag;
    private String smallPoster;
    private String score;

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

    public String getSmallPoster() {
        return smallPoster;
    }

    public void setSmallPoster(String smallPoster) {
        this.smallPoster = smallPoster;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SimpleRepDetailBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recommend=" + recommend +
                ", vipFlag=" + vipFlag +
                ", smallPoster='" + smallPoster + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
