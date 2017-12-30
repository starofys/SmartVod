package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.SimpleRepBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class ProgramSuperDataRep {
    private ArrayList<SimpleRepBean> programSuperSimpleBoList = new ArrayList<>();

    public ArrayList<SimpleRepBean> getProgramSuperSimpleBoList() {
        return programSuperSimpleBoList;
    }

    public void setProgramSuperSimpleBoList(ArrayList<SimpleRepBean> programSuperSimpleBoList) {
        this.programSuperSimpleBoList = programSuperSimpleBoList;
    }

    @Override
    public String toString() {
        return "ProgramSuperDataRep{" +
                "programSuperSimpleBoList=" + programSuperSimpleBoList +
                '}';
    }
}
