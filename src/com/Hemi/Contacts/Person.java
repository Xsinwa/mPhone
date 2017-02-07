package com.Hemi.Contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.annotation.NonNull;

public class Person implements Comparable<Person>{
	private String PersonName;
	private String Alphas;
	
	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public String getAlphas() {

		return Alphas;
	}

	public void setAlphas(String personAlphas) {
		Alphas = personAlphas;
	}

	public Person(String personName) {
		super();
		PersonName = personName;
		
		Pattern pattern1 = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher matcher1 = pattern1.matcher(personName);
		
		Pattern pattern2 = Pattern.compile("[a-zA-Z]+");
		Matcher matcher2 =pattern2.matcher(personName);
		if (matcher1.matches()) {
			Alphas = AlphaUtil.getAlpha(personName);
		} else if(matcher2.matches()){
			Alphas = personName.toUpperCase();
		}else {
			Alphas ="#";
		}
	}

	@Override
	public int compareTo(@NonNull Person another) {
		return Alphas.compareTo(another.getAlphas());
	}

}
