package com.hhzt.vod.api;

/**
 * Created by wujichang on 2017/12/29.
 */

public class HttpVod {
    /**
     * 1.点播主界面
     *
     * @param appkey           用于验证的Appkey
     * @param format           返回数据格式
     * @param method           请求API方法的名称
     * @param v                版本号
     * @param session          登陆之后的session
     * @param tenantid         租户ID
     * @param sign             签名
     * @param iHttpRetCallBack 回调接口
     * @param tClass           范型类型
     * @param tag              tag标志
     * @param <T>
     */
    public static <T> void getVodRecommendDatas(String appkey,
                                                String format,
                                                String method,
                                                String v,
                                                String session,
                                                String tenantid,
                                                String sign,
                                                String tag,
                                                Class<T> tClass,
                                                IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodRecommendUrl(appkey, format, method, v, session, ConfigMgr.getInstance().getUserName(), tenantid, sign);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 2.点播分组下分类导航
     *
     * @param appkey           用于验证的Appkey
     * @param format           返回数据格式
     * @param method           请求API方法的名称
     * @param v                版本号
     * @param session          登陆之后的session
     * @param programGroupId   租ID
     * @param tag              tag
     * @param tClass           范型类型
     * @param iHttpRetCallBack 回调接口
     * @param <T>
     */
    public static <T> void getVodMenuByGroupDatas(String appkey,
                                                  String format,
                                                  String method,
                                                  String v,
                                                  String session,
                                                  int programGroupId,
                                                  String tag,
                                                  Class<T> tClass,
                                                  IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodGroupDatasUrl(appkey, format, method, v, session, ConfigMgr.getInstance().getUserName(), programGroupId);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 3.点播分组下分类详情
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param programGroupId
     * @param sign
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodCategoryDetailByGroupDatas(String appkey,
                                                            String format,
                                                            String method,
                                                            String v,
                                                            String session,
                                                            int pageNum,
                                                            int pageSize,
                                                            int categoryId,
                                                            int programGroupId,
                                                            String sign,
                                                            String tag,
                                                            Class<T> tClass,
                                                            IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodGroupDetailDatasUrl(appkey, format, method, v, session, pageNum, pageSize, categoryId, ConfigMgr.getInstance().getUserName(), programGroupId, sign);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 4.点播节目详情
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param programId
     * @param categoryId
     * @param programGroupId
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodItemDetailDatas(String appkey,
                                                 String format,
                                                 String method,
                                                 String v,
                                                 String session,
                                                 int programId,
                                                 int categoryId,
                                                 int programGroupId,
                                                 String tag,
                                                 Class<T> tClass,
                                                 IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodItemDetailUrl(appkey, format, method, v, session, programId, categoryId, ConfigMgr.getInstance().getUserName(), programGroupId);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 5.点播节目播放记录和热度统计(热度每播放一次加一)
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param mediaId
     * @param requestIp
     * @param playingTime
     * @param tenantId
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodPlayRecordDatas(String appkey,
                                                 String format,
                                                 String method,
                                                 String v,
                                                 String session,
                                                 int mediaId,
                                                 String requestIp,
                                                 int playingTime,
                                                 int tenantId,
                                                 String tag,
                                                 Class<T> tClass,
                                                 IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodPlayNumRecordUrl(appkey, format, method, v, session, ConfigMgr.getInstance().getUserName(), mediaId, requestIp, playingTime, tenantId);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 6.点播节目评星
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param programId
     * @param starRating
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodItemStarDatas(String appkey,
                                               String format,
                                               String method,
                                               String v,
                                               String session,
                                               int programId,
                                               int starRating,
                                               String tag,
                                               Class<T> tClass,
                                               IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodItemPlayStarUrl(appkey, format, method, v, session, programId, starRating);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 7.点播搜索热度记录(每搜索一次加1)
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param programId
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodItemSearchRecordNumberDatas(String appkey,
                                                             String format,
                                                             String method,
                                                             String v,
                                                             String session,
                                                             int programId,
                                                             String tag,
                                                             Class<T> tClass,
                                                             IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodItemSearchStarUrl(appkey, format, method, v, session, programId);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 8.点播搜索主页
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodSearchMainDatas(String appkey,
                                                 String format,
                                                 String method,
                                                 String v,
                                                 String session,
                                                 String tag,
                                                 Class<T> tClass,
                                                 IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodSearchMainUrl(appkey, format, method, v, session, ConfigMgr.getInstance().getUserName());
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 9.点播搜索
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param pageNum
     * @param pageSize
     * @param searchKeyword
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodSearchKeyCodeDatas(String appkey,
                                                    String format,
                                                    String method,
                                                    String v,
                                                    String session,
                                                    int pageNum,
                                                    int pageSize,
                                                    String searchKeyword,
                                                    String tag,
                                                    Class<T> tClass,
                                                    IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodSearchVodUrl(appkey, format, method, v, session, pageNum, pageSize, searchKeyword, ConfigMgr.getInstance().getUserName());
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 10.点播历史记录列表
     *
     * @param appkey
     * @param format
     * @param method
     * @param v
     * @param session
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodSearchHistorListDatas(String appkey,
                                                       String format,
                                                       String method,
                                                       String v,
                                                       String session,
                                                       String tag,
                                                       Class<T> tClass,
                                                       IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodPlayHistoryUrl(appkey, format, method, v, session, ConfigMgr.getInstance().getUserName());
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 获取支付结果
     *
     * @param contentId
     * @param type
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodPayResultDatas(String contentId,
                                                String type,
                                                String tag,
                                                Class<T> tClass,
                                                IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodPayResultUrl(ConfigMgr.getInstance().getUserName(), contentId, type);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }

    /**
     * 显示H5支付页面
     *
     * @param contentId
     * @param type
     * @param tag
     * @param tClass
     * @param iHttpRetCallBack
     * @param <T>
     */
    public static <T> void getVodPayWebTips(String contentId,
                                            String type,
                                            String tag,
                                            Class<T> tClass,
                                            IHttpRetCallBack<T> iHttpRetCallBack) {
        String url = HttpUrlCreator.getVodPayActionTipsUrl(ConfigMgr.getInstance().getUserName(), contentId, type);
        HttpX.get(url, null, iHttpRetCallBack, tClass, tag);
    }


}
