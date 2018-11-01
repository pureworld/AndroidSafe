package org.pw.safe;

import java.io.File;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class MyService extends Service {

	WindowManager wm = null;
	TextView textView = null;
	@Override
	public void onCreate() {
		Log.d("MyService", "onCreate");
		
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener()
        {

			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				if (state == TelephonyManager.CALL_STATE_RINGING)
				{
					Context context = getApplicationContext();
					
					boolean isClosed = getIsClosed(context);
					if (isClosed) {
						return;
					}
					
					wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
					textView = new TextView(context);
					textView.setText(getAddress(context, incomingNumber));
					textView.setTextSize(14f);
					
					WindowManager.LayoutParams params = new WindowManager.LayoutParams();
					params.height = WindowManager.LayoutParams.WRAP_CONTENT;
					params.width = WindowManager.LayoutParams.WRAP_CONTENT;
					
					// 设置Window flag
					params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
							| LayoutParams.FLAG_NOT_FOCUSABLE;
					
					params.type = LayoutParams.TYPE_PHONE;
					params.gravity = Gravity.CENTER;
		
					wm.addView(textView, params);
				} else if (state == TelephonyManager.CALL_STATE_IDLE) {
					if (textView != null) {
						wm.removeView(textView);
						textView = null;
					}
				}
				super.onCallStateChanged(state, incomingNumber);
			}
        	
        }, PhoneStateListener.LISTEN_CALL_STATE);
		
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d("MyService", "onDestroy");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	final String DBNAME = "address.db";
	
	public boolean getIsClosed(Context context){
		SharedPreferences sp = getSharedPreferences("mysettings", MODE_PRIVATE);
		return sp.getBoolean("income", false);
	}
	
	public String getAddress(Context context, String number){
		String address = number ;
		File file = new File(context.getFilesDir(),DBNAME);
		SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
		Cursor cur = null;
		if(db.isOpen()){
			if(number.matches("^1[358]\\d{9}$")){	//查询手机号
				number = number.substring(0,7);
				cur = db.rawQuery("select city from info where mobileprefix=?", new String []{number});
				if(cur.moveToFirst()){
					address = cur.getString(0);
				}
				cur.close();
				cur = null;
			}
			else {
				switch(number.length()){
				case 7:
					address = context.getApplicationContext().getResources().getString(R.string.local);
					break;
				case 8:
					address = context.getApplicationContext().getResources().getString(R.string.local);
					break;
				case 10:
					number = number.substring(0,3);
					cur = db.rawQuery("select city from info where area=?", new String[]{number});
					if(cur.moveToFirst()){
						address = cur.getString(0);
					}
					cur.close();
					cur = null;
					break;
				case 11:
					final String numberprefix_3 = number.substring(0, 3);
					final String numberprefix_4 = number.substring(0, 4);
					cur = db.rawQuery("slect city from info where area=? or area=?", new String[]{numberprefix_3,numberprefix_4});
					if(cur.moveToFirst()){
						address = cur.getString(0);
					}
					cur.close();
					cur = null;
					break;
				case 12:
					number = number.substring(0,4);
					cur = db.rawQuery("select city from info where area=?", new String[]{number});
					if(cur.moveToFirst()){
						address = cur.getString(0);
					}
					cur.close();
					cur = null;
					break;
				case 4:	//模拟器
					address = context.getApplicationContext().getResources().getString(R.string.local);
					break;
				}
			}
		}
		db.close();
		return address;
	} 
	
}
