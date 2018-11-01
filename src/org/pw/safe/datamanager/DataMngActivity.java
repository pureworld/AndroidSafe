package org.pw.safe.datamanager;

import java.util.ArrayList;
import java.util.List;

import org.pw.safe.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.TrafficStats;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class DataMngActivity extends Activity {
	private ListView mDataListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datamgn);
		
		mDataListView = (ListView)findViewById(R.id.listView_data);
		
		TextView topView = new TextView(this);
		topView.setText("流量查看");
		mDataListView.addHeaderView(topView);
		
		DatamngAdapter adapter = new DatamngAdapter(this, getData());
		
		mDataListView.setAdapter(adapter);
	}
	
	private List<DataInfo> getData() {
		List<DataInfo> list = new ArrayList<DataInfo>();
		
		PackageManager pm = getPackageManager();
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> run = am.getRunningAppProcesses();

		
		for (ActivityManager.RunningAppProcessInfo runningProcess : run) {
		   DataInfo info = new DataInfo();
		   ApplicationInfo applicationInfo;
		   
		try {
			applicationInfo = pm.getApplicationInfo(runningProcess.processName, 0);
			   
			info.setPackageName(runningProcess.processName);		
			info.setnRxData(TrafficStats.getUidRxBytes(runningProcess.uid));
		    info.setnTxData(TrafficStats.getUidTxBytes(runningProcess.uid));
		    info.setAppName((String) applicationInfo.loadLabel(pm));
		    info.setAppIcon(applicationInfo.loadIcon(pm));
		    list.add(info);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		}
		return list;
	}
}
