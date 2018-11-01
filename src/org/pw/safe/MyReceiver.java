package org.pw.safe;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.android.internal.telephony.ITelephony;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class MyReceiver extends BroadcastReceiver {

	SharedPreferences mSharedPreferences;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String state = bundle.getString("state");
		//获得手机号码
		String number = null;
		if (state.equals("RINGING")) {
			number = bundle.getString("incoming_number");
		} else {
			return;
		}
		
		Log.d("MyReceiver", "onReceive");
		
		//黑名单
		mSharedPreferences = context.getSharedPreferences("blacklist", Context.MODE_PRIVATE);
		Set<String> blacklists =  mSharedPreferences.getStringSet("call", new HashSet<String>());
		if (blacklists.contains(number)) {
			 ITelephony phone =(ITelephony)ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
            try {
            	phone.endCall();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
			return;
		}
		
	}
}
