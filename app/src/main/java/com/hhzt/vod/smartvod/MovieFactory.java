package com.hhzt.vod.smartvod;

/**
 * Created by zengxiaoping on 2017/12/29.
 *
 * @Author zengxiaoping
 */

public class MovieFactory {
    public static final int MOVIE_SHOW_TYPE_BIG_PICTURE = 0;  //大图(推荐)
    public static final int MOVIE_SHOW_TYPE_MIX_PICTURE = 1;  //混合图（大图 and 小图）
    public static final int MOVIE_SHOW_TYPE_SMALL_PICTURE = 2;//小图

    public static BaseFragment getFragment(int movieShowType, int categroyId) {
        BaseFragment fragment;
        switch (movieShowType) {
            case MOVIE_SHOW_TYPE_BIG_PICTURE:
                fragment = MovieBigPictureListFragment.getIntance();
                break;
            case MOVIE_SHOW_TYPE_MIX_PICTURE:
//                break;
//            case MOVIE_SHOW_TYPE_SMALL_PICTURE:
            default:
                fragment = MovieMixPictureListFragment.getIntance(categroyId);
//                fragment = MovieSmallPictureListFragment.getIntance(categroyId);
                break;
        }
        return fragment;
    }
}
