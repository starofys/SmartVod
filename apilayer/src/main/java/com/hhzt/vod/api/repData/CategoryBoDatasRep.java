package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.SimpleRepBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class CategoryBoDatasRep {
    private ArrayList<SimpleRepBean> categoryBoList = new ArrayList<>();

    public ArrayList<SimpleRepBean> getCategoryBoList() {
        return categoryBoList;
    }

    public void setCategoryBoList(ArrayList<SimpleRepBean> categoryBoList) {
        this.categoryBoList = categoryBoList;
    }

    @Override
    public String toString() {
        return "CategoryBoDatasRep{" +
                "categoryBoList=" + categoryBoList +
                '}';
    }
}
