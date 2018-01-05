package com.hhzt.vod.api.repBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class ProgrameDetailBo {
    public int id;
    public String name;
    public String year;
    public String areaname;
    public String actors;
    public String writers;
    public String description;
    public int seriesFlag;
    public int price;
    public int vipFlag;
    public int score;
    public String smallPoster;
    public String bigPoster;
    public ArrayList<MediaRepBean> mediaList = new ArrayList<>();

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
