package com.hhzt.vod.smartvod;

/**
 * Created by duanyitao on 2017/12/29.
 *
 * @description TODO
 * @Author duanyitao
 */

public class MovieFactory {
    public static final int MOVIE_SHOW_TYPE_BIG_PICTURE = 0;  //大图
    public static final int MOVIE_SHOW_TYPE_MIX_PICTURE = 1;  //混合图（大图 and 小图）
    public static final int MOVIE_SHOW_TYPE_SMALL_PICTURE = 2;//小图

    public static BaseFragment getFragment(int movieShowType, int categroyId) {
        BaseFragment fragment;
        switch (movieShowType) {
            case MOVIE_SHOW_TYPE_BIG_PICTURE:
                fragment = MovieBigPictureListFragment.getIntance(categroyId);
                break;
            case MOVIE_SHOW_TYPE_MIX_PICTURE:
                fragment = MovieMixPictureListFragment.getIntance(categroyId);
                break;
            case MOVIE_SHOW_TYPE_SMALL_PICTURE:
            default:
                fragment = MovieSmallPictureListFragment.getIntance(categroyId);
                break;
        }
        return fragment;
    }
}
