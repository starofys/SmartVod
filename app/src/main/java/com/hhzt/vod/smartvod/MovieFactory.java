package com.hhzt.vod.smartvod;

/**
 * Created by duanyitao on 2017/12/29.
 *
 * @description TODO
 * @Author duanyitao
 */

public class MovieFactory {
    public static final int MOVIE_TYPE_BIG_PICTURE = 0;  //大图
    public static final int MOVIE_TYPE_MIX_PICTURE = 1;  //混合图（大图 and 小图）
    public static final int MOVIE_TYPE_SMALL_PICTURE = 2;//小图

    public static BaseFragment getFragment(int movieType,int categroy) {
        BaseFragment fragment;
        switch (movieType) {
            case MOVIE_TYPE_BIG_PICTURE:
                fragment = MovieBigPictureListFragment.getIntance(categroy);
                break;
            case MOVIE_TYPE_MIX_PICTURE:
                fragment = MovieMixPictureListFragment.getIntance(categroy);
                break;
            case MOVIE_TYPE_SMALL_PICTURE:
            default:
                fragment = MovieSmallPictureListFragment.getIntance(categroy);
                break;
        }
        return fragment;
    }
}
