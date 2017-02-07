package com.Hemi.CallRecords;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.Hemi.dataBase.ContactsInfo;
import com.Hemi.dataBase.mContacts;
import com.Hemi.mphone.MyApplication;
import com.Hemi.mphone.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CallRecords extends Fragment implements OnClickListener {

	private Context context;
	private CallRecordAdapter callAdapter;
	private List<CallEntity> callrecords = new ArrayList<CallEntity>();
	private ListView CallRecordsListView;

	private TextView allCall;
	private TextView MissedCall;
	private TextView edit;
	private TextView mDelete;
	private TextView finish;
	private boolean flag;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View callRecordsView = inflater.inflate(R.layout.fragment_phonerecord,null);
		
		CallRecordsListView = (ListView) callRecordsView.findViewById(R.id.records);

		context = MyApplication.getInstance();
		flag = true;

		allCall = (TextView) callRecordsView.findViewById(R.id.allcall);
		allCall.setBackgroundResource(R.drawable.shape_allcall_selected);
		allCall.setOnClickListener(this);

		MissedCall = (TextView) callRecordsView.findViewById(R.id.misscall);
		MissedCall.setOnClickListener(this);
		
		mDelete=(TextView) callRecordsView.findViewById(R.id.delete);
		mDelete.setOnClickListener(this);
		
		finish=(TextView) callRecordsView.findViewById(R.id.finish);
		finish.setOnClickListener(this);
		

		edit = (TextView) callRecordsView.findViewById(R.id.edit);
		edit.setOnClickListener(this);
		
		updateCallRecords();

		return callRecordsView;
	}

	

	private void updateCallRecords() {
		callrecords.removeAll(callrecords);
		getCallRecords(flag);
		callAdapter = new CallRecordAdapter(context, callrecords);
		CallRecordsListView.setAdapter(callAdapter);
		callAdapter.notifyDataSetChanged();
		CallRecordsListView.invalidate();
		
	}
	

	
	private void getCallRecords(Boolean flag) {
		Cursor cursor = context.getContentResolver().query(
				CallLog.Calls.CONTENT_URI, null, null, null, null);
		int i = 0;
		if (cursor.moveToLast()) {
			String prenumber = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));
			int type = TypeDecision(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Calls.TYPE))));
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(Calls.DATE))));
			String time = sfd.format(date);
			String name = getContactName(prenumber);
			String duration = cursor.getString(cursor.getColumnIndexOrThrow(Calls.DURATION));
			if (flag) {
				callrecords.add(new CallEntity(type, time, "0", name, prenumber, duration));
			} else {
				if (type == 2) {
					callrecords.add(new CallEntity(type, time, "0", name, prenumber, duration));
				}
			}

			while (cursor.moveToPrevious()) {
				String numbers = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));
				int temptype = TypeDecision(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Calls.TYPE))));
				SimpleDateFormat sfd1 = new SimpleDateFormat("yyyy-MM-dd");
				Date tempdate = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(Calls.DATE))));
				String temptime = sfd.format(tempdate);
				String tempname = getContactName(numbers);
				String tempduration = cursor.getString(cursor.getColumnIndexOrThrow(Calls.DURATION));

				if (flag) {
					if (!prenumber.equals(numbers) || !temptime.equals(time) || (temptype != type)) {

						callrecords.add(new CallEntity(temptype, temptime, "0", tempname, numbers, tempduration));

						prenumber = numbers;
						time = temptime;
						type = temptype;
					} else {
						time = temptime;
						prenumber = numbers;
						type = temptype;
					}
				} else {
					if (temptype == 2 && (!prenumber.equals(numbers) || !temptime.equals(time))) {
						callrecords.add(new CallEntity(temptype, temptime, "0", tempname, numbers, tempduration));
						
						prenumber = numbers;
						time = temptime;
					} else {
						time = temptime;
						prenumber = numbers;
					}
				}
			}
		}
	}

	public String getContactName(String PhoneNumber) {
		String contactName = null;
		mContacts con=new mContacts();
		List<ContactsInfo> contacts = con.getAllContacts();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getContactNumber().equals(PhoneNumber)) {
				contactName = contacts.get(i).getContactName();
				break;
			}
		}
		return contactName;
	}

	private int TypeDecision(int type) {
		int Mytype;
		switch (type) {
		case Calls.INCOMING_TYPE:
			Mytype = 0;
			break;

		case Calls.OUTGOING_TYPE:
			Mytype = 1;
			break;

		case Calls.MISSED_TYPE:
			Mytype = 2;
			break;

		default:
			Mytype = 3;// ¹Ò¶Ï
			break;
		}
		return Mytype;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.allcall:
			flag = true;
			allCall.setBackgroundResource(R.drawable.shape_allcall_selected);
			MissedCall.setBackgroundColor(Color.TRANSPARENT);
			mDelete.setVisibility(View.GONE);
			edit.setVisibility(View.VISIBLE);
			finish.setVisibility(View.GONE);
			
			updateCallRecords();
			break;

		case R.id.misscall:
			flag = false;
			MissedCall.setBackgroundResource(R.drawable.shape_misscall_selected);
			allCall.setBackgroundColor(Color.TRANSPARENT);
			mDelete.setVisibility(View.GONE);
			edit.setVisibility(View.VISIBLE);
			finish.setVisibility(View.GONE);
			
			updateCallRecords();
			break;

		case R.id.edit:
			edit.setVisibility(View.GONE);
			finish.setVisibility(View.VISIBLE);
			mDelete.setVisibility(View.VISIBLE);
			callAdapter.setFlag(false);
			callAdapter.notifyDataSetChanged();
			break;
			
		case R.id.finish:
			edit.setVisibility(View.VISIBLE);
			finish.setVisibility(View.GONE);
			mDelete.setVisibility(View.GONE);
			callAdapter.setFlag(true);
			callAdapter.notifyDataSetChanged();
			break;
			
		case R.id.delete:
			CallRecordPopWindow popWindow= new CallRecordPopWindow(getActivity());
			popWindow.showPopUpwindow(v);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		updateCallRecords();
	}
	
}
