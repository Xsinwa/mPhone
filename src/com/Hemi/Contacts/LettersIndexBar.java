package com.Hemi.Contacts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LettersIndexBar extends View {
	private Paint mPaint;
	private char[] letters;
	private Rect mRect;
	private int paddingTop;
	private int paddingBootom;
	private int mIndex=-1;
	private float beginY;
	private float mCellHeight;

public LettersIndexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LettersIndexBar(Context context) {
		super(context);
	}


	public LettersIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		letters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '#' };
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#949494"));
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mPaint.setTextSize(24);
		mPaint.setAntiAlias(true);
//		mPaint.setTextAlign(Paint.Align.CENTER);
		mRect=new Rect();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (letters != null) {
			
			for (int i = 0; i < letters.length; i++) {
				String text = String.valueOf(letters[i]);
				mPaint.getTextBounds(text, 0, text.length(), mRect);
				float textWidth = mPaint.measureText(text);
				float textHeight = mRect.height();
				float mHeight = getMeasuredHeight() - paddingBootom - paddingTop;
				int mCellWidth = getMeasuredWidth();
				mCellHeight = (mHeight) * 1.0f / 25;
				beginY = (mHeight - mCellHeight * letters.length) * 0.5f;
				float x = mCellWidth * 0.5f - textWidth * 0.5f;
				float y = mCellHeight * 0.5f + textHeight * 0.5f + mCellHeight * i + beginY + paddingTop;
				mPaint.setColor(Color.parseColor("#5CACEE"));
				canvas.drawText(text, x, y, mPaint);
			}
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float y = event.getY();
		invalidate();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);
			checkIndex(y);
			break;

		case MotionEvent.ACTION_MOVE:
			checkIndex(y);
			break;

		case MotionEvent.ACTION_UP:
			mIndex = -1;
			break;

		default:
			break;
		}
		
		return super.onTouchEvent(event);
	}
	
	
	private void checkIndex(float y) {
		int currentIndex;
        if (y < beginY+getPaddingTop()) {
            return;
        }
        currentIndex = (int) ((y - beginY-paddingTop) / mCellHeight);
        if (currentIndex != mIndex) {
            if (mOnLetterChangeListener != null) {
                if (letters != null && currentIndex < letters.length) {
                    mOnLetterChangeListener.onLetterChange(currentIndex,String.valueOf(letters[currentIndex]));
                    mIndex = currentIndex;
                }
            }
        }
	}
	
	public interface OnLetterChangeListener {
        void onLetterChange(int position, String letter);
    }

    private OnLetterChangeListener mOnLetterChangeListener;

    @SuppressWarnings("unused")
    public OnLetterChangeListener getOnLetterChangeListener() {
        return mOnLetterChangeListener;
    }

    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener) {
        mOnLetterChangeListener = onLetterChangeListener;
    }

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		paddingTop = getPaddingTop();
		paddingBootom = getPaddingBottom();
	}

}
