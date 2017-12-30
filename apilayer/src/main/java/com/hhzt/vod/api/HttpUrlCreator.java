package com.hhzt.vod.api;

/**
 * Created by wujichang on 2017/12/30.
 */

public class HttpUrlCreator {

    /**
     * 1.主页推荐请求url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param username
     * @param tenantid
     * @param sign
     * @return
     */
    public static String getVodRecommendUrl(String appkey,
                                            String format,
                                            String method,
                                            String v,
                                            String session,
                                            String username,
                                            String tenantid,
                                            String sign) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&username=" + username
                + "&tenantid=" + tenantid
                + "&sign=" + sign;
    }

    /**
     * 2.点播分组下分类导航url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param username
     * @param programGroupId
     * @return
     */
    public static String getVodGroupDatasUrl(String appkey,
                                             String format,
                                             String method,
                                             String v,
                                             String session,
                                             String username,
                                             int programGroupId) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&username=" + username
                + "&programGroupId=" + programGroupId;
    }

    /**
     * 3.点播分组下分类详情url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param username
     * @param programGroupId
     * @param sign
     * @return
     */
    public static String getVodGroupDetailDatasUrl(String appkey,
                                                   String format,
                                                   String method,
                                                   String v,
                                                   String session,
                                                   int pageNum,
                                                   int pageSize,
                                                   int categoryId,
                                                   String username,
                                                   int programGroupId,
                                                   String sign) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&pageNum=" + pageNum
                + "&pageSize=" + pageSize
                + "&categoryId=" + categoryId
                + "&username=" + username
                + "&programGroupId=" + programGroupId;
    }

    /**
     * 4.点播节目详情url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param programId
     * @param categoryId
     * @param username
     * @param programGroupId
     * @return
     */
    public static String getVodItemDetailUrl(String appkey,
                                             String format,
                                             String method,
                                             String v,
                                             String session,
                                             int programId,
                                             int categoryId,
                                             String username,
                                             int programGroupId) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&programId=" + programId
                + "&categoryId=" + categoryId
                + "&username=" + username
                + "&programGroupId=" + programGroupId;
    }

    /**
     * 5.点播节目播放记录和热度统计(热度每播放一次加一)url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param username
     * @param mediaId
     * @param requestIp
     * @param playingTime
     * @param tenantId
     * @return
     */
    public static String getVodPlayNumRecordUrl(String appkey,
                                                String format,
                                                String method,
                                                String v,
                                                String session,
                                                String username,
                                                int mediaId,
                                                String requestIp,
                                                int playingTime,
                                                int tenantId) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&username=" + username
                + "&mediaId=" + mediaId
                + "&requestIp=" + requestIp
                + "&playingTime=" + playingTime
                + "&tenantId=" + tenantId;
    }

    /**
     * 6.点播节目评星url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param programId
     * @param starRating
     * @return
     */
    public static String getVodItemPlayStarUrl(String appkey,
                                               String format,
                                               String method,
                                               String v,
                                               String session,
                                               int programId,
                                               int starRating) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&programId=" + programId
                + "&starRating=" + starRating;
    }

    /**
     * 7.点播搜索热度记录(每搜索一次加1)url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param programId
     * @return
     */
    public static String getVodItemSearchStarUrl(String appkey,
                                                 String format,
                                                 String method,
                                                 String v,
                                                 String session,
                                                 int programId) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&programId=" + programId;
    }

    /**
     * 8.点播搜索主页url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param username
     * @return
     */
    public static String getVodSearchMainUrl(String appkey,
                                             String format,
                                             String method,
                                             String v,
                                             String session,
                                             String username) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&username=" + username;
    }

    /**
     * 9.点播搜索url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param pageNum
     * @param pageSize
     * @param searchKeyword
     * @param username
     * @return
     */
    public static String getVodSearchVodUrl(String appkey,
                                            String format,
                                            String method,
                                            String v,
                                            String session,
                                            int pageNum,
                                            int pageSize,
                                            String searchKeyword,
                                            String username) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&pageNum=" + pageNum
                + "&pageSize=" + pageSize
                + "&searchKeyword" + searchKeyword
                + "&username=" + username;

    }

    /**
     * 10.点播历史记录列表url
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param username
     * @return
     */
    public static String getVodPlayHistoryUrl(String appkey,
                                                String format,
                                                String method,
                                                String v,
                                                String session,
                                                String username) {
        return HttpConst.BASE_HOST
                + "appkey=" + appkey
                + "&format=" + format
                + "&method=" + method
                + "&v=" + v
                + "&session=" + session
                + "&username=" + username;
    }
}
