package org.pw.safe.datamanager;

import android.graphics.drawable.Drawable;

public class DataInfo {
	String packageName;
	String appName;
	Drawable appIcon;
	long nRxData; // 接收流量
	long nTxData; // 发送流量
	
	public DataInfo() { }
	public DataInfo(String packageName, String appName, Drawable appIcon,
			long nRxData, long nTxData) {
		super();
		this.packageName = packageName;
		this.appName = appName;
		this.appIcon = appIcon;
		this.nRxData = nRxData;
		this.nTxData = nTxData;
	}
	
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Drawable getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}
	public long getnRxData() {
		return nRxData;
	}
	public void setnRxData(long nRxData) {
		this.nRxData = nRxData;
	}
	public long getnTxData() {
		return nTxData;
	}
	public void setnTxData(long nTxData) {
		this.nTxData = nTxData;
	}
}
