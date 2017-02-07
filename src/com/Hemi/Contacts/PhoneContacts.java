package com.Hemi.Contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.Hemi.CallRecords.CallDetails;
import com.Hemi.dataBase.ContactsInfo;
import com.Hemi.dataBase.mContacts;
import com.Hemi.mphone.MyApplication;
import com.Hemi.mphone.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class PhoneContacts extends Fragment implements OnClickListener {
	private Context context;
	private List<Person> Persons=new ArrayList<Person>();
	private ContactsListAdapter contactAdapter;
	private ListView contactlist;
	private LettersIndexBar LIndexBar;
	private List<ContactsInfo> contacts = new ArrayList<ContactsInfo>();
	
	private EditText mSearch;
	private ImageView more;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mContacts=inflater.inflate(R.layout.fragment_contacts, null);
		
		contactlist=(ListView) mContacts.findViewById(R.id.list_contacts);
		context=MyApplication.getInstance();
		
		getContactsName();
		contactAdapter=new ContactsListAdapter(context, Persons);
		contactlist.setAdapter(contactAdapter);
		contactAdapter.notifyDataSetChanged();
		contactlist.invalidate();
		
		LIndexBar=(LettersIndexBar) mContacts.findViewById(R.id.index_bar);
		LIndexBar.setOnLetterChangeListener(new LettersIndexBar.OnLetterChangeListener() {
			
			@Override
			public void onLetterChange(int position, String letter) {
				for(int i=0;i<Persons.size();i++){
					String firstAlpha=String.valueOf(Persons.get(i).getAlphas().charAt(0));
					if(TextUtils.equals(firstAlpha, letter)){
						contactlist.setSelection(i);
						break;
					}
				}
			}
		});
		
		
		mSearch=(EditText) mContacts.findViewById(R.id.search);
		mSearch.setCursorVisible(false);
		mSearch.setOnClickListener(this);
		
		more=(ImageView) mContacts.findViewById(R.id.more);
		more.setOnClickListener(this);
		
		return mContacts;
	}
	
	private void getContactsName() {
		mContacts mcontacts= new mContacts();
		contacts=mcontacts.getAllContacts();
		for(int i=0;i<contacts.size();i++){
			String contactName=contacts.get(i).getContactName();
			Persons.add(new Person(contactName));
		}
		
		Collections.sort(Persons);
	}
	
	private String getContactsNumbers(String name){
		mContacts mcontacts= new mContacts();
		contacts=mcontacts.getAllContacts();
		String numbers=null;
		for(int i=0;i<contacts.size();i++){
			String contactName=contacts.get(i).getContactName();
			if(name.equals(contactName)){
				numbers=contacts.get(i).getContactNumber();
				break;
			}
		}
		return numbers;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		contactlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Person mPerson= (Person) contactlist.getItemAtPosition(position);
				String name=mPerson.getPersonName();
				String numbers=getContactsNumbers(name);
				Intent intent = new Intent(context, ContactInformation.class);
				intent.putExtra("name", name);
				intent.putExtra("numbers", numbers);
				startActivity(intent);
			}
			
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search:
			mSearch.setCursorVisible(true);
			break;
			
		case R.id.more:
			MyPopupWindow popwindow=new MyPopupWindow(getActivity());
			popwindow.showPopUpwindow(v);
//			popwindow.showAsDropDown(v, v.getLayoutParams().width / 2, 30);
			break;

		default:
			break;
		}
	}


}
