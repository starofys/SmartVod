package com.hhzt.vod.smartvod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhzt.vod.logiclayer.FragmentUtil;

import org.xutils.view.annotation.ContentView;

/**
 * Created by wujichang on 2017/12/28.
 */
@ContentView(R.layout.activity_movie_detail)
public class MovieDetailActivity extends BaseActivity {

    public static final String MOVIE_TYPE_ID = "movie_tyype_Id";
    public static final String MOVIE_DETAIL_ID = "movie_Detail_Id";

    //电影类型id、具体电影id
    private int mMovieTypeId;
    private int mMovieDetailId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMovieTypeId = intent.getIntExtra(MOVIE_TYPE_ID, 0);
        mMovieDetailId = intent.getIntExtra(MOVIE_DETAIL_ID, 0);

        MovieDetailFragment movieDetailFragment =  MovieDetailFragment.getInstance(mMovieTypeId,mMovieDetailId);
        FragmentUtil.replace(this, false, R.id.movie_detail_fragment_container, movieDetailFragment);
    }
}
