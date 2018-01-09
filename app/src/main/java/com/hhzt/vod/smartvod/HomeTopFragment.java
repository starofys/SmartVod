package com.hhzt.vod.smartvod;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhzt.vod.smartvod.mvp.iview.IHomeTopView;

import org.xutils.view.annotation.ContentView;

/**
 * Created by Johnson on 2017/12/29.
 */
@ContentView(R.layout.fragment_top)
public class HomeTopFragment extends BaseFragment implements IHomeTopView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showDataTime() {

    }

    @Override
    public void showWeather() {

    }
}
