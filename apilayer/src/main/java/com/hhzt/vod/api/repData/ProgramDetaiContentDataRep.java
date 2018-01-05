package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class ProgramDetaiContentDataRep {

    public ProgrameDetailBo programDetailBo;
//    public ArrayList<MovieInfoData> relevantList = new ArrayList<>();
    public ArrayList<MovieInfoData> hotList = new ArrayList<>();

    @Override
    public String toString() {
        return "ProgramDetaiContentDataRep{" +
                "programDetailBo=" + programDetailBo.toString() +
//                ", relevantList=" + relevantList.toString() +
                ", hotList=" + hotList.toString() +
                '}';
    }
}
