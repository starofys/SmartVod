package com.hhzt.vod.api.repBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class ProgrameDetailBo {
    private int id;
    private String name;
    private String year;
    private String areaname;
    private String actors;
    private String writers;
    private String description;
    private int seriesFlag;
    private int price;
    private int vipFlag;
    private int score;
    private String smallPoster;
    private String bigPoster;
    private ArrayList<MediaRepBean> mediaList = new ArrayList<>();

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeriesFlag() {
        return seriesFlag;
    }

    public void setSeriesFlag(int seriesFlag) {
        this.seriesFlag = seriesFlag;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getBigPoster() {
        return bigPoster;
    }

    public void setBigPoster(String bigPoster) {
        this.bigPoster = bigPoster;
    }

    public ArrayList<MediaRepBean> getMediaList() {
        return mediaList;
    }

    public void setMediaList(ArrayList<MediaRepBean> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public String toString() {
        return "ProgrameDetailBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", areaname='" + areaname + '\'' +
                ", actors='" + actors + '\'' +
                ", writers='" + writers + '\'' +
                ", description='" + description + '\'' +
                ", seriesFlag=" + seriesFlag +
                ", price=" + price +
                ", vipFlag=" + vipFlag +
                ", score=" + score +
                ", smallPoster='" + smallPoster + '\'' +
                ", bigPoster='" + bigPoster + '\'' +
                ", mediaList=" + mediaList +
                '}';
    }
}
