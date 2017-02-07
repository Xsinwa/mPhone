package com.Hemi.DialPadActivity;
import com.Hemi.mphone.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CircleButton extends LinearLayout {
	private TextView number;
	private TextView mLetters;

	public CircleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.mview, this,true);
		
		number=(TextView) findViewById(R.id.dial_digital);
		mLetters=(TextView) findViewById(R.id.dial_letters);
	}
	
	public void setDigitalContent(String text){
		number.setText(text);
	}
	
	public void setLettersContent(String text){
		mLetters.setText(text);
	}
	
	public void setLettersVisibility(int visibility){
		mLetters.setVisibility(visibility);
	}

}
