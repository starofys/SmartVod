package com.hhzt.vod.smartvod.mvp.link;

import android.content.Context;

import com.hhzt.vod.api.repBean.MovieInfoData;
import com.hhzt.vod.smartvod.mvp.iview.BaseView;
import com.hhzt.vod.smartvod.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @Author zengxiaoping
 */

public class HomeMovieListContract {
	public interface HomeMovieListView extends BaseView<HomeMovieListPresenter> {
		void showData(List<MovieInfoData> movieInfoData);
	}

	public interface HomeMovieListPresenter extends BasePresenter {
		void init();

		void showData(int programGroupId,int categoryId, int pageNum, int pageSize);

		void clearData();

		void destoryInit();

		void toMovieDetail(Context packageContext, Class<?> cls, int position);
	}
}
