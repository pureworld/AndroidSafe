package org.pw.safe.softmanager;

import java.util.List;

import android.graphics.drawable.Drawable;

public class AppInfo {

	String appName;  
    String packageName;  
    Drawable drawable;
    String activityName;
    String appVersion;
    List<String> appPermission;
	
	public AppInfo() { }
    public AppInfo(String appName, String packageName, Drawable drawable,
    		String activityName, String appVersion, List<String> appPermissionInfos) {
		this.appName = appName;
		this.packageName = packageName;
		this.drawable = drawable;
		this.activityName = activityName;
		this.appVersion = appVersion;
		this.appPermission = appPermissionInfos;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public List<String> getAppPermission() {
		return appPermission;
	}
	public void setAppPermission(List<String> appPermission) {
		this.appPermission = appPermission;
	}
}
