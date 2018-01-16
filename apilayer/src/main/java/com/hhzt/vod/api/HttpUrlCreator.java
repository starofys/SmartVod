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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
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
		return ConfigMgr.getInstance().getEpgUrlHost()
				+ "appkey=" + appkey
				+ "&format=" + format
				+ "&method=" + method
				+ "&v=" + v
				+ "&session=" + session
				+ "&pageNum=" + pageNum
				+ "&pageSize=" + pageSize
				+ "&searchKeyword=" + searchKeyword
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
		return ConfigMgr.getInstance().getEpgUrlHost()
				+ "appkey=" + appkey
				+ "&format=" + format
				+ "&method=" + method
				+ "&v=" + v
				+ "&session=" + session
				+ "&username=" + username;
	}

	/**
	 * 点播鉴权接口
	 *
	 * @param userName
	 * @param contentId
	 * @param type
	 * @return
	 */
	public static String getVodPayResultUrl(String userName, int contentId, int type) {
		return ConfigMgr.getInstance().getPayUrlHost()
				+ "authentication?"
				+ "username=" + userName
				+ "&contentId=" + contentId
				+ "&type=" + type;
	}

	/**
	 * 支付提示页面展示接口
	 *
	 * @param userName  用户名
	 * @param contentId 媒体id
	 * @param type      媒体类型
	 * @return
	 */
	public static String getVodPayActionTipsUrl(String userName, int contentId, int type) {
		return ConfigMgr.getInstance().getPayUrlHost()
				+ "productInfo?"
				+ "username=" + userName
				+ "&contentId=" + contentId
				+ "&type=" + type;
	}

	/**
	 * 试看结束提示付款界面访问地址
	 *
	 * @param userName  用户名
	 * @param contentId 媒体id
	 * @param type      媒体类型
	 * @return
	 */
	public static String getVodPayLookActionTipsUrl(String userName, int contentId, int type) {
		return ConfigMgr.getInstance().getPayUrlHost()
				+ "productInfoLook?"
				+ "username=" + userName
				+ "&contentId=" + contentId
				+ "&type=" + type;
	}

	/**
	 * weather天气
	 *
	 * @param v
	 * @param method
	 * @param appkey
	 * @param format
	 * @return
	 */
	public static String getWeatherUrl(String v,
	                                   String method,
	                                   String appkey,
	                                   String format) {
		return ConfigMgr.getInstance().getEpgUrlHost()
				+ "v=" + v
				+ "&method=" + method
				+ "&appkey=" + appkey
				+ "&format=" + format;

	}
}
