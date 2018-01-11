package com.hhzt.vod.api.repBean;

/**
 * Created by wujichang on 2017/12/30.
 */

public class SimpleRepBean {
    private int id;
    private int programId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleRepBean{" +
                "id=" + id +
                ", programId=" + programId +
                ", name='" + name + '\'' +
                '}';
    }
}
