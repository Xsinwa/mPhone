package com.Hemi.dataBase;

import java.util.ArrayList;
import java.util.List;

import com.Hemi.mphone.MyApplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;

public class mContacts {
	private Context context=MyApplication.getInstance();
	
	private static final int PHONE_DISPLAY_NAME_INDEX = 0;
	private static final int PHONE_NUMBER_INDEX = 1;
	
	private List<ContactsInfo> contacts=new ArrayList<ContactsInfo>();
	private static final  String[] PHONE_PROJECTION =new String[]{Phone.DISPLAY_NAME,Phone.NUMBER};
	
	public List<ContactsInfo> getAllContacts(){
		getPhoneContacts();
//		getSimContacts();
		
		return contacts;
	}
	
	private void getPhoneContacts(){
		Cursor PhoneCursor = context.getContentResolver().query(Phone.CONTENT_URI, PHONE_PROJECTION, null, null, null);
		if (PhoneCursor != null) {
			while (PhoneCursor.moveToNext()) {
				String ConPhoneNumber = PhoneCursor.getString(PHONE_NUMBER_INDEX);
				if (TextUtils.isEmpty(ConPhoneNumber))
					continue;
				
				String ContactName=PhoneCursor.getString(PHONE_DISPLAY_NAME_INDEX);
				contacts.add(new ContactsInfo(ContactName, ConPhoneNumber));
			}
			
		}
		
	}
	
	private void getSimContacts(){
		Uri uri=Uri.parse("content://icc//adn");
		Cursor SimCursor=context.getContentResolver().query(uri, PHONE_PROJECTION, null, null, null);
		if (SimCursor != null) {
			while (SimCursor.moveToNext()) {
				String ConPhoneNumber = SimCursor.getString(PHONE_NUMBER_INDEX);
				if (TextUtils.isEmpty(ConPhoneNumber))
					continue;
				
				String ContactName=SimCursor.getString(PHONE_DISPLAY_NAME_INDEX);
				contacts.add(new ContactsInfo(ContactName, ConPhoneNumber));
			}
			
		}
		
	}

}
