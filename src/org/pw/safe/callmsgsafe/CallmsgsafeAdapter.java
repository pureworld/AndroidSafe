package org.pw.safe.callmsgsafe;

import java.util.HashSet;
import java.util.Set;

import org.pw.safe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CallmsgsafeAdapter extends BaseAdapter {

	Set<String> mDataSet = new HashSet<String>();
	
	LayoutInflater mInflater = null;
	
	public CallmsgsafeAdapter(Context context, Set<String> data) {
		mDataSet = data;
		mInflater = LayoutInflater.from(context);
	}
	
	public void Add(String num) {
		mDataSet.add(num);
	}
	
	public void Del(String num) {
		if (mDataSet.contains(num)) {
			mDataSet.remove(num);
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDataSet.size();
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
			view = mInflater.inflate(R.layout.layout_callmsgsafe_item, null);
		} else {
			view = convertView;
		}
		
		TextView textView = (TextView) view.findViewById(R.id.number);
		textView.setText((String)mDataSet.toArray()[position]);
		
		return view;
	}

}
