package com.Hemi.DialPadActivity;

import com.Hemi.mphone.R;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class mDialpad extends Fragment implements OnClickListener {
	
	private CircleButton num1;
	private CircleButton num2;
	private CircleButton num3;
	private CircleButton num4;
	private CircleButton num5;
	private CircleButton num6;
	private CircleButton num7;
	private CircleButton num8;
	private CircleButton num9;
	private CircleButton numX;
	private CircleButton num0;
	private CircleButton numJ;
	
	private TextView phoneNumber;
	private ImageView mMore;
	private ImageView mDelete;
	private ImageView dialup;
	private String str;
	
	private static String telNumbers;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View dialpadActivity=inflater.inflate(R.layout.activity_dialpad, container, false);
//		getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		num1=(CircleButton) dialpadActivity.findViewById(R.id.num1);
		num1.setDigitalContent("1");
		num1.setLettersContent(null);
		num1.setOnClickListener(this);
		
		num2=(CircleButton) dialpadActivity.findViewById(R.id.num2);
		num2.setDigitalContent("2");
		num2.setLettersContent("A B C");
		num2.setOnClickListener(this);
		
		num3=(CircleButton) dialpadActivity.findViewById(R.id.num3);
		num3.setDigitalContent("3");
		num3.setLettersContent("D E F");
		num3.setOnClickListener(this);
		
		num4=(CircleButton) dialpadActivity.findViewById(R.id.num4);
		num4.setDigitalContent("4");
		num4.setLettersContent("G H I");
		num4.setOnClickListener(this);
		
		num5=(CircleButton) dialpadActivity.findViewById(R.id.num5);
		num5.setDigitalContent("5");
		num5.setLettersContent("J K L");
		num5.setOnClickListener(this);
		
		num6=(CircleButton) dialpadActivity.findViewById(R.id.num6);
		num6.setDigitalContent("6");
		num6.setLettersContent("M N O");
		num6.setOnClickListener(this);
		
		num7=(CircleButton) dialpadActivity.findViewById(R.id.num7);
		num7.setDigitalContent("7");
		num7.setLettersContent("P Q R S");
		num7.setOnClickListener(this);
		
		num8=(CircleButton) dialpadActivity.findViewById(R.id.num8);
		num8.setDigitalContent("8");
		num8.setLettersContent("T U V");
		num8.setOnClickListener(this);
		
		num9=(CircleButton) dialpadActivity.findViewById(R.id.num9);
		num9.setDigitalContent("9");
		num9.setLettersContent("W X Y Z");
		num9.setOnClickListener(this);
		
		numX=(CircleButton) dialpadActivity.findViewById(R.id.numX);
		numX.setDigitalContent("*");
		numX.setLettersVisibility(View.GONE);
		numX.setOnClickListener(this);
		
		num0=(CircleButton) dialpadActivity.findViewById(R.id.num0);
		num0.setDigitalContent("0");
		num0.setLettersContent("+");
		num0.setOnClickListener(this);
		
		numJ=(CircleButton) dialpadActivity.findViewById(R.id.numJ);
		numJ.setDigitalContent("#");
		numJ.setLettersVisibility(View.GONE);
		numJ.setOnClickListener(this);
		
		phoneNumber=(TextView) dialpadActivity.findViewById(R.id.phonenumber);
		mMore=(ImageView) dialpadActivity.findViewById(R.id.more);
		mMore.setOnClickListener(this);
		
		mDelete=(ImageView) dialpadActivity.findViewById(R.id.delet);
		mDelete.setOnClickListener(this);
		
		dialup=(ImageView) dialpadActivity.findViewById(R.id.dialPhone);
		dialup.setOnClickListener(this);
		
		
		return dialpadActivity;
	}
	
	@Override
	public void onClick(View v) {
		str=phoneNumber.getText().toString();
		mDelete.setVisibility(View.VISIBLE);
		mMore.setVisibility(View.VISIBLE);
		switch (v.getId()) {
		case R.id.num1:
			phoneNumber.setText(str+"1");
			break;
		case R.id.num2:
			phoneNumber.setText(str+"2");
			break;
		case R.id.num3:
			phoneNumber.setText(str+"3");
			break;
		case R.id.num4:
			phoneNumber.setText(str+"4");
			break;
		case R.id.num5:
			phoneNumber.setText(str+"5");
			break;
		case R.id.num6:
			phoneNumber.setText(str+"6");
			break;
		case R.id.num7:
			phoneNumber.setText(str+"7");
			break;
		case R.id.num8:
			phoneNumber.setText(str+"8");
			break;
		case R.id.num9:
			phoneNumber.setText(str+"9");
			break;
		case R.id.numX:
			phoneNumber.setText(str+"*");
			break;
		case R.id.num0:
			phoneNumber.setText(str+"0");
			break;
		case R.id.numJ:
			phoneNumber.setText(str+"#");
			break;
			
		case R.id.delet:
			if(!str.equals("")){
				str=str.substring(0, str.length()-1);
				if(str.equals("")){
					mDelete.setVisibility(View.GONE);
					mMore.setVisibility(View.GONE);
				}
			}
			phoneNumber.setText(str);
			break;
		case R.id.more:
			DialPadPopwindow popwindow=new DialPadPopwindow(getActivity());
			popwindow.showPopUpwindow(v);
			break;
		case R.id.dialPhone:
			if(str.equals("")){
				mDelete.setVisibility(View.GONE);
				mMore.setVisibility(View.GONE);
			}
			Intent intent =new Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + str));  
            //¿ªÆôÏµÍ³²¦ºÅÆ÷  
            startActivity(intent);
			break;

		}
		telNumbers =str;
	}
	
	

	public static String getTelNumbers() {
		return telNumbers;
	}


	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}
	
	/**
	 * ±ÜÃâfragmentÖØµþ
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
