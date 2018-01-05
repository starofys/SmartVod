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
 * 支付成功
 * Created by Administrator on 2017/12/31 0031.
 */

public class PayEndDialogFragment extends DialogFragment {

    public static PayEndDialogFragment getInstance() {
        PayEndDialogFragment fragment = new PayEndDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pay_tip_end, null);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
