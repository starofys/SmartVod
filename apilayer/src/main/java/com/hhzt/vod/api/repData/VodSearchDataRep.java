package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.MovieInfoData;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class VodSearchDataRep {
    private int pageNum;
    private int pageSize;
    private int totalPage;
    private int total;
    private ArrayList<MovieInfoData> programSimpleBoList = new ArrayList<>();

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<MovieInfoData> getProgramSimpleBoList() {
        return programSimpleBoList;
    }

    public void setProgramSimpleBoList(ArrayList<MovieInfoData> programSimpleBoList) {
        this.programSimpleBoList = programSimpleBoList;
    }

    @Override
    public String toString() {
        return "VodSearchDataRep{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", total=" + total +
                ", programSimpleBoList=" + programSimpleBoList +
                '}';
    }
}
