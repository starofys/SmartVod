package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.CategoryRepBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class ProgramSuperDataRep {
    private ArrayList<CategoryRepBean> programSuperSimpleBoList = new ArrayList<>();

    public ArrayList<CategoryRepBean> getProgramSuperSimpleBoList() {
        return programSuperSimpleBoList;
    }

    public void setProgramSuperSimpleBoList(ArrayList<CategoryRepBean> programSuperSimpleBoList) {
        this.programSuperSimpleBoList = programSuperSimpleBoList;
    }

    @Override
    public String toString() {
        return "ProgramSuperDataRep{" +
                "programSuperSimpleBoList=" + programSuperSimpleBoList +
                '}';
    }
}
