package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.CategoryRepBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class CategoryBoDatasRep {
    private ArrayList<CategoryRepBean> categoryBoList = new ArrayList<>();

    public ArrayList<CategoryRepBean> getCategoryBoList() {
        return categoryBoList;
    }

    public void setCategoryBoList(ArrayList<CategoryRepBean> categoryBoList) {
        this.categoryBoList = categoryBoList;
    }

    @Override
    public String toString() {
        return "CategoryBoDatasRep{" +
                "categoryBoList=" + categoryBoList +
                '}';
    }
}
