package com.hhzt.vod.api.repBean;

/**
 * Created by wujichang on 2017/12/30.
 */

public class HotRepBean {
    private int id;
    private String name;
    private int recommend;
    private int vipFlag;
    private int score;
    private String smallPoster;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
        return "HotRepBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recommend=" + recommend +
                ", vipFlag=" + vipFlag +
                ", score=" + score +
                ", smallPoster='" + smallPoster + '\'' +
                '}';
    }
}
