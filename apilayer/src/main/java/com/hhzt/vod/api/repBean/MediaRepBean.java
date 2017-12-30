package com.hhzt.vod.api.repBean;

/**
 * Created by wujichang on 2017/12/30.
 */

public class MediaRepBean {
    private String name;
    private int sequence;
    private String cpobjectcode;
    private int mediaType;
    private int duration;
    private int videoType;
    private int audioType;
    private int streamType;
    private String filePath;
    private int fileSize;
    private String urlType;
    private String otherPlayUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getCpobjectcode() {
        return cpobjectcode;
    }

    public void setCpobjectcode(String cpobjectcode) {
        this.cpobjectcode = cpobjectcode;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public int getAudioType() {
        return audioType;
    }

    public void setAudioType(int audioType) {
        this.audioType = audioType;
    }

    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getOtherPlayUrl() {
        return otherPlayUrl;
    }

    public void setOtherPlayUrl(String otherPlayUrl) {
        this.otherPlayUrl = otherPlayUrl;
    }

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
