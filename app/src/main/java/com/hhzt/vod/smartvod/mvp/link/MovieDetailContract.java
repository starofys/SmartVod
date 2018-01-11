package com.hhzt.vod.smartvod.mvp.link;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.hhzt.vod.api.otherBean.EpisodeBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;
import com.hhzt.vod.media.Clarity;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
import com.hhzt.vod.smartvod.mvp.iview.BaseView;
import com.hhzt.vod.smartvod.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class MovieDetailContract {
    public interface MovieDetailView extends BaseView<MovieDetailPresenter> {
        void showSmallVideo(List<Clarity> clarities, String movieName, String urlIcon);

        void showMovieDetail(List<EpisodeBean> episodeList, List<String> episodeRangeList, ProgrameDetailBo programDetailBo);

        void showMovieRelate(ArrayList<MovieInfoData> relevantList);

        void showMovieHot(ArrayList<MovieInfoData> hotList);
    }

    public interface MovieDetailPresenter extends BasePresenter {
        void init();

        void showData(int programGroupId, int programId, int categoryId);

        void clickOtherMovieDetail(MovieDetailCallBack movieDetailCallBack, int type, int position);

        void clearData();

        void destoryInit();

        void toVideoPlayerActivity(Context packageContext, Class<?> cls, int position);

        void showPayWeb(FragmentManager fragmentManager);
    }
}
