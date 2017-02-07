package com.Hemi.dataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferenceUtils {

	public static void setData(Context context, String key, int value) {
		final SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(context);
		settings.edit().putInt(key, value).commit();
	}

	public static int getData(Context context, String key, int value) {
		final SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(context);
		return settings.getInt(key, value);
	}
	
	public static void clearPreference(Context context,  
            final SharedPreferences p) {  
        final Editor editor = p.edit();  
        editor.clear();  
        editor.commit();  
    }  

}
