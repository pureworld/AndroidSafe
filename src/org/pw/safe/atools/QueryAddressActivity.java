package org.pw.safe.atools;

import java.io.File;

import org.pw.safe.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class QueryAddressActivity extends Activity {

	final String DBNAME = "address.db";
	
	EditText mPhoneNum = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queryaddress);
		
		mPhoneNum = (EditText) findViewById(R.id.editText_phone);
		findViewById(R.id.btn_query).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(QueryAddressActivity.this,
						getAddress(mPhoneNum.getText().toString()),
						Toast.LENGTH_LONG).
					show();				
				
			}
		});
	}

	public String getAddress(String number){
		String address = number ;
		File file = new File(getFilesDir(),DBNAME);
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
					address = getApplicationContext().getResources().getString(R.string.local);
					break;
				case 8:
					address = getApplicationContext().getResources().getString(R.string.local);
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
					address = getApplicationContext().getResources().getString(R.string.local);
					break;
				}
			}
		}
		db.close();
		return address;
	} 
}
