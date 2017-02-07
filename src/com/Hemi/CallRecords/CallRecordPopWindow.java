package com.Hemi.CallRecords;

import com.Hemi.mphone.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class CallRecordPopWindow extends PopupWindow implements OnClickListener {

	private View contentView;
	private LinearLayout mCancel;
	private TextView ClearAll;

	public CallRecordPopWindow(final Activity context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView =inflater.inflate(R.layout.callrecord_pop, null);
		this.setContentView(contentView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.update();
		this.setBackgroundDrawable(new ColorDrawable(0xa0000000));
		contentView.setOnTouchListener(listener);
		
		
		initView();
	}
	
	private void initView() {
		mCancel=(LinearLayout) contentView.findViewById(R.id.pop_cancel);
		ClearAll=(TextView) contentView.findViewById(R.id.clearAll);
		mCancel.setOnClickListener(this);
		ClearAll.setOnClickListener(this);
	}

	OnTouchListener listener= new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int height =v.findViewById(R.id.pop_head).getTop();
			int y=(int) event.getY();
			if(event.getAction()==MotionEvent.ACTION_UP){
				if(y<height){
					dismiss();
				}
			}
			return true;
		}
	};
	
	public void showPopUpwindow(View parent){
		if(!this.isShowing()){
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0); 
		}else{
			this.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pop_cancel:
			dismiss();
			break;
			
		case R.id.clearAll:
			
			break;

		default:
			break;
		}
	}

}
