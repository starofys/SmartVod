package com.hhzt.vod.api.repData;

/**
 * Created by wujichang on 2017/12/30.
 */

public class SimpleResultRep {
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SimpleResultRep{" +
                "result=" + result +
                '}';
    }
}
