package com.Hemi.CallRecords;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Hemi.mphone.MyApplication;
import com.Hemi.mphone.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CallDetails extends Activity implements OnClickListener {
	private Context context;
	private CallDetailAdapter callDetailAdapter;
	private List<CallEntity> callDetailList = new ArrayList<CallEntity>();
	private ListView callDetailListview;
	private LinearLayout detailNumbers;
	private LinearLayout addToContacts;
	private TextView phoneNumbers;
	private TextView call_detailDate;
	private int minute;
	private int hour;
	private int second;
	private String callDuration;
	private String callDate;
	private String callNumbers;
	
	private ActionBar actionbar;
	private LinearLayout Backword;
	private TextView catactname;
	private TextView edit;
	private TextView tabName;
	private LinearLayout SendMessage;
	private LinearLayout dialup;
	private LinearLayout videoCall;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_detail);
		
		actionbar=getActionBar();
		actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionbar.setCustomView(R.layout.contactinformation_bar);
		initView();
		
	}

	private void initView() {
		context = MyApplication.getInstance();
		detailNumbers=(LinearLayout) findViewById(R.id.detail_numbers);
		detailNumbers.setOnClickListener(this);
		
		addToContacts=(LinearLayout) findViewById(R.id.item_addtocontacts);
		
		phoneNumbers=(TextView) findViewById(R.id.pho_number);
		call_detailDate=(TextView) findViewById(R.id.detail_calldate);
		
		Intent intent=getIntent();
		
		callNumbers=intent.getStringExtra("phone_numbers");
		phoneNumbers.setText(callNumbers);
		
		callDate =intent.getStringExtra("CallDate");
		call_detailDate.setText(callDate);
		
		callDetailListview =(ListView) findViewById(R.id.call_list);
		
		getCallDetails(callDate,callNumbers);
		callDetailAdapter = new CallDetailAdapter(context, callDetailList);
		callDetailListview.setAdapter(callDetailAdapter);
		callDetailAdapter.notifyDataSetChanged();
		callDetailListview.invalidate();
		
		tabName=(TextView) findViewById(R.id.tabName);
		tabName.setText(R.string.records);
		
		Backword=(LinearLayout) findViewById(R.id.backword);
		Backword.setOnClickListener(this);
		
		catactname= (TextView) findViewById(R.id.contactname);
		if(null!=intent.getStringExtra("contactName")){
			catactname.setText(intent.getStringExtra("contactName"));
			addToContacts.setVisibility(View.GONE);
		}else {
			catactname.setText(callNumbers);
		}
		
		edit=(TextView) findViewById(R.id.contact_edit);
		edit.setVisibility(View.GONE);
		
		SendMessage=(LinearLayout) findViewById(R.id.message);
		SendMessage.setOnClickListener(this);
		
		dialup=(LinearLayout) findViewById(R.id.phone);
		dialup.setOnClickListener(this);
		
		videoCall=(LinearLayout) findViewById(R.id.videophone);
//		videoCall.setOnClickListener(this);
	}

	private void getCallDetails(String callDate, String callNumbers) {
		Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);

		if(cursor.moveToFirst()){
			while(cursor.moveToNext()){
				CallLog calls = new CallLog();
				String numbers = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));
				int type;
				switch (Integer.parseInt(cursor.getString(cursor.getColumnIndex(Calls.TYPE)))) {
				case Calls.INCOMING_TYPE:
					type=0;
					break;
					
				case Calls.OUTGOING_TYPE:
					type=1;
					break;
					
				case Calls.MISSED_TYPE:
					type=2;
					break;
					
				default:
					type=3;//挂断
					break;
				}
				SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");                              
				SimpleDateFormat sfd1 = new SimpleDateFormat("HH:mm:ss");                              
				Date date = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(Calls.DATE))));
				//呼叫时间  
				String mDate = sfd.format(date);
				String time = sfd1.format(date);                                                                  
				
				int tempduration = cursor.getInt(cursor.getColumnIndexOrThrow(Calls.DURATION));
				if((tempduration / 60) != 0){
					minute = tempduration / 60;
					tempduration= tempduration%60;
					if(tempduration !=0){
						minute+=1;	
					}
					callDuration =  minute + "分钟";
					if((minute / 60) != 0){
						hour = minute / 60;
						minute=minute%60;
						if(minute == 0){
							callDuration = hour+"小时";
						}else{
							callDuration = hour+"小时" + callDuration;
						}
					}
					
				}else {
					second = tempduration;
					callDuration = second + "秒钟";
				}
				
				CallRecords callRecords =new CallRecords();
				String contactName=callRecords.getContactName(callNumbers);
				
				if(callDate.equals(mDate) && callNumbers.equals(numbers)){
					callDetailList.add(0, new CallEntity(type, time, "0", contactName, numbers, callDuration));
				}
				

			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_numbers:
			Intent phoneintent =new Intent();
			phoneintent.setAction(Intent.ACTION_CALL);
			phoneintent.setData(Uri.parse("tel:" + callNumbers));  
            //开启系统拨号器  
            startActivity(phoneintent); 
			break;

		case R.id.backword:
			finish();
			break;
			
		case R.id.phone:
			Intent callintent =new Intent();
			callintent.setAction(Intent.ACTION_CALL);
			callintent.setData(Uri.parse("tel:" + callNumbers));  
            //开启系统拨号器  
            startActivity(callintent);
			break;
			
		case R.id.message:
			Intent messageIntent=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+callNumbers));
			startActivity(messageIntent);
			break;
			
		default:
			break;
		}
	}

}
