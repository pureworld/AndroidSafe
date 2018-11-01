package org.pw.safe.taskmanager;

import java.util.ArrayList;
import java.util.List;

import org.pw.safe.R;
import org.pw.safe.R.id;
import org.pw.safe.R.layout;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Debug;
import android.widget.ListView;
import android.widget.TextView;

public class TaskMgnActivity extends Activity {

	private ListView mTaskListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taskmgn);
		
		mTaskListView = (ListView)findViewById(R.id.listView_task);
		
		TextView topView = new TextView(this);
		topView.setText("任务列表");
		mTaskListView.addHeaderView(topView);
		
		TaskmgnAdaptor adapter = new TaskmgnAdaptor(this, getData());
		
		mTaskListView.setAdapter(adapter);
	}
	
	public List<TaskInfo> getData() {
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> tasks =  am.getRunningAppProcesses();
        PackageManager pm = getPackageManager();
        
        for (RunningAppProcessInfo info : tasks) {
        	if (info.pid == 0) {
        		continue;
        	}
        	
        	TaskInfo taskInfo = new TaskInfo(info.processName);
        	try {
				ApplicationInfo applicationInfo = pm.getApplicationInfo(info.processName, 0);
				PackageInfo packageInfo = pm.getPackageInfo(info.processName, 0);
				PackageInfo packageInfo2 = pm.getPackageInfo(info.processName, PackageManager.GET_PERMISSIONS);
				
				taskInfo.setAppName((String) applicationInfo.loadLabel(pm));
				taskInfo.setDrawable(applicationInfo.loadIcon(pm));
				taskInfo.setAppVersion(packageInfo.versionName);
				taskInfo.setAppPermission(packageInfo2.requestedPermissions);
				
				int[] myMempid = new int[] { info.pid };
				Debug.MemoryInfo[] memoryInfos = am.getProcessMemoryInfo(myMempid);
				taskInfo.setAppMemory(memoryInfos[0].dalvikPrivateDirty);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
        	list.add(taskInfo);
        }
		
		return list;
	}
}
