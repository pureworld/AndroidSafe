package org.pw.safe.taskmanager;

import java.util.List;

import android.graphics.drawable.Drawable;

public class TaskInfo {


	String appName;  
    String packageName;  
    Drawable drawable;
    String activityName;
    String appVersion;
    String[] appPermission;
    int appMemory;
	
    public TaskInfo() { }
    
	public TaskInfo(String packageName) {
		this.packageName = packageName;
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
	public String[] getAppPermission() {
		return appPermission;
	}
	public void setAppPermission(String[] appPermission) {
		this.appPermission = appPermission;
	}
	public int getAppMemory() {
		return appMemory;
	}
	public void setAppMemory(int appMemory) {
		this.appMemory = appMemory;
	}
}
