/**
 * 使用单例模式保持Application的引用,实现在fragment中引用context
 */
package com.Hemi.mphone;

import android.app.Application;

public class MyApplication extends Application {

	private static MyApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
	
	public static MyApplication getInstance(){
		return instance;
	}
}
