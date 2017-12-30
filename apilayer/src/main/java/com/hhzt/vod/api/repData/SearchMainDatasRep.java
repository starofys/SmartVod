package com.hhzt.vod.api.repData;

import com.hhzt.vod.api.repBean.HotRepBean;
import com.hhzt.vod.api.repBean.SimpleRepBean;

import java.util.ArrayList;

/**
 * Created by wujichang on 2017/12/30.
 */

public class SearchMainDatasRep {
    private ArrayList<SimpleRepBean> hotSearchList = new ArrayList<>();
    private ArrayList<HotRepBean> hotList = new ArrayList<>();

    public ArrayList<SimpleRepBean> getHotSearchList() {
        return hotSearchList;
    }

    public void setHotSearchList(ArrayList<SimpleRepBean> hotSearchList) {
        this.hotSearchList = hotSearchList;
    }

    public ArrayList<HotRepBean> getHotList() {
        return hotList;
    }

    public void setHotList(ArrayList<HotRepBean> hotList) {
        this.hotList = hotList;
    }

    @Override
    public String toString() {
        return "SearchMainDatasRep{" +
                "hotSearchList=" + hotSearchList +
                ", hotList=" + hotList +
                '}';
    }
}
