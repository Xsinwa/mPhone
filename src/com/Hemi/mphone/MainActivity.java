package com.Hemi.mphone;


import com.Hemi.CallRecords.CallRecords;
import com.Hemi.Contacts.PhoneContacts;
import com.Hemi.DialPadActivity.mDialpad;
import com.Hemi.dataBase.SharedPreferenceUtils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;


public class MainActivity extends Activity {
//	private final static String Tag="MainActivity";
	private mDialpad dialpad;
	private PhoneContacts phoneContacts;
	private CallRecords callRecords;
	
	
	private FragmentManager fragmentManager;
	private RadioGroup radioGroup;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        
    }
    
    

	private void initView() {
		fragmentManager=getFragmentManager();
		radioGroup=(RadioGroup) findViewById(R.id.phoneTab);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				FragmentTransaction transaction = fragmentManager.beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
				if(null!=dialpad){
					transaction.hide(dialpad);
				}
				if(null != callRecords){
					transaction.hide(callRecords);
				}
				if(null != phoneContacts){
					transaction.hide(phoneContacts);
				}
				switch (checkedId) {
				case R.id.callrecords:
					if(null==callRecords){
						callRecords= new CallRecords();
						transaction.add(R.id.contentActivity, callRecords);
					}else{
						transaction.show(callRecords);
					}
					SharedPreferenceUtils.setData(MainActivity.this,"value",0);
					break;
				case R.id.phonecontacts:
					if(null==phoneContacts){
						phoneContacts= new PhoneContacts();
						transaction.add(R.id.contentActivity, phoneContacts);
					}else{
						transaction.show(phoneContacts);
					}
					SharedPreferenceUtils.setData(MainActivity.this,"value",1);
					break;
				case R.id.dialpad:
					if(null==dialpad){
						dialpad= new mDialpad();
						transaction.add(R.id.contentActivity, dialpad);
					}else{
						transaction.show(dialpad);
					}
					SharedPreferenceUtils.setData(MainActivity.this,"value",2);
					break;

				default:
					break;
				}
				transaction.commit();
			}
		});
	}
	

    @Override
    protected void onStart() {
    	super.onStart();
		int index= SharedPreferenceUtils.getData(this,"value",0);
		switch (index) {
		case 0:
			radioGroup.check(R.id.callrecords);
			break;
			
		case 1:
			radioGroup.check(R.id.phonecontacts);
			break;
			
		case 2:
			radioGroup.check(R.id.dialpad);
			break;

		}
    	
    }
    
	
    /**
     * 创建actionbar菜单
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	MenuInflater inflater=getMenuInflater();
//    	inflater.inflate(R.menu.main_actionbar, menu);
//    	return super.onCreateOptionsMenu(menu);
//    }
//    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	switch (item.getItemId()) {
//		case R.id.newContacts:
//			Intent intent=new Intent(MainActivity.this, NewContacts.class);
//			startActivity(intent);
//			break;
//			
//		case R.id.setGroup:
//			
//			break;
//			
//		case R.id.phoneSetting:
//			
//			break;
//
//		default:
//			break;
//		}
//    	return super.onOptionsItemSelected(item);
//    }
    
//    @Override
//    /**
//     * 显示overflow图标
//     */
//    public boolean onMenuOpened(int featureId, Menu menu) {
//    	if(featureId==Window.FEATURE_ACTION_BAR && menu != null){
//    		if(menu.getClass().getSimpleName().equals("MenuBuilder")){
//    			try{
//    				Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);  
//                    m.setAccessible(true);  
//                    m.invoke(menu, true); 
//    			}catch(Exception e){
//    				
//    			}
//    		}
//    	}
//    	return super.onMenuOpened(featureId, menu);
//    }

}
