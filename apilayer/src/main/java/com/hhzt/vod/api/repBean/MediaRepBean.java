package com.hhzt.vod.api.repBean;

/**
 * Created by wujichang on 2017/12/30.
 */

public class MediaRepBean {
    public int id;
    public String name;
    public int sequence;
    public String cpobjectcode;
    public int mediaType;
    public int duration;
    public int videoType;
    public int audioType;
    public int streamType;
    public String filePath;
    public int fileSize;
    public String urlType;
    public String otherPlayUrl;

    @Override
    public String toString() {
        return "MediaRepBean{" +
                "name='" + name + '\'' +
                ", sequence=" + sequence +
                ", cpobjectcode='" + cpobjectcode + '\'' +
                ", mediaType=" + mediaType +
                ", duration=" + duration +
                ", videoType=" + videoType +
                ", audioType=" + audioType +
                ", streamType=" + streamType +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", urlType='" + urlType + '\'' +
                ", otherPlayUrl='" + otherPlayUrl + '\'' +
                '}';
    }
}
