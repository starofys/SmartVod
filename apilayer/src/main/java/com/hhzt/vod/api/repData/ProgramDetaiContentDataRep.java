package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.HotRepBean;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;
import com.hhzt.vod.api.repBean.RelevantBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class ProgramDetaiContentDataRep {

    private ArrayList<ProgrameDetailBo> programDetailBo = new ArrayList<>();
    private ArrayList<RelevantBean> relevantList = new ArrayList<>();
    private ArrayList<HotRepBean> hotList = new ArrayList<>();

    public ArrayList<ProgrameDetailBo> getProgramDetailBo() {
        return programDetailBo;
    }

    public void setProgramDetailBo(ArrayList<ProgrameDetailBo> programDetailBo) {
        this.programDetailBo = programDetailBo;
    }

    public ArrayList<RelevantBean> getRelevantList() {
        return relevantList;
    }

    public void setRelevantList(ArrayList<RelevantBean> relevantList) {
        this.relevantList = relevantList;
    }

    public ArrayList<HotRepBean> getHotList() {
        return hotList;
    }

    public void setHotList(ArrayList<HotRepBean> hotList) {
        this.hotList = hotList;
    }

    @Override
    public String toString() {
        return "ProgramDetaiContentDataRep{" +
                "programDetailBo=" + programDetailBo +
                ", relevantList=" + relevantList +
                ", hotList=" + hotList +
                '}';
    }
}
