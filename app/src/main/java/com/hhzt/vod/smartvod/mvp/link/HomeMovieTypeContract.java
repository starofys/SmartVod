package com.hhzt.vod.smartvod.mvp.link;

import android.support.v4.app.FragmentActivity;

import com.hhzt.vod.api.repBean.CategoryRepBean;
import com.hhzt.vod.smartvod.mvp.iview.BaseView;
import com.hhzt.vod.smartvod.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * Created by Johnson on 2017/12/29.
 */

public class HomeMovieTypeContract {
	public interface IHomeMovieTypeView extends BaseView<HomeMovieTypePresenter> {
		void showData(List<CategoryRepBean> movieTypeNames);
	}

	public interface HomeMovieTypePresenter extends BasePresenter {
		void init();

		void showData(int programGroupId);

		void clearData();

		void destoryInit();

		void switchFragment(FragmentActivity activity, int layoutId, int position);
	}
}
