package com.hhzt.vod.smartvod.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class PayStartDialogFragment extends DialogFragment {
    public static final String SINGLE_MOVIE_MONEY = "singleMovieMoney";
    public static final String ONE_DAY_MOVIE_MONEY = "oneDayMovieMoney";

    private ReflectItemView mRivLeft;
    private ReflectItemView mRivRight;
    private TextView mTvPayTotalMoney;
    private TextView mTvLeftMoney;
    private TextView mTvRightMoney;

    View.OnClickListener mLeftClick;
    View.OnClickListener mRightClick;


    private String mSingleMovieMoney;
    private String mOneDayMovieMoney;

    public static PayStartDialogFragment getInstance(String singleMovieMoney, String oneDayMovieMoney) {
        PayStartDialogFragment fragment = new PayStartDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SINGLE_MOVIE_MONEY, singleMovieMoney);
        bundle.putString(ONE_DAY_MOVIE_MONEY, oneDayMovieMoney);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setLeftClickLisenter(View.OnClickListener leftClick) {
        mLeftClick = leftClick;
        if (mRivLeft != null) mRivLeft.setOnClickListener(mLeftClick);
    }

    public void setRightClickLisenter(View.OnClickListener rightClick) {
        mRightClick = rightClick;
        if (mRivRight != null) mRivLeft.setOnClickListener(mRightClick);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pay_tip_start, null);

        mSingleMovieMoney = getArguments().getString(SINGLE_MOVIE_MONEY);
        mOneDayMovieMoney = getArguments().getString(ONE_DAY_MOVIE_MONEY);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRivLeft = view.findViewById(R.id.riv_left);
        mRivRight = view.findViewById(R.id.riv_right);
        mTvPayTotalMoney = view.findViewById(R.id.tv_pay_total_money);
        mTvLeftMoney = view.findViewById(R.id.tv_left_money);
        mTvRightMoney = view.findViewById(R.id.tv_right_money);

        mRivLeft.setOnClickListener(mLeftClick);
        mRivRight.setOnClickListener(mRightClick);
    }

    private void initData() {
        mTvPayTotalMoney.setText("￥" + mSingleMovieMoney + "元");
        mTvLeftMoney.setText(mSingleMovieMoney);
        mTvRightMoney.setText(mOneDayMovieMoney);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
