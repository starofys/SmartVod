package com.hhzt.vod.api.repData;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class VodGroupDetailDataRep {
    private int pageNum;
    private int pageSize;
    private int totalPage;
    private int total;
    private int categoryId;
    private int programGroupId;
    private ArrayList<VodGroupDetailDataRep> programSimpleBoList = new ArrayList<>();

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProgramGroupId() {
        return programGroupId;
    }

    public void setProgramGroupId(int programGroupId) {
        this.programGroupId = programGroupId;
    }

    public ArrayList<VodGroupDetailDataRep> getProgramSimpleBoList() {
        return programSimpleBoList;
    }

    public void setProgramSimpleBoList(ArrayList<VodGroupDetailDataRep> programSimpleBoList) {
        this.programSimpleBoList = programSimpleBoList;
    }

    @Override
    public String toString() {
        return "VodGroupDetailDataRep{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", total=" + total +
                ", categoryId=" + categoryId +
                ", programGroupId=" + programGroupId +
                ", programSimpleBoList=" + programSimpleBoList +
                '}';
    }
}
