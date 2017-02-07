package com.Hemi.Contacts;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.Hemi.mphone.MainActivity;
import com.Hemi.mphone.R;
import com.Hemi.mphone.R.drawable;
import com.Hemi.mphone.R.id;
import com.Hemi.mphone.R.layout;
import com.Hemi.mphone.R.menu;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NewContacts extends Activity implements OnClickListener {
	
	private TextView addBirthday;
	private EditText inputName;
	private EditText telNum;
	private int year;
	private int month;
	private int day;
	private Intent intent;
	
	InputMethodManager inputManager;
	private ActionBar actionBar;
	private TextView mCancel;
	private TextView mFinish;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newcontacts);
		
		actionBar=getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.newcontact_actionbar);
		
		initView();
	}
	
	private void initView() {
		intent=getIntent();
		
		addBirthday=(TextView) findViewById(R.id.addBirthday);
		addBirthday.setOnClickListener(this);
		
		telNum=(EditText) findViewById(R.id.telnum);
		telNum.setText(intent.getCharSequenceExtra("telnum"));
		
		inputName=(EditText) findViewById(R.id.name);
		
		inputManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
		
		mCancel=(TextView) findViewById(R.id.cancel_new);
		mCancel.setOnClickListener(this);
		
		mFinish=(TextView) findViewById(R.id.mFinish);
		mFinish.setOnClickListener(this);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater= getMenuInflater();
//		inflater.inflate(R.menu.contacts_actionbar, menu);
//		return super.onCreateOptionsMenu(menu);
//	}

	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.finish:
//			
//			break;
//			
//		case android.R.id.home:
//			finish();
//			/**
//			 * ���������ؼ�������Դ���
//			 */
//			 if(inputManager.isActive()&&getCurrentFocus()!=null){
//			    if (getCurrentFocus().getWindowToken()!=null) {
//			    	inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//			    }             
//			 }
//			break;
//
//		default:
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addBirthday:
			Calendar mycalendar=Calendar.getInstance(Locale.CHINA);
	        Date mydate=new Date(); //��ȡ��ǰ����Date����
	        mycalendar.setTime(mydate);////ΪCalendar��������ʱ��Ϊ��ǰ����
	        
	        year=mycalendar.get(Calendar.YEAR); //��ȡCalendar�����е���
	        month=mycalendar.get(Calendar.MONTH);//��ȡCalendar�����е���
	        day=mycalendar.get(Calendar.DAY_OF_MONTH);//��ȡ����µĵڼ���
			DatePickerDialog datepickerDialog=new DatePickerDialog(NewContacts.this, DateListener, year, month, day);
			datepickerDialog.show();
			break;
			
		case R.id.cancel_new:
			finish();
			/**
			 * ���������ؼ�������Դ���
			 */
			 if(inputManager.isActive()&&getCurrentFocus()!=null){
			    if (getCurrentFocus().getWindowToken()!=null) {
			    	inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			    }             
			 }
			break;
			
		case R.id.mFinish:
			
			break;

		default:
			break;
		}
		
	}
	
	/**
	 * ʵ�ֵ������հ״�������ʧ
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if (event.getAction() == MotionEvent.ACTION_DOWN) {    
	        if (NewContacts.this.getCurrentFocus() != null) {    
	              if (NewContacts.this.getCurrentFocus().getWindowToken() != null) {    
	            	  inputManager.hideSoftInputFromWindow(NewContacts.this.getCurrentFocus().getWindowToken(),    
	                    InputMethodManager.HIDE_NOT_ALWAYS);    
	           }    
	       }    
	    } 
		return super.onTouchEvent(event);
	}
	
	private DatePickerDialog.OnDateSetListener DateListener=new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
            month=monthOfYear;
            day=dayOfMonth;
            //��������
            updateDate();
		}

		private void updateDate() {
			addBirthday.setText((month+1)+"��"+day+"��");
		}
	};
}
