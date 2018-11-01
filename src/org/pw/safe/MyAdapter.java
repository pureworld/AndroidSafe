package org.pw.safe;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	int [] mImages = {
			R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,
			R.drawable.taskmanager,R.drawable.netmanager,R.drawable.trojan,
			R.drawable.sysoptimize,R.drawable.atools,R.drawable.settings};
	String [] mNames = {
			"手机防盗","通讯卫士","软件管理",
			"任务管理","流量查看","手机杀毒",
			"系统优化","常用工具","设置中心",
		};
	
	LayoutInflater mInflater = null;
	
	public MyAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNames.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.layout_gridview1_item, null);
		} else {
			view = convertView;
		}
		
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
		imageView.setImageResource(mImages[position]);
		TextView textView = (TextView) view.findViewById(R.id.number);
		textView.setText(mNames[position]);
		
		return view;
	}

}
