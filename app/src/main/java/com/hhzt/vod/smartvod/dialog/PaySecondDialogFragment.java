package com.hhzt.vod.smartvod.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class PaySecondDialogFragment extends DialogFragment {
    public static final int PAY_SINGLE_MOVIE = 0;
    public static final int PAY_ONE_DAY_MOVIE = 1;

    public static final String ALIPAY_QR_CODE = "alipayQrCode";
    public static final String WECHAT_QR_CODE = "wechatQrCode";
    public static final String PAY_TYPE = "payType";
    public static final String PAY_MONEY = "payMoney";

    private ReflectItemView mRivLeft;
    private ReflectItemView mRivRight;
    private TextView mTvPayTypeTip;
    private TextView mTvPayTotalMoney;
    private ImageView mIvSingleMovie;
    private ImageView mIvOneDayMovie;

    View.OnClickListener mLeftClick;
    View.OnClickListener mRightClick;

    private String mAlipayQrCodeUrl;
    private String mWechatQrCodeUrl;
    private int mPayType = PAY_SINGLE_MOVIE;
    private String mPayMoney;

    public static PaySecondDialogFragment getInstance(String alipayQrCodeUrl, String wechatQrCodeUrl, int payType, String payMoney) {
        PaySecondDialogFragment fragment = new PaySecondDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ALIPAY_QR_CODE, alipayQrCodeUrl);
        bundle.putString(WECHAT_QR_CODE, wechatQrCodeUrl);
        bundle.putInt(PAY_TYPE, payType);
        bundle.putString(PAY_MONEY, payMoney);
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
        View view = inflater.inflate(R.layout.dialog_pay_tip_second, null);

        mAlipayQrCodeUrl = getArguments().getString(ALIPAY_QR_CODE);
        mWechatQrCodeUrl = getArguments().getString(WECHAT_QR_CODE);
        mPayType = getArguments().getInt(PAY_TYPE);
        mWechatQrCodeUrl = getArguments().getString(PAY_MONEY);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRivLeft = view.findViewById(R.id.riv_left);
        mRivRight = view.findViewById(R.id.riv_right);
        mTvPayTypeTip = view.findViewById(R.id.tv_pay_type_tip);
        mTvPayTotalMoney = view.findViewById(R.id.tv_pay_total_money);
        mIvSingleMovie = view.findViewById(R.id.iv_single_movie);
        mIvOneDayMovie = view.findViewById(R.id.iv_one_day_movie);

        mRivLeft.setOnClickListener(mLeftClick);
        mRivRight.setOnClickListener(mRightClick);
    }

    private void initData() {
        if (mPayType == PAY_SINGLE_MOVIE) {
            mTvPayTypeTip.setText(getString(R.string.pay_single_movie));
        } else if (mPayType == PAY_ONE_DAY_MOVIE) {
            mTvPayTypeTip.setText(getString(R.string.pay_one_day_movie));
        }
        mTvPayTotalMoney.setText("￥" + mPayMoney + "元");

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        x.image().bind(mIvSingleMovie, mAlipayQrCodeUrl, imageOptions);
        imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        x.image().bind(mIvOneDayMovie, mWechatQrCodeUrl, imageOptions);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
