package org.pw.safe.callmsgsafe;

import java.util.HashSet;
import java.util.Set;

import org.pw.safe.R;
import org.pw.safe.taskmanager.TaskmgnAdaptor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class CallmsgsafeActivity extends Activity {

	@Override
	protected void onDestroy() {
		Editor ed = mSharedPreferences.edit();
		ed.putStringSet("call", mAdapter.mDataSet);
		ed.commit();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater infalter = getMenuInflater();
		infalter.inflate(R.menu.menu_callmsgsafe, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int itemId = item.getItemId();
		switch(itemId){
		case R.id.menu_addblacklist: {
				View view = View.inflate(this, R.layout.layout_edittext, null);
				final EditText editText = (EditText) view.findViewById(R.id.editText1);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(R.string.add_blacklist)
				.setView(view)
				.setNegativeButton(R.string.ok, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mAdapter.Add(editText.getText().toString());
						mAdapter.notifyDataSetChanged();
					}
				})
				.setPositiveButton(R.string.cancel, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.create().show();
				
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private ListView mCallmsgsafeListView = null;
	SharedPreferences mSharedPreferences = null;
	CallmsgsafeAdapter mAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callmsgsafe);
		
		mSharedPreferences = getSharedPreferences("blacklist", MODE_PRIVATE);
		
		mCallmsgsafeListView = (ListView)findViewById(R.id.listView_callmsgsafe);	
		mAdapter = new CallmsgsafeAdapter(this, getData());		
		mCallmsgsafeListView.setAdapter(mAdapter);
		
	}
	
	public Set<String> getData() { 
		Set<String> data = mSharedPreferences.getStringSet("call", new HashSet<String>());
		return data;
	}

}
