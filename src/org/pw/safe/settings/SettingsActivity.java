package org.pw.safe.settings;

import org.pw.safe.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class SettingsActivity extends Activity {

	SharedPreferences mSharedPreferences = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		
		mSharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
		boolean isClosed = mSharedPreferences.getBoolean("income", false);

		checkBox.setChecked(isClosed);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Editor editor = mSharedPreferences.edit();
				editor.putBoolean("income", isChecked);
				editor.commit();
			}
		});
		
	}

}
