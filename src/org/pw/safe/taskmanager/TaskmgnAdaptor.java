package org.pw.safe.taskmanager;

import java.util.List;

import org.pw.safe.R;
import org.pw.safe.R.id;
import org.pw.safe.R.layout;
import org.pw.safe.R.menu;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class TaskmgnAdaptor extends BaseAdapter {

	Context mContext;
	List<TaskInfo> mInfos;
	int mPosition;
	
	public TaskmgnAdaptor(Context context, List<TaskInfo> taskInfo) {
		this.mContext = context;
		this.mInfos = taskInfo;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.vlist, null);
		view.setTag(position);
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(mContext, v);
				MenuInflater inflater = popupMenu.getMenuInflater();
				inflater.inflate(R.menu.menu_task, popupMenu.getMenu());
				mPosition = (int)(Integer)(v.getTag());
				
				popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
						case R.id.kill: {
							ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
							am.killBackgroundProcesses(mInfos.get(mPosition).getPackageName());
						}
							break;
						case R.id.detail: {
							ListView lstView = new ListView(mContext);
							String[] items = {"程序名字" + mInfos.get(mPosition).getAppName(),
                                    "程序版本" + mInfos.get(mPosition).getAppVersion(),
                                    "程序包名" + mInfos.get(mPosition).getPackageName(),
                                    "程序权限"};
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
									android.R.layout.simple_list_item_1, items);
							lstView.setAdapter(adapter);
							
							AlertDialog dlg = new AlertDialog.Builder(mContext)
											.setView(lstView)
											.create();
							dlg.show();
						}
							break;
						case R.id.uninstall: {
			        		Intent intent2 = new Intent();
			            	intent2.setAction(Intent.ACTION_DELETE);
			            	intent2.setData(Uri.parse("package:" + mInfos.get(mPosition).getPackageName()));
			            	mContext.startActivity(intent2);
						}
							break;
						default:
							break;
						}
						return false;
					}
					
				});	
				popupMenu.show();
			}
		});
		
		
		ImageView imageView = (ImageView)(view.findViewById(R.id.img));
		imageView.setImageDrawable(mInfos.get(position).getDrawable());
		TextView appNameView = (TextView)(view.findViewById(R.id.title));
		appNameView.setText(mInfos.get(position).getAppName());
		TextView appPackageView = (TextView)(view.findViewById(R.id.info));
		appPackageView.setText(Integer.toString(mInfos.get(position).getAppMemory()) + "kb");
		
		return view;
	}

}
