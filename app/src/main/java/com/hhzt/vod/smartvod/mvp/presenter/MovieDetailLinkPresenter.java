package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.ConfigMgr;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.otherBean.EpisodeBean;
import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.api.repBean.ProgrameDetailBo;
import com.hhzt.vod.api.repData.ProgramDetaiContentDataRep;
import com.hhzt.vod.media.Clarity;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.VideoPlayerActivity;
import com.hhzt.vod.smartvod.callback.MovieDetailCallBack;
import com.hhzt.vod.smartvod.dialog.PayDialogFragment;
import com.hhzt.vod.smartvod.mvp.link.MovieDetailContract;
import com.hhzt.vod.smartvod.mvp.model.IMovieDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @description TODO
 * @Author zengxiaoping
 */

public class MovieDetailLinkPresenter implements MovieDetailContract.MovieDetailPresenter {
    public static final int TYPE_RELATE = 1;
    public static final int TYPE_HOT = 2;

    private Context mContext;
    private IMovieDetail mIMovieDetail;
    private MovieDetailContract.MovieDetailView mMovieDetailView;

    private ProgramDetaiContentDataRep mProgramDetaiContentDataRep;
    private ProgrameDetailBo mProgramDetailBo;
    private List<EpisodeBean> mEpisodeList = new ArrayList<>();
    private List<String> mEpisodeRangeList = new ArrayList<>();
    private ArrayList<MovieInfoData> mReleteList = new ArrayList<>();
    private ArrayList<MovieInfoData> mHotList = new ArrayList<>();

    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    public MovieDetailLinkPresenter(Context context, IMovieDetail IMovieDetail, MovieDetailContract.MovieDetailView movieDetailView) {
        mContext = context;
        mIMovieDetail = IMovieDetail;
        mMovieDetailView = movieDetailView;

        mMovieDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void init() {

    }

    @Override
    public void showData(int programGroupId, int programId, int categoryId) {
        mIMovieDetail.requestMovieDetail(programGroupId, programId, categoryId, new IHttpRetCallBack<ProgramDetaiContentDataRep>() {
            @Override
            public void onResponseSuccess(CommonRspRetBean bean, ProgramDetaiContentDataRep programDetaiContentDataRep) {
                mProgramDetaiContentDataRep = programDetaiContentDataRep;

                mProgramDetailBo = mProgramDetaiContentDataRep.programDetailBo;
                mReleteList.addAll(new ArrayList<MovieInfoData>());
                mHotList.addAll(mProgramDetaiContentDataRep.hotList);

                int number = mProgramDetailBo.getMediaList().size();
                for (int i = 1; i <= number; i++) {
                    EpisodeBean episodeBean = new EpisodeBean();
                    episodeBean.setEpisode(i + "");
                    episodeBean.setTable(0);          //todo 判断是否付费,需要付费改成其他的   0：表示免费     1:表示付费
                    mEpisodeList.add(episodeBean);
                }

                boolean divided = number % 10 == 0;
                if (divided) {
                    number = number / 10;
                } else {
                    number = number / 10 + 1;
                }
                for (int i = 0; i < number; i++) {
                    mEpisodeRangeList.add("" + (i * 10 + 1) + "-" + (i * 10 + 10));
                }
                mMovieDetailView.showMovieDetail(mEpisodeList, mEpisodeRangeList, mProgramDetailBo);

                mMovieDetailView.showMovieRelate(mReleteList);
                mMovieDetailView.showMovieHot(mHotList);

                List<Clarity> clarities = new ArrayList<>();
                Resources resources = mContext.getResources();
                clarities.add(new Clarity(resources.getString(R.string.media_clarities_normal), resources.getString(R.string.media_clarities_270p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
                clarities.add(new Clarity(resources.getString(R.string.media_clarities_high), resources.getString(R.string.media_clarities_480p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
                clarities.add(new Clarity(resources.getString(R.string.media_clarities_super), resources.getString(R.string.media_clarities_720p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
                clarities.add(new Clarity(resources.getString(R.string.media_clarities_blue), resources.getString(R.string.media_clarities_1080p), mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(0).getFilePath()));
                mMovieDetailView.showSmallVideo(clarities, mProgramDetailBo.getName(), mProgramDetailBo.getSmallPoster());
            }

            @Override
            public void onResponseFailed(CommonRspRetBean bean) {

            }

            @Override
            public void onError(String result) {

            }

            @Override
            public void onCancelled() {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void clickOtherMovieDetail(MovieDetailCallBack movieDetailCallBack, int type, int position) {
        int programId = 0;
        int categoryId = 0;
        switch (type) {
            case TYPE_RELATE:
                categoryId = mReleteList.get(position).getId();
                programId = mReleteList.get(position).getProgramId();
                break;
            case TYPE_HOT:
                categoryId = mHotList.get(position).getId();
                programId = mHotList.get(position).getProgramId();
                break;
            default:
                break;
        }
        movieDetailCallBack.showMovieDetailCallBack(0, programId, categoryId);
    }

    @Override
    public void clearData() {
        mReleteList.clear();
        mHotList.clear();
    }

    @Override
    public void destoryInit() {
        mContext = null;
    }

    @Override
    public void toVideoPlayerActivity(Context packageContext, Class<?> cls, int position) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra(VideoPlayerActivity.MOVIEW_PLAYER_URL, mProgramDetaiContentDataRep.programDetailBo.getMediaList().get(position).getFilePath());
        packageContext.startActivity(intent);
    }

    @Override
    public void showPayWeb(FragmentManager fragmentManager) {
        String contentId = mProgramDetaiContentDataRep.programDetailBo.getId() + "";
        PayDialogFragment payDialogFragment = PayDialogFragment.getInstance(ConfigMgr.getInstance().getUserName(), contentId, 1 + "");
        payDialogFragment.show(fragmentManager, "showPayWeb");

//        checkPayResult(contentId, "1", payDialogFragment);
    }

    public void checkPayResult(final String contentId, final String type, final PayDialogFragment payDialogFragment) {
        mUIHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIMovieDetail.requestVodPayResult(contentId, type, new IHttpRetCallBack<CommonRspRetBean>() {

                    @Override
                    public void onResponseSuccess(CommonRspRetBean bean, CommonRspRetBean commonRspRetBean) {
                        System.out.print("onResponseSuccess:" + bean.msg);
                        if (bean.isSuccess()) {
                            payDialogFragment.dismiss();
                        }
                    }

                    @Override
                    public void onResponseFailed(CommonRspRetBean bean) {
                        System.out.print("onResponseFailed:" + bean.msg);
                        checkPayResult(contentId, type, payDialogFragment);
                    }

                    @Override
                    public void onError(String result) {
                        System.out.print("onError:" + result);
                        checkPayResult(contentId, type, payDialogFragment);
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
        }, 1000);
    }
}
