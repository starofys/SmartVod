package com.hhzt.vod.api;

import com.hhzt.vod.api.repData.CategoryBoDatasRep;
import com.hhzt.vod.api.repData.ProgramDatasRep;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;
import com.hhzt.vod.api.repData.ProgramSuperDataRep;
import com.hhzt.vod.api.repData.SearchMainDatasRep;
import com.hhzt.vod.api.repData.SimpleResultRep;
import com.hhzt.vod.api.repData.VodGroupDetailDataRep;
import com.hhzt.vod.api.repData.VodSearchDataRep;

/**
 * Created by wujichang on 2017/12/30.
 */

public class HttpApiTestEng {

    /**
     * 接口名称:点播主界面
     */
    public static void testHttpVod01() {
        HttpVod.getVodRecommendDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_01,
                HttpConst.VERSION,
                "",
                HttpConst.USERNAME,
                "",
                "",
                "getVodRecommendDatas",
                ProgramDatasRep.class,
                new IHttpRetCallBack<ProgramDatasRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, ProgramDatasRep programDatasRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                });
    }

    /**
     * 接口名称:点播分组下分类导航
     */
    public static void testHttpVod02() {
        HttpVod.getVodMenuByGroupDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_02,
                HttpConst.VERSION,
                "",
                HttpConst.USERNAME,
                1,
                "getVodMenuByGroupDatas",
                CategoryBoDatasRep.class,
                new IHttpRetCallBack<CategoryBoDatasRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, CategoryBoDatasRep categoryBoDatasRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播分组下分类详情
     */
    public static void testHttpVod03() {
        HttpVod.getVodCategoryDetailByGroupDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_03,
                HttpConst.VERSION,
                "",
                1,
                12,
                1001,
                HttpConst.USERNAME,
                1,
                "",
                "getVodCategoryDetailByGroupDatas",
                VodGroupDetailDataRep.class,
                new IHttpRetCallBack<VodGroupDetailDataRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, VodGroupDetailDataRep vodGroupDetailDataRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播节目详情
     */
    public static void testHttpVod04() {
        HttpVod.getVodItemDetailDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_04,
                HttpConst.VERSION,
                "",
                29,
                99999,
                HttpConst.USERNAME,
                1,
                "getVodItemDetailDatas",
                ProgramDetaiContentDataRep.class,
                new IHttpRetCallBack<ProgramDetaiContentDataRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, ProgramDetaiContentDataRep programDetaiContentDataRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播节目播放记录和热度统计(热度每播放一次加一)
     */
    public static void testHttpVod05() {
        HttpVod.getVodPlayRecordDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_05,
                HttpConst.VERSION,
                "",
                HttpConst.USERNAME,
                37,
                "192.168.1.149",
                5555,
                10,
                "getVodPlayRecordDatas",
                SimpleResultRep.class,
                new IHttpRetCallBack<SimpleResultRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, SimpleResultRep simpleResultRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播节目评星
     */
    public static void testHttpVod06() {
        HttpVod.getVodItemStarDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_06,
                HttpConst.VERSION,
                "",
                1,
                5,
                "getVodItemStarDatas",
                SimpleResultRep.class,
                new IHttpRetCallBack<SimpleResultRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, SimpleResultRep simpleResultRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播搜索热度记录(每搜索一次加1)
     */
    public static void testHttpVod07() {
        HttpVod.getVodItemSearchRecordNumberDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_07,
                HttpConst.VERSION,
                "",
                1,
                "getVodItemSearchRecordNumberDatas",
                SimpleResultRep.class,
                new IHttpRetCallBack<SimpleResultRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, SimpleResultRep simpleResultRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播搜索主页
     */
    public static void testHttpVod08() {
        HttpVod.getVodSearchMainDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_08,
                HttpConst.VERSION,
                "",
                HttpConst.USERNAME,
                "getVodSearchMainDatas",
                SearchMainDatasRep.class,
                new IHttpRetCallBack<SearchMainDatasRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, SearchMainDatasRep searchMainDatasRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播搜索
     */
    public static void testHttpVod09() {
        HttpVod.getVodSearchKeyCodeDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_09,
                HttpConst.VERSION,
                "",
                1,
                8,
                "it",
                HttpConst.USERNAME,
                "getVodSearchKeyCodeDatas",
                VodSearchDataRep.class,
                new IHttpRetCallBack<VodSearchDataRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, VodSearchDataRep vodSearchDataRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }

    /**
     * 接口名称:点播历史记录列表
     */
    public static void testHttpVod10() {
        HttpVod.getVodSearchHistorListDatas(
                HttpConst.APP_KEY,
                HttpConst.FORMAT,
                HttpConst.METHOD_10,
                HttpConst.VERSION,
                "",
                HttpConst.USERNAME,
                "getVodSearchHistorListDatas",
                ProgramSuperDataRep.class,
                new IHttpRetCallBack<ProgramSuperDataRep>() {
                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, ProgramSuperDataRep programSuperDataRep) {
                        System.out.print("onResponseSuccess");
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed");
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError");
                    }

                    @Override
                    public void onCancelled() {
                        System.out.print("onCancelled");
                    }

                    @Override
                    public void onFinish() {
                        System.out.print("onFinish");
                    }
                }
        );
    }
}
