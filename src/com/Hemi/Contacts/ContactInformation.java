package com.Hemi.Contacts;

import com.Hemi.mphone.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactInformation extends Activity implements OnClickListener {
	private Intent intent;
	private TextView numbers;
	private LinearLayout CallNum;
	private TextView SendMessage;
	private TextView AddToBlack;
	private TextView ContactName;
	private LinearLayout Backword;
	private LinearLayout Send_Message;
	private LinearLayout dialup;
	private LinearLayout videoCall;
	private ActionBar actionbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_contactinformation);

		actionbar=getActionBar();
		actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionbar.setCustomView(R.layout.contactinformation_bar);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		InitView();

	}

	private void InitView() {
		intent = getIntent();
		numbers = (TextView) findViewById(R.id.pho_number);
		numbers.setText(intent.getStringExtra("numbers"));
		
		ContactName=(TextView) findViewById(R.id.contactname);
		ContactName.setText(intent.getStringExtra("name"));

		CallNum = (LinearLayout) findViewById(R.id.CallNum);
		CallNum.setOnClickListener(this);
		
		SendMessage=(TextView) findViewById(R.id.SendMessage);
		SendMessage.setOnClickListener(this);
		
		AddToBlack=(TextView) findViewById(R.id.addtoblack);
		AddToBlack.setOnClickListener(this);
		
		Backword=(LinearLayout) findViewById(R.id.backword);
		Backword.setOnClickListener(this);
		
		Send_Message=(LinearLayout) findViewById(R.id.message);
		Send_Message.setOnClickListener(this);
		
		dialup=(LinearLayout) findViewById(R.id.phone);
		dialup.setOnClickListener(this);
		
		videoCall=(LinearLayout) findViewById(R.id.videophone);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.CallNum:
			Intent callIntent =new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:"+intent.getStringExtra("numbers")));
			startActivity(callIntent);
			break;
			
		case R.id.SendMessage:
			Uri smsToUri= Uri.parse("smsto:"+intent.getStringExtra("numbers"));
			Intent messageIntent =new Intent(Intent.ACTION_SENDTO, smsToUri);
			startActivity(messageIntent);
			break;
			
		case R.id.addtoblack:
			
			break;
			
		case R.id.backword:
			finish();
			break;
			
		case R.id.message:
			Uri smsToUri1= Uri.parse("smsto:"+intent.getStringExtra("numbers"));
			Intent messageIntent1 =new Intent(Intent.ACTION_SENDTO, smsToUri1);
			startActivity(messageIntent1);
			break;
			
		case R.id.phone:
			Intent callIntent1 =new Intent(Intent.ACTION_CALL);
			callIntent1.setData(Uri.parse("tel:"+intent.getStringExtra("numbers")));
			startActivity(callIntent1);
			break;

		default:
			break;
		}
	}

}
