package org.pw.safe.softmanager;

import java.util.ArrayList;
import java.util.List;

import org.pw.safe.R;
import org.pw.safe.R.id;
import org.pw.safe.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SoftMngActivity extends Activity {

	private ListView mSoftListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_softmgn);
		
		mSoftListView = (ListView)findViewById(R.id.listView_soft);
		
		TextView topView = new TextView(this);
		topView.setText("Èí¼þÁÐ±í");
		mSoftListView.addHeaderView(topView);
		
		SoftmgnAdaptor adapter = new SoftmgnAdaptor(this, getData());
		
		mSoftListView.setAdapter(adapter);
	}
	
	private List<AppInfo> getData() {
        List<AppInfo> list = new ArrayList<AppInfo>();
        
        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps =  pm.queryIntentActivities(intent, 0);
        
        for (ResolveInfo info : apps) {
    		PackageInfo pi = null;
			try {
				pi = pm.getPackageInfo(info.activityInfo.packageName, PackageManager.GET_ACTIVITIES
						| PackageManager.GET_PERMISSIONS);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
			List<String> lstStrings = new ArrayList<String>();
			if (pi.permissions != null) {
				for (PermissionInfo info2 : pi.permissions) {
					lstStrings.add(info2.name);
				}
			}

        	AppInfo appInfo = new AppInfo(info.loadLabel(pm).toString(),
            		info.activityInfo.packageName,
            		info.loadIcon(pm),
            		info.activityInfo.name,
            		pi.versionName,
            		lstStrings);

            list.add(appInfo);
        }
         
        return list;
	}
}
