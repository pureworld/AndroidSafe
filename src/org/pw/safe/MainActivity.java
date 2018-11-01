package org.pw.safe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.pw.safe.atools.AtoolsActivity;
import org.pw.safe.callmsgsafe.CallmsgsafeActivity;
import org.pw.safe.callmsgsafe.CallmsgsafeAdapter;
import org.pw.safe.datamanager.DataMngActivity;
import org.pw.safe.settings.SettingsActivity;
import org.pw.safe.softmanager.SoftMngActivity;
import org.pw.safe.taskmanager.TaskMgnActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemClickListener {
	GridView mGridView = null;
	MyAdapter mAdapter = null;
	final String DBNAME = "address.db";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.gridView1);
        mAdapter = new MyAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        
		Intent service = new Intent(this ,MyService.class);
		service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startService(service);
        
        File file = new File(getFilesDir(),DBNAME);
        if (!file.exists()) {
        	AssetManager am = getAssets();
        	try {
				InputStream is = am.open(DBNAME);
				FileOutputStream fos = new FileOutputStream(file);
				byte [] buffer = new byte[1024];
				int len ;
				while((len = is.read(buffer))!= -1){
					fos.write(buffer, 0, len);
				}
				buffer = null;
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0: {
			
		}
			break;
		case 1: {
			startActivity(new Intent(MainActivity.this, CallmsgsafeActivity.class));
		}
			break;
		case 2: {
			startActivity(new Intent(MainActivity.this, SoftMngActivity.class));
		}
			break;
		case 3: {
			startActivity(new Intent(MainActivity.this, TaskMgnActivity.class));
		}
			break;
		case 4: {
			startActivity(new Intent(MainActivity.this, DataMngActivity.class));
		}
			break;
		case 5: {
			
		}
			break;
		case 6: {
			
		}
			break;
		case 7: {
			startActivity(new Intent(MainActivity.this, AtoolsActivity.class));
		}
			break;
		case 8: {
			startActivity(new Intent(MainActivity.this, SettingsActivity.class));
		}
			break;
		}
		
	}
}
