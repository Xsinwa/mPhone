package com.Hemi.DialPadActivity;

import com.Hemi.Contacts.NewContacts;
import com.Hemi.mphone.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.widget.Toast;

public class DialPadPopwindow extends PopupWindow {
	private TextView AddNew;
	private TextView AddExists;
	private TextView SendMessage;
	private LinearLayout cancel;
	private View contentView;
	
	
	public DialPadPopwindow(final Activity context) {
		super(context);
		
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView=inflater.inflate(R.layout.dialpad_pop, null);
		this.setContentView(contentView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.update();
		this.setBackgroundDrawable(new ColorDrawable(0xa0000000));
		contentView.setOnTouchListener(listener);
		
		InitView();
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		AddNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				Intent intent=new Intent(v.getContext(),NewContacts.class);
				intent.putExtra("telnum", mDialpad.getTelNumbers());
				context.startActivity(intent);
			}
		});
		
		SendMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				String numbers =mDialpad.getTelNumbers();
				Uri smsToUri=Uri.parse("smsto:"+numbers);
				Intent intent =new Intent(Intent.ACTION_SENDTO, smsToUri);
				context.startActivity(intent);
			}
		});
		
		AddExists.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "sorry!此功能暂未实现，试试其他的呗！", Toast.LENGTH_LONG).show();
//				dismiss();
			}
		});
	}
	
	OnTouchListener listener= new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int height =v.findViewById(R.id.head).getTop();
			int y=(int) event.getY();
			if(event.getAction()==MotionEvent.ACTION_UP){
				if(y<height){
					dismiss();
				}
			}
			return true;
		}
	};
	

	private void InitView() {
		AddNew =(TextView) contentView.findViewById(R.id.addnew);
		AddExists =(TextView) contentView.findViewById(R.id.addExisted);
		SendMessage =(TextView) contentView.findViewById(R.id.sendMessage);
		cancel=(LinearLayout) contentView.findViewById(R.id.cancel);
	}


	public void showPopUpwindow(View parent){
		if(!this.isShowing()){
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0); 
		}else{
			this.dismiss();
		}
	}
}
