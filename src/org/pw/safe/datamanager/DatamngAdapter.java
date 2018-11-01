package org.pw.safe.datamanager;

import java.util.List;

import org.pw.safe.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DatamngAdapter extends BaseAdapter {

	Context mContext;
	List<DataInfo> list;
	int mPosition;	

	public DatamngAdapter(Context mContext, List<DataInfo> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.datalist, null);
		
		ImageView imageView = (ImageView)(view.findViewById(R.id.img_data));
		imageView.setImageDrawable(list.get(position).getAppIcon());
		TextView appNameView = (TextView)(view.findViewById(R.id.title_data));
		appNameView.setText(list.get(position).getAppName());
		TextView appUpView = (TextView)(view.findViewById(R.id.up_data));		
		appUpView.setText(Long.toString(list.get(position).getnRxData()) + "bytes");
		TextView appDownView = (TextView)(view.findViewById(R.id.down_data));
		appDownView.setText(Long.toString(list.get(position).getnTxData()) + "bytes");
		
		return view;
	}

}
