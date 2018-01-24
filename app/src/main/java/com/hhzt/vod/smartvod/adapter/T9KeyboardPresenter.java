package com.hhzt.vod.smartvod.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzt.vod.api.otherBean.KeyBean;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.adapter.viewHolder.T9KeyboardViewHolder;
import com.hhzt.vod.smartvod.callback.T9ClickCallBack;
import com.hhzt.vod.smartvod.constant.ConfigX;
import com.hhzt.vod.viewlayer.androidtvwidget.bridge.RecyclerViewBridge;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.hhzt.vod.viewlayer.androidtvwidget.leanback.mode.OpenPresenter;
import com.hhzt.vod.viewlayer.androidtvwidget.view.ReflectItemView;

import java.util.ArrayList;

/**
 * T9键盘
 * zengxiaoping
 */
public class T9KeyboardPresenter extends OpenPresenter {

	private ArrayList<KeyBean> mKeyList;
	private GeneralAdapter mAdapter;
	private Context mContext;
	private Handler mHandler;
	private RecyclerViewBridge mRecyclerViewBridge;
	private T9ClickCallBack mT9ClickCallBack;
	private int mClickPosition = 0;
	private boolean mEditState = false;

	public T9KeyboardPresenter(Context context, ArrayList<KeyBean> mEpisodeList, Handler handler, RecyclerViewBridge recyclerViewBridge) {
		this.mKeyList = mEpisodeList;
		this.mContext = context;
		this.mHandler = handler;
		this.mRecyclerViewBridge = recyclerViewBridge;
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

		showT9Expanding(t9KeyboardViewHolder, mEditState);

		t9KeyboardViewHolder.mRivT9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mEditState = true;
				mClickPosition = position;
				showExpanding(t9KeyboardViewHolder, position);
			}
		});
		t9KeyboardViewHolder.mRivT9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (mEditState && hasFocus) {
					mEditState = false;
					mAdapter.notifyItemChanged(mClickPosition);
				}
			}
		});
	}

	public void showExpanding(final T9KeyboardViewHolder viewHolder, final int position) {
		showT9Expanding(viewHolder, mEditState);

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

//		viewHolder.mRivT9ExpandingUp.setOnKeyListener(new OnKeyListener(position, mAdapter, mEditState));
//		viewHolder.mRivT9ExpandingLeft.setOnKeyListener(new OnKeyListener(position, mAdapter, mEditState));
//		viewHolder.mRivT9ExpandingDown.setOnKeyListener(new OnKeyListener(position, mAdapter, mEditState));
//		viewHolder.mRivT9ExpandingRight.setOnKeyListener(new OnKeyListener(position, mAdapter, mEditState));
		viewHolder.mRivT9ExpandingUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickPositionByShowT9(position, 0, numberKeyBoard, numberKeyBoard, viewHolder);
			}
		});
		viewHolder.mRivT9ExpandingLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickPositionByShowT9(position, 1, letterKeyBoard, keyLetter[0], viewHolder);
			}
		});
		viewHolder.mRivT9ExpandingDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickPositionByShowT9(position, 2, letterKeyBoard, keyLetter[1], viewHolder);
			}
		});
		viewHolder.mRivT9ExpandingRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickPositionByShowT9(position, 3, letterKeyBoard, keyLetter[2], viewHolder);
			}
		});

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				ReflectItemView rivT9ExpandingUp = viewHolder.mRivT9ExpandingUp;
				mRecyclerViewBridge.setFocusView(rivT9ExpandingUp, ConfigX.SCALE);
				rivT9ExpandingUp.requestLayout();
				rivT9ExpandingUp.requestFocus();
			}
		}, 10);
	}

	private void clickPositionByShowT9(int position, int childPosition, String parentKeyName, String childKeyName, final T9KeyboardViewHolder viewHolder) {
		if (mT9ClickCallBack != null) {
			mT9ClickCallBack.t9KeyBoardClickPosition(position, childPosition, parentKeyName, childKeyName);
			mEditState = false;
			showT9Expanding(viewHolder, false);
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mRecyclerViewBridge.setFocusView(viewHolder.mRivT9, ConfigX.SCALE);
					viewHolder.mRivT9.requestLayout();
					viewHolder.mRivT9.requestFocus();
				}
			}, 10);
		}
	}

	public void showT9Expanding(T9KeyboardViewHolder viewHolder, boolean show) {
		viewHolder.mLlT9.setVisibility(show ? View.GONE : View.VISIBLE);
		viewHolder.mLlT9Expanding.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	private class OnKeyListener implements View.OnKeyListener {

		private int position;
		private GeneralAdapter adapter;
		private boolean editState;

		public OnKeyListener(int position, GeneralAdapter adapter, boolean editState) {
			this.position = position;
			this.adapter = adapter;
			this.editState = editState;
		}

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				if (position <= 2 && editState) {
					editState = false;
					adapter.notifyItemChanged(position);
				}
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				if (position % 3 == 0 && position <= 2 && editState) {
					editState = false;
					adapter.notifyItemChanged(position);
				}
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				if (position >= 6 && position <= 2 && editState) {
					editState = false;
					adapter.notifyItemChanged(position);
				}
			}
			return false;
		}
	}
}
