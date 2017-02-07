package com.Hemi.Contacts;

import com.Hemi.mphone.R;
import com.Hemi.mphone.R.id;
import com.Hemi.mphone.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MyPopupWindow extends PopupWindow {
	private View contentView;
	
	private LinearLayout newContacts;
	private LinearLayout mGroup;
	private LinearLayout pSetting;

	public MyPopupWindow(final Activity context) {
		super(context);
		
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView=inflater.inflate(R.layout.popwindow, null);
		this.setContentView(contentView);
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		this.setWidth(w/2-50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.update();
		this.setBackgroundDrawable(new ColorDrawable(0xa0000000));
		
		initView();
		
		//新建联系人
		newContacts.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				Intent intent=new Intent(v.getContext(),NewContacts.class);
				context.startActivity(intent);
			}
		});
		
		//群组
		mGroup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//通话设置
		pSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void initView() {
		newContacts=(LinearLayout) contentView.findViewById(R.id.addContacts);
		mGroup=(LinearLayout) contentView.findViewById(R.id.mGroup);
		pSetting=(LinearLayout) contentView.findViewById(R.id.pSetting);
		
	}

	public void showPopUpwindow(View parent){
		if(!this.isShowing()){
			this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 30); 
		}else{
			this.dismiss();
		}
	}
	
	
}
