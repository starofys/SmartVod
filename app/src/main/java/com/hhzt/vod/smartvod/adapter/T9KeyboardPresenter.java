package com.hhzt.vod.smartvod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.callback.T9ClickCallBack;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.ArrayList;

/**
 * T9键盘
 * zengxiaoping
 */
public class T9KeyboardPresenter extends OpenPresenter {

	private ArrayList<KeyBean> mKeyList;
	private GeneralAdapter mAdapter;
	private Context mContext;
	private T9ClickCallBack mT9ClickCallBack;
	private int mFoucusPosition = 0;

	public T9KeyboardPresenter(Context context, ArrayList<KeyBean> mEpisodeList) {
		this.mKeyList = mEpisodeList;
		this.mContext = context;
	}

	public void setT9ClickCallBack(T9ClickCallBack t9ClickCallBack) {
		this.mT9ClickCallBack = t9ClickCallBack;
	}

	@Override
	public void setAdapter(GeneralAdapter adapter) {
		this.mAdapter = adapter;
	}

	@Override
	public int getItemCount() {
		return mKeyList.size();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t9_keyboard, parent, false);
		return new T9KeyboardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		final T9KeyboardViewHolder t9KeyboardViewHolder = (T9KeyboardViewHolder) viewHolder;
		KeyBean keyBean = mKeyList.get(position);
		t9KeyboardViewHolder.mTvKeyNumber.setText(keyBean.getNumberKeyBoard());
		t9KeyboardViewHolder.mTvKeyLetter.setText(keyBean.getLetterKeyBoard());

		t9KeyboardViewHolder.mRivT9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showExpanding(t9KeyboardViewHolder, position);
			}
		});
	}

	public void showExpanding(final T9KeyboardViewHolder viewHolder, final int position) {
		showT9Expanding(viewHolder, true);

		final String keyLetter[] = new String[3];
		final String letterKeyBoard = mKeyList.get(position).getLetterKeyBoard();
		final String numberKeyBoard = mKeyList.get(position).getNumberKeyBoard();
		for (int i = 0; i < letterKeyBoard.length(); i++) {
			keyLetter[i] = letterKeyBoard.substring(i, i + 1);
		}

		viewHolder.mTvT9ExpandingUp.setText(numberKeyBoard);
		viewHolder.mTvT9ExpandingLeft.setText(keyLetter[0]);
		viewHolder.mTvT9ExpandingDown.setText(keyLetter[1]);
		viewHolder.mTvT9ExpandingRight.setText(keyLetter[2]);

		viewHolder.mRivT9ExpandingUp.requestLayout();
		viewHolder.mRivT9ExpandingUp.requestFocus();

//		final boolean[] upFoucus = {false};
//		final boolean[] leftFoucus = {false};
//		final boolean[] downFoucus = {false};
//		final boolean[] rightFoucus = {false};
//		viewHolder.mRivT9ExpandingUp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				upFoucus[0] = hasFocus;
//				if (!hasFocus) {
//					if (!leftFoucus[0] && !downFoucus[0] && !rightFoucus[0])
//						showT9Expanding(viewHolder, false);
//				}
//			}
//		});
//		viewHolder.mRivT9ExpandingLeft.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				leftFoucus[0] = hasFocus;
//				if (!hasFocus) {
//					if (!upFoucus[0] && !downFoucus[0] && !rightFoucus[0])
//						showT9Expanding(viewHolder, false);
//				}
//			}
//		});
//		viewHolder.mRivT9ExpandingDown.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				downFoucus[0] = hasFocus;
//				if (!hasFocus) {
//					if (!upFoucus[0] && !leftFoucus[0] && !rightFoucus[0])
//						showT9Expanding(viewHolder, false);
//				}
//			}
//		});
//		viewHolder.mRivT9ExpandingRight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				rightFoucus[0] = hasFocus;
//				if (!hasFocus) {
//					if (!upFoucus[0] && !leftFoucus[0] && !downFoucus[0])
//						showT9Expanding(viewHolder, false);
//				}
//			}
//		});

		viewHolder.mRivT9ExpandingUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mT9ClickCallBack != null) {
					mT9ClickCallBack.t9KeyBoardClickPosition(position, 0, numberKeyBoard, numberKeyBoard);
					showT9Expanding(viewHolder, false);
				}
			}
		});
		viewHolder.mRivT9ExpandingLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mT9ClickCallBack != null) {
					mT9ClickCallBack.t9KeyBoardClickPosition(position, 1, letterKeyBoard, keyLetter[0]);
					showT9Expanding(viewHolder, false);
				}
			}
		});
		viewHolder.mRivT9ExpandingDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mT9ClickCallBack != null) {
					mT9ClickCallBack.t9KeyBoardClickPosition(position, 2, letterKeyBoard, keyLetter[1]);
					showT9Expanding(viewHolder, false);
				}
			}
		});
		viewHolder.mRivT9ExpandingRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mT9ClickCallBack != null) {
					mT9ClickCallBack.t9KeyBoardClickPosition(position, 3, letterKeyBoard, keyLetter[2]);
					showT9Expanding(viewHolder, false);
				}
			}
		});


	}

	public void showT9Expanding(T9KeyboardViewHolder viewHolder, boolean show) {
		viewHolder.mLlT9.setVisibility(show ? View.GONE : View.VISIBLE);
		viewHolder.mLlT9Expanding.setVisibility(show ? View.VISIBLE : View.GONE);
	}

}
