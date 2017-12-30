package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.ProgramGroupBo;
import com.hhzt.vod.api.repBean.ProgramSimpleBo;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/29.
 */

public class ProgramDatasRep {

    private ArrayList<ProgramSimpleBo> programSimpleBoList = new ArrayList<>();
    private ArrayList<ProgramGroupBo> programGroupBoList = new ArrayList<>();

    public ArrayList<ProgramSimpleBo> getProgramSimpleBoList() {
        return programSimpleBoList;
    }

    public void setProgramSimpleBoList(ArrayList<ProgramSimpleBo> programSimpleBoList) {
        this.programSimpleBoList = programSimpleBoList;
    }

    public ArrayList<ProgramGroupBo> getProgramGroupBoList() {
        return programGroupBoList;
    }

    public void setProgramGroupBoList(ArrayList<ProgramGroupBo> programGroupBoList) {
        this.programGroupBoList = programGroupBoList;
    }

    @Override
    public String toString() {
        return "ProgramDatasRep{" +
                "programSimpleBoList=" + programSimpleBoList +
                ", programGroupBoList=" + programGroupBoList +
                '}';
    }
}
