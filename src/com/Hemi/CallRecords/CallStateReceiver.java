package com.Hemi.CallRecords;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallStateReceiver extends BroadcastReceiver {
	private Context mContext;
	
	
	private int oldState =TelephonyManager.CALL_STATE_IDLE;
	private int currentState =TelephonyManager.CALL_STATE_IDLE;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext=context;
		if(intent.getAction().equals("android.intent.action.PHONE_STATE")){
			TelephonyManager tm =(TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
			tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
		}
	}
	
	private class MyPhoneStateListener extends PhoneStateListener{

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			 
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				currentState=TelephonyManager.CALL_STATE_IDLE;
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				currentState=TelephonyManager.CALL_STATE_OFFHOOK;
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				currentState=TelephonyManager.CALL_STATE_RINGING;
				break;

			default:
				break;
			}
			
			if(oldState==TelephonyManager.CALL_STATE_OFFHOOK && currentState ==TelephonyManager.CALL_STATE_IDLE){
//				CallRecords.
			}
		}
	}

}
