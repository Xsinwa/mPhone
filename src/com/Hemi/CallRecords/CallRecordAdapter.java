package com.Hemi.CallRecords;

import java.util.List;

import com.Hemi.mphone.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CallRecordAdapter extends BaseAdapter {

	private static Context context;
	private List<CallEntity> callEntityList;
	private Boolean mflag = true;

	public CallRecordAdapter(Context context, List<CallEntity> callEntityList) {
		super();
		this.context = context;
		this.callEntityList = callEntityList;
	}

	@Override
	public int getCount() {
		return callEntityList.size();
	}

	@Override
	public Object getItem(int position) {
		return callEntityList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		final CallEntity callentity = callEntityList.get(position);
		int type;
		switch (callentity.getType()) {
		case 0:
			type=0;
			break;
		case 1:
			type=1;
			break;
		case 2:
			type=2;
			break;

		default:
			type=0;
			break;
		}
		return type;
	}
	
	@Override
	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		final CallEntity callentity = callEntityList.get(position);
		
		if(convertView==null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			switch (getItemViewType(position)) {
			case 0:
				convertView = inflater.inflate(R.layout.item_call_in, null);
				break;

			case 1:
				convertView = inflater.inflate(R.layout.item_call_out, null);
				break;

			case 2:
				convertView = inflater.inflate(R.layout.item_call_in, null);
				break;

			default:
				break;
			}
			
			viewHolder.initView(convertView);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}

		
		viewHolder.callDate.setText(callentity.getCallDate());
		viewHolder.callLocation.setText(callentity.getSimLocation());
		NameOrNum(viewHolder, callentity.getCallName(),callentity.getCallNum());
		colordetermine(viewHolder, position);
		if(mflag){
			viewHolder.delRecord.setVisibility(View.GONE);
			viewHolder.mDetail.setVisibility(View.VISIBLE);
		}else {
			viewHolder.delRecord.setVisibility(View.VISIBLE);
			viewHolder.mDetail.setVisibility(View.GONE);
		}

		viewHolder.mDetail.setTag(position);
		viewHolder.mDetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CallDetails.class);
				intent.putExtra("phone_numbers", callentity.getCallNum());
				intent.putExtra("CallDate", callentity.getCallDate());
				String name = callentity.getCallName();
				intent.putExtra("contactName", name);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});

		viewHolder.callInformation.setTag(position);
		viewHolder.callInformation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + callentity.getCallNum()));
				// ¿ªÆôÏµÍ³²¦ºÅÆ÷
				context.startActivity(intent);

			}
		});
		return convertView;
	}
	
	public Boolean setFlag(Boolean flag){
		mflag=flag;
		return mflag;
	}
	
	private void NameOrNum(ViewHolder holder, String callName, String callNum) {
		if (null == callName){
			holder.callName.setText(callNum);
		}else {
			holder.callName.setText(callName);
		}
	}

	private void colordetermine(ViewHolder holder, int position){
		
		if (getItemViewType(position)==2) {
			holder.callName.setTextColor(Color.RED);
		}else {
			holder.callName.setTextColor(Color.DKGRAY);
		}
	}
	

	static class ViewHolder {
		int position;
		private TextView callName;
		private TextView callDate;
		private TextView callLocation;
		private TextView delRecord;
		private RelativeLayout mDetail;
		private LinearLayout callInformation;
		
		public void initView(View view){
			callDate = (TextView) view.findViewById(R.id.callDate);
			callLocation = (TextView) view.findViewById(R.id.location);
			callName = (TextView) view.findViewById(R.id.phone_numb);
			mDetail = (RelativeLayout) view.findViewById(R.id.detail);
			callInformation = (LinearLayout) view.findViewById(R.id.call_information);
			delRecord= (TextView) view.findViewById(R.id.delrecord);
		}
		
	}

}
