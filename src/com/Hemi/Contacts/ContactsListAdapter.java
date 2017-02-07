package com.Hemi.Contacts;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Hemi.mphone.R;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactsListAdapter extends BaseAdapter {
	
	private List<Person> person;
	private Context context;
	

	public ContactsListAdapter(Context context, List<Person> person) {
		super();
		this.person = person;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return person.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return person.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder=null;
		Person mPerson=person.get(position);
		if(convertView ==null){
			viewholder=new ViewHolder();
			convertView=View.inflate(context, R.layout.item_contact, null);
			viewholder.FirstAlpha=(TextView) convertView.findViewById(R.id.first_alpha);
			viewholder.ContactName=(TextView) convertView.findViewById(R.id.contact_name);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		
		
		String firstAlpha = String.valueOf(mPerson.getAlphas().charAt(0));
		String PreFirstAlpha;
		if(position==0){
			PreFirstAlpha="-";
		}else {
			PreFirstAlpha=String.valueOf(person.get(position-1).getAlphas().charAt(0));
		}
		viewholder.FirstAlpha.setVisibility(!TextUtils.equals(firstAlpha,
				PreFirstAlpha) ? View.VISIBLE : View.GONE);
		viewholder.FirstAlpha.setText(String.valueOf(mPerson.getAlphas().charAt(0)));
		viewholder.ContactName.setText(mPerson.getPersonName());
		return convertView;
	}
	
	static class ViewHolder{
		private TextView FirstAlpha;
		private TextView ContactName;
	}

}
